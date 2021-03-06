/*
 * ONNXProgramGenerator
 * Copyright (c) 2018, 2019 Alexander Merritt
 * All rights reserved.
 * This program is free software: you can redistribute it and/or modify
 *
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.emergentorder.onnx

import java.nio.file._
import scala.meta._
import onnx.onnx._
import collection.JavaConverters._

import scala.reflect.io.Streamable

//TODEFER: Use Squid to clean up / improve this

//TODEFER: de-tuple on the left hand side when there are multiple outputs . should also solve the other output TODOs
object ONNXProgramGenerator {
  def main(args: Array[String]): Unit = {

    @SuppressWarnings(Array("org.wartremover.warts.Equals"))
    implicit final class AnyOps[A](self: A) {
      def ===(other: A): Boolean = self == other
    }

    val fileName = args(0)

    val byteArray = Streamable.bytes(
      getClass.getResourceAsStream("/" + fileName)
    ) // JAVA 9+ only : .readAllBytes()

    val onnxHelper = new ONNXHelper(byteArray)

    val maxOpsetVersion = onnxHelper.maxOpsetVersion

    val schemas =
      org.bytedeco.onnx.OpSchemaRegistry.get_all_schemas_with_history
    val schemasSize = schemas.size

    val scalaCollSchemas = (0 until schemasSize.toInt).map(x => schemas.get(x))
    val schemaMap = scalaCollSchemas
      .filter(x => x.since_version.toInt <= maxOpsetVersion)
      .map(x =>
        x.Name.getString ->
          (x.inputs, x.since_version)
      )
      .toMap

    val useZIO   = false
    val useDotty = false

    //TODO: Test outputs for the benchmark models shown here: https://github.com/onnx/backend-scoreboard
    //TODEFER: run time benchmarks on the same models

    val programName = fileName
      .stripSuffix(".onnx")
      .replaceFirst("\\.", "dot")
      .capitalize + (if (useZIO)
                       "ZIO"
                     else "")
    val path = Paths.get(
      "programGenerator/src/main/scala/gen/" + programName + ".scala"
    );

    //TODEFER: Be explicit about model version, metadata
    //Notes, from the standard:
    //"Each model MUST explicitly name the operator sets that it relies on for its functionality."
    //"An implementation must support all operators in the set or reject the model" - This can happen at runtime via ZIOstyle implicits, possibly mixing backends
    //"Operator sets other than the default operator set MUST specify its domain and SHOULD use reverse domain names based on the responsible organization's identity, the same convention that is used for naming Java packages." - - "Must be unique among all sets."  - Do not support custom opsets initially, backlog
    //"Models MUST specify a domain and use reverse domain names based on the responsible organization's identity, the same convention that is traditionally used for naming Java packages." - Encode this
    //"Note: As of the publication of this document, no ONNX implementation is known to process operator set documents." - backlog

    def fullSource = {
      val params     = onnxHelper.params
      val nodeInputs = onnxHelper.nodeInputs
      println("N " + nodeInputs.size)
      val graphInputs  = onnxHelper.graphInputs
      val graphOutputs = onnxHelper.graphOutputs

      val nodeOutputs = onnxHelper.nodeOutputs
      val outputs     = onnxHelper.outputs
      val attributes  = onnxHelper.attributes

      //val sortedParamNames = params.keys.toSeq.sorted.map(x => "param_" + x)
      val ops         = onnxHelper.ops
      val distinctOps = ops.distinct

      def replaceTypeStrings(s: String) =
        s.replaceAll("uint64", "ULong")
          .replaceAll("uint32", "UInt")
          .replaceAll("uint16", "UShort")
          .replaceAll("uint8", "UByte")
          .replaceAll("int64", "Long")
          .replaceAll("Int64", "Long")
          .replaceAll("int32", "Int")
          .replaceAll("Int32", "Int")
          .replaceAll("int16", "Short")
          .replaceAll("int8", "Byte")
          .replaceAll("string", "String")
          .replaceAll("float", "Float")
          .replaceAll("double", "Double")
          .replaceAll("Bool", "Boolean")
          .replaceAll("bool", "Boolean")
          .replaceAll("complex64", "Complex[Float]")
          .replaceAll("complex128", "Complex[Double]")

      val graphOutputType = replaceTypeStrings(graphOutputs(0)._2)

      val nodesInputsOpsAndOutputs = (nodeInputs zip ops) zip nodeOutputs

      "package org.emergentorder.onnx" + (if (useZIO) "ZIO" else "") + "\n\n" +
        (if (useZIO)
           "import zio.Task\n" +
             "import org.emergentorder.onnx._\n"
         else
           "import org.emergentorder.onnx._\n" +
             "import org.emergentorder.onnx.backends._\n") +
        (if (useDotty) ""
         else
           "import org.emergentorder.union._\n") +
        "import scala.reflect.ClassTag\n" +
        "import spire.implicits._\n" +
        "import spire.math.UByte\n" +
        "import spire.math.UShort\n" +
        "import spire.math.Complex\n" +
        "import spire.math.Numeric\n\n" +
        ("class ") + programName + "(byteArray: Array[Byte]) extends AutoCloseable" + " {\n" +
        (if (useZIO) "val backend = new ONNXNGraphHandlers()"
         else "val backend = new ORTOperatorBackendAll()") +
        "\n" +
        "val bytesDataSource = new ONNXBytesDataSource(byteArray)" +
        "\n" +
        distinctOps
          .map { x =>
            "  val " + x + (if (useZIO) "ZIO" else "") + ": " + x.getOrElse("none").capitalize + (if (useZIO)
                                                                                  "ZIO"
                                                                                else
                                                                                  "") +
              " = backend" +
              "\n"
          } //TODO: inject op/backend implementations
          .mkString("") +
        "  val dataSource: DataSource" + (" = bytesDataSource") + "\n" +
//    "  import cats.implicits._\n" +
        //Omit return type here for now
        "  def program" + (if (graphInputs.size > 0)
                             "(" + graphInputs
                               .map { x =>
                                 "inputData" + x._1 + ": " + (if (useZIO)
                                                                "Task["
                                                              else
                                                                "") + "Tensor[" + x._2 + "]" + (if (
                                                                                                  useZIO
                                                                                                )
                                                                                                  "]"
                                                                                                else
                                                                                                  "")
                               }
                               .mkString(",") + ")") +
        (if (useZIO)
           ": Task[Tensor[" + graphOutputType + "]] " //TODO: Fix graphOutputType for multiple outputs
         else
           ": List[Tensor[" + graphOutputType + "]] ") + " = \n" +
        //Body of program generated here
        "    for {\n" +
        graphInputs
          .map { x =>
            "      node" + x._1.replaceAll("\\.", "") +
              " <- " + (if (useZIO) ""
                        else "List(") + "inputData" + x._1 + //"[" + replaceTypeStrings(x._2) + "]" + //"[T]" +
              (if (useZIO)
                 ""
               else
                 ")")
          }
          .mkString("\n") +
        "\n" +
        params
          .map(x =>
            "      node" + x._1.replaceAll("\\.", "") + " <- "
              + (if (useZIO) "" else "List(") + " dataSource.getParams" + (if (useZIO)
                                                                             "ZIO"
                                                                           else
                                                                             "") + "[" + x._2 + "]" + "(\"" + x._1 + "\")" + (if (
                                                                                                                                useZIO
                                                                                                                              )
                                                                                                                                ""
                                                                                                                              else
                                                                                                                                ")") + "\n"
          )
          .mkString("") +
        (nodesInputsOpsAndOutputs zip attributes)
          .map { x =>
            //TODO: handle multiple outputs
            val nodesOrParams = x._1._1._1.map { y =>
              "Some(node" + y.replaceAll("\\.", "") + ")"
            } // ,""" + y.name.getString + "name" + " = " + """ Some("""" + y + """")""")

            val longFields = x._2 
              .map { y =>
                val field = y.i.asInstanceOf[Long]
                y.name.getOrElse("none") + """ = Some((""" + field.toInt + """))"""
              }

            val longListFields = x._2
              .filter { y =>
                val longListCount = y.ints.size
                val longListList =
                  (0 until longListCount.toInt).map(z => y.ints(z)).toList
                !longListList.isEmpty
              }
              .map { y =>
                val longListCount = y.ints.size
                val longListList =
                  (0 until longListCount.toInt).map(z => y.ints(z)).toList
                val field = longListList.toVector.asInstanceOf[Vector[Long]]
                y.name.getOrElse("none") + """ = Some((Array(""" + field.mkString(",") + """)))"""
              }
            val stringFields = x._2
              .filter { y =>
                val stringCount = y.strings.size
                val stringList =
                  (0 until stringCount.toInt).map(z => y.strings(z)).toList
                !stringList.isEmpty
              }
              .map { y =>
                val stringCount = y.strings.size
                val stringList =
                  (0 until stringCount.toInt)
                    .map(z => y.strings(z).toStringUtf8)
                    .toList
                val field = stringList
                y.name.getOrElse("none") + """ = Some(Array(""" + field
                  .map(z => "\"" + z + "\"")
                  .mkString(",") + """))"""
              }

            val tensorProtoField = x._2 
              .map { y =>
                y.t.map { t =>
                val dimsCount = t.dims.size
                val dimsArray =
                  if (dimsCount == 0) Array(1)
                  else (0 until dimsCount.toInt).map(x => t.dims(x)).toArray
                val field = onnxHelper.onnxTensorProtoToArray(t)
                (field, dimsArray) match {
                  case arrays: (Array[_], Array[_]) =>
                    y.name.getOrElse("none") + " = Some(TensorFactory.getTensor(Array(" + arrays._1
                      .map(x => if (x.toString.contains(".")) x.toString + "f" else x.toString)
                      .mkString(",") + ")," +
                      "Array(" + arrays._2.mkString(",") + ")))"
                }
                }
              }

            val tensorProtoFields = x._2
              .filter { y =>
                val tensorCount = y.tensors.size
                val tensorList =
                  (0 until tensorCount.toInt).map(z => y.tensors(z)).toList
                !tensorList.isEmpty
              }
              .map { y =>
                val tensorCount = y.tensors.size
                val tensorList =
                  (0 until tensorCount.toInt).map(z => y.tensors(z)).toList
                val field = onnxHelper.onnxTensorProtoToArray(
                  tensorList.asInstanceOf[TensorProto]
                )
                field match {
                  case array: Array[_] =>
                    y.name.getOrElse("none") + " = Some((Array(" + array.mkString(",") + ")))"

                }
              }

            val opName = x._1._1._2

            val opInputsNames = (0 until schemaMap(opName.getOrElse("none"))._1.size.toInt).map { b =>
              schemaMap(opName.getOrElse("none"))._1.get(b).GetName.getString
            }

            val opInputsIsVariadic =
              (0 until schemaMap(opName.getOrElse("none"))._1.size.toInt).map { b =>
                schemaMap(opName.getOrElse("none"))._1.get(b).GetOption === 2
              }

            val sinceVersion = schemaMap(opName.getOrElse("none"))._2.toString

            val groupedNodesOrParams: Array[String] = nodesOrParams.take(
              opInputsNames.size - 1
            ) ++ Seq(nodesOrParams.drop(opInputsNames.size - 1).mkString(","))

            val opInputs = (opInputsNames zip opInputsIsVariadic) zip groupedNodesOrParams

            val namedNodesOrParams = opInputs
              .filter(t => !t._2.equals(""))
              .map(t =>
                t._1._1
                  .replaceAll("var", "someVar")
                  .replaceAll("shape", "shapeInput") + " = " + (if (t._1._2)
                                                                  t._2
                                                                    .replaceFirst(
                                                                      "Some",
                                                                      "Seq(Some"
                                                                    )
                                                                    + ")"
                                                                else t._2)
              )

            val nodeName = x._1._2(0)

            "      node" + nodeName.replaceAll("\\.", "") + " <- " + (if (useZIO)
                                                                        ""
                                                                      else
                                                                        "List(") + opName + (if (
                                                                                               useZIO
                                                                                             )
                                                                                               "ZIO"
                                                                                             else
                                                                                               "") + "." + opName + sinceVersion + (if (
                                                                                                                                      useZIO
                                                                                                                                    )
                                                                                                                                      "ZIO"
                                                                                                                                    else
                                                                                                                                      "") +
              (if (nodeName.contains("output")) "[" + graphOutputType + "]"
               else "") +
              "(" +
              """"""" + nodeName + """" """ + //assumes > 0 args
              (if (tensorProtoField.size > 0) "," else "") +
              tensorProtoField.mkString(",") +
              (if (tensorProtoFields.size > 0) "," else "") +
              tensorProtoFields.mkString(",") +
              (if (longListFields.size > 0) "," else "") +
              longListFields.mkString(",") +
              (if (stringFields.size > 0) "," else "") +
              stringFields.mkString(",") +
              (if (longFields.size > 0) "," else "") +
              longFields.mkString(",") +
              (if (namedNodesOrParams.size > 0) "," else "") +
              namedNodesOrParams.mkString(",") +
              ")" + (if (useZIO) ""
                     // "}"
                     else ")") + "\n"
          }
          .mkString("") +
        "    } yield (" +
        outputs.map(x => "node" + x).mkString(",") +
        ")\n" +
        "\n" +
        """  override def close(): Unit = {
    backend.close
    bytesDataSource.close
  }""" +
        "\n" +
        "}\n"
    }
//pw.write("for {\n")

    def generate() = {
      println(fullSource)
      //Seems to not catch some things it should
      val onnxSource = fullSource.parse[Source].get

      Files.write(path, onnxSource.syntax.getBytes("UTF-8"));
    }

    generate()
  }
}
