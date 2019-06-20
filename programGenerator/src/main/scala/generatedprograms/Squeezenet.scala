package org.emergentorder.onnx

import org.emergentorder.onnx.backends._
import org.emergentorder.union.UnionType._
import scala.reflect.ClassTag
import spire.implicits._
import spire.math.UByte
import spire.math.UShort
import spire.math.Complex
import spire.algebra.Field
import spire.math.Numeric
import scala.language.higherKinds

trait Squeezenet {
  val onnxHelper = new ONNXHelper("squeezenet.onnx")
  val Conv: Conv = new NGraphBackend(onnxHelper)
  val Relu: Relu = new NGraphBackend(onnxHelper)
  val MaxPool: MaxPool = new NGraphBackend(onnxHelper)
  val Concat: Concat = new NGraphBackend(onnxHelper)
  val Dropout: Dropout = new NGraphBackend(onnxHelper)
  val GlobalAveragePool: GlobalAveragePool = new NGraphBackend(onnxHelper)
  val Softmax: Softmax = new NGraphBackend(onnxHelper)
  val dataSource: DataSource = new NGraphBackend(onnxHelper)
  def program(inputDatadata_0: Tensor[Float]): List[Tensor[Float]] =
    for {
      nodedata_0 <- List(inputDatadata_0)
      nodeconv10_b_0 <- List(dataSource.getParams[Float]("conv10_b_0"))
      nodeconv10_w_0 <- List(dataSource.getParams[Float]("conv10_w_0"))
      nodeconv1_b_0 <- List(dataSource.getParams[Float]("conv1_b_0"))
      nodeconv1_w_0 <- List(dataSource.getParams[Float]("conv1_w_0"))
      nodefire2_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire2_expand1x1_b_0")
      )
      nodefire2_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire2_expand1x1_w_0")
      )
      nodefire2_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire2_expand3x3_b_0")
      )
      nodefire2_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire2_expand3x3_w_0")
      )
      nodefire2_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire2_squeeze1x1_b_0")
      )
      nodefire2_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire2_squeeze1x1_w_0")
      )
      nodefire3_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire3_expand1x1_b_0")
      )
      nodefire3_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire3_expand1x1_w_0")
      )
      nodefire3_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire3_expand3x3_b_0")
      )
      nodefire3_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire3_expand3x3_w_0")
      )
      nodefire3_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire3_squeeze1x1_b_0")
      )
      nodefire3_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire3_squeeze1x1_w_0")
      )
      nodefire4_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire4_expand1x1_b_0")
      )
      nodefire4_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire4_expand1x1_w_0")
      )
      nodefire4_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire4_expand3x3_b_0")
      )
      nodefire4_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire4_expand3x3_w_0")
      )
      nodefire4_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire4_squeeze1x1_b_0")
      )
      nodefire4_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire4_squeeze1x1_w_0")
      )
      nodefire5_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire5_expand1x1_b_0")
      )
      nodefire5_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire5_expand1x1_w_0")
      )
      nodefire5_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire5_expand3x3_b_0")
      )
      nodefire5_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire5_expand3x3_w_0")
      )
      nodefire5_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire5_squeeze1x1_b_0")
      )
      nodefire5_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire5_squeeze1x1_w_0")
      )
      nodefire6_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire6_expand1x1_b_0")
      )
      nodefire6_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire6_expand1x1_w_0")
      )
      nodefire6_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire6_expand3x3_b_0")
      )
      nodefire6_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire6_expand3x3_w_0")
      )
      nodefire6_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire6_squeeze1x1_b_0")
      )
      nodefire6_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire6_squeeze1x1_w_0")
      )
      nodefire7_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire7_expand1x1_b_0")
      )
      nodefire7_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire7_expand1x1_w_0")
      )
      nodefire7_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire7_expand3x3_b_0")
      )
      nodefire7_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire7_expand3x3_w_0")
      )
      nodefire7_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire7_squeeze1x1_b_0")
      )
      nodefire7_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire7_squeeze1x1_w_0")
      )
      nodefire8_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire8_expand1x1_b_0")
      )
      nodefire8_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire8_expand1x1_w_0")
      )
      nodefire8_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire8_expand3x3_b_0")
      )
      nodefire8_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire8_expand3x3_w_0")
      )
      nodefire8_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire8_squeeze1x1_b_0")
      )
      nodefire8_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire8_squeeze1x1_w_0")
      )
      nodefire9_expand1x1_b_0 <- List(
        dataSource.getParams[Float]("fire9_expand1x1_b_0")
      )
      nodefire9_expand1x1_w_0 <- List(
        dataSource.getParams[Float]("fire9_expand1x1_w_0")
      )
      nodefire9_expand3x3_b_0 <- List(
        dataSource.getParams[Float]("fire9_expand3x3_b_0")
      )
      nodefire9_expand3x3_w_0 <- List(
        dataSource.getParams[Float]("fire9_expand3x3_w_0")
      )
      nodefire9_squeeze1x1_b_0 <- List(
        dataSource.getParams[Float]("fire9_squeeze1x1_b_0")
      )
      nodefire9_squeeze1x1_w_0 <- List(
        dataSource.getParams[Float]("fire9_squeeze1x1_w_0")
      )
      nodeconv1_1 <- List(
        Conv.Conv1(
          "conv1_1",
          strides = Some((Array(2, 2))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodedata_0),
          W = Some(nodeconv1_w_0),
          B = Some(nodeconv1_b_0)
        )
      )
      nodeconv1_2 <- List(Relu.Relu6("conv1_2", X = Some(nodeconv1_1)))
      nodepool1_1 <- List(
        MaxPool.MaxPool1(
          "pool1_1",
          strides = Some((Array(2, 2))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodeconv1_2)
        )
      )
      nodefire2_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire2_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodepool1_1),
          W = Some(nodefire2_squeeze1x1_w_0),
          B = Some(nodefire2_squeeze1x1_b_0)
        )
      )
      nodefire2_squeeze1x1_2 <- List(
        Relu.Relu6("fire2_squeeze1x1_2", X = Some(nodefire2_squeeze1x1_1))
      )
      nodefire2_expand1x1_1 <- List(
        Conv.Conv1(
          "fire2_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire2_squeeze1x1_2),
          W = Some(nodefire2_expand1x1_w_0),
          B = Some(nodefire2_expand1x1_b_0)
        )
      )
      nodefire2_expand1x1_2 <- List(
        Relu.Relu6("fire2_expand1x1_2", X = Some(nodefire2_expand1x1_1))
      )
      nodefire2_expand3x3_1 <- List(
        Conv.Conv1(
          "fire2_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire2_squeeze1x1_2),
          W = Some(nodefire2_expand3x3_w_0),
          B = Some(nodefire2_expand3x3_b_0)
        )
      )
      nodefire2_expand3x3_2 <- List(
        Relu.Relu6("fire2_expand3x3_2", X = Some(nodefire2_expand3x3_1))
      )
      nodefire2_concat_1 <- List(
        Concat.Concat4(
          "fire2_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire2_expand1x1_2), Some(nodefire2_expand3x3_2))
        )
      )
      nodefire3_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire3_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire2_concat_1),
          W = Some(nodefire3_squeeze1x1_w_0),
          B = Some(nodefire3_squeeze1x1_b_0)
        )
      )
      nodefire3_squeeze1x1_2 <- List(
        Relu.Relu6("fire3_squeeze1x1_2", X = Some(nodefire3_squeeze1x1_1))
      )
      nodefire3_expand1x1_1 <- List(
        Conv.Conv1(
          "fire3_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire3_squeeze1x1_2),
          W = Some(nodefire3_expand1x1_w_0),
          B = Some(nodefire3_expand1x1_b_0)
        )
      )
      nodefire3_expand1x1_2 <- List(
        Relu.Relu6("fire3_expand1x1_2", X = Some(nodefire3_expand1x1_1))
      )
      nodefire3_expand3x3_1 <- List(
        Conv.Conv1(
          "fire3_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire3_squeeze1x1_2),
          W = Some(nodefire3_expand3x3_w_0),
          B = Some(nodefire3_expand3x3_b_0)
        )
      )
      nodefire3_expand3x3_2 <- List(
        Relu.Relu6("fire3_expand3x3_2", X = Some(nodefire3_expand3x3_1))
      )
      nodefire3_concat_1 <- List(
        Concat.Concat4(
          "fire3_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire3_expand1x1_2), Some(nodefire3_expand3x3_2))
        )
      )
      nodepool3_1 <- List(
        MaxPool.MaxPool1(
          "pool3_1",
          strides = Some((Array(2, 2))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire3_concat_1)
        )
      )
      nodefire4_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire4_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodepool3_1),
          W = Some(nodefire4_squeeze1x1_w_0),
          B = Some(nodefire4_squeeze1x1_b_0)
        )
      )
      nodefire4_squeeze1x1_2 <- List(
        Relu.Relu6("fire4_squeeze1x1_2", X = Some(nodefire4_squeeze1x1_1))
      )
      nodefire4_expand1x1_1 <- List(
        Conv.Conv1(
          "fire4_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire4_squeeze1x1_2),
          W = Some(nodefire4_expand1x1_w_0),
          B = Some(nodefire4_expand1x1_b_0)
        )
      )
      nodefire4_expand1x1_2 <- List(
        Relu.Relu6("fire4_expand1x1_2", X = Some(nodefire4_expand1x1_1))
      )
      nodefire4_expand3x3_1 <- List(
        Conv.Conv1(
          "fire4_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire4_squeeze1x1_2),
          W = Some(nodefire4_expand3x3_w_0),
          B = Some(nodefire4_expand3x3_b_0)
        )
      )
      nodefire4_expand3x3_2 <- List(
        Relu.Relu6("fire4_expand3x3_2", X = Some(nodefire4_expand3x3_1))
      )
      nodefire4_concat_1 <- List(
        Concat.Concat4(
          "fire4_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire4_expand1x1_2), Some(nodefire4_expand3x3_2))
        )
      )
      nodefire5_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire5_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire4_concat_1),
          W = Some(nodefire5_squeeze1x1_w_0),
          B = Some(nodefire5_squeeze1x1_b_0)
        )
      )
      nodefire5_squeeze1x1_2 <- List(
        Relu.Relu6("fire5_squeeze1x1_2", X = Some(nodefire5_squeeze1x1_1))
      )
      nodefire5_expand1x1_1 <- List(
        Conv.Conv1(
          "fire5_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire5_squeeze1x1_2),
          W = Some(nodefire5_expand1x1_w_0),
          B = Some(nodefire5_expand1x1_b_0)
        )
      )
      nodefire5_expand1x1_2 <- List(
        Relu.Relu6("fire5_expand1x1_2", X = Some(nodefire5_expand1x1_1))
      )
      nodefire5_expand3x3_1 <- List(
        Conv.Conv1(
          "fire5_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire5_squeeze1x1_2),
          W = Some(nodefire5_expand3x3_w_0),
          B = Some(nodefire5_expand3x3_b_0)
        )
      )
      nodefire5_expand3x3_2 <- List(
        Relu.Relu6("fire5_expand3x3_2", X = Some(nodefire5_expand3x3_1))
      )
      nodefire5_concat_1 <- List(
        Concat.Concat4(
          "fire5_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire5_expand1x1_2), Some(nodefire5_expand3x3_2))
        )
      )
      nodepool5_1 <- List(
        MaxPool.MaxPool1(
          "pool5_1",
          strides = Some((Array(2, 2))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire5_concat_1)
        )
      )
      nodefire6_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire6_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodepool5_1),
          W = Some(nodefire6_squeeze1x1_w_0),
          B = Some(nodefire6_squeeze1x1_b_0)
        )
      )
      nodefire6_squeeze1x1_2 <- List(
        Relu.Relu6("fire6_squeeze1x1_2", X = Some(nodefire6_squeeze1x1_1))
      )
      nodefire6_expand1x1_1 <- List(
        Conv.Conv1(
          "fire6_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire6_squeeze1x1_2),
          W = Some(nodefire6_expand1x1_w_0),
          B = Some(nodefire6_expand1x1_b_0)
        )
      )
      nodefire6_expand1x1_2 <- List(
        Relu.Relu6("fire6_expand1x1_2", X = Some(nodefire6_expand1x1_1))
      )
      nodefire6_expand3x3_1 <- List(
        Conv.Conv1(
          "fire6_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire6_squeeze1x1_2),
          W = Some(nodefire6_expand3x3_w_0),
          B = Some(nodefire6_expand3x3_b_0)
        )
      )
      nodefire6_expand3x3_2 <- List(
        Relu.Relu6("fire6_expand3x3_2", X = Some(nodefire6_expand3x3_1))
      )
      nodefire6_concat_1 <- List(
        Concat.Concat4(
          "fire6_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire6_expand1x1_2), Some(nodefire6_expand3x3_2))
        )
      )
      nodefire7_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire7_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire6_concat_1),
          W = Some(nodefire7_squeeze1x1_w_0),
          B = Some(nodefire7_squeeze1x1_b_0)
        )
      )
      nodefire7_squeeze1x1_2 <- List(
        Relu.Relu6("fire7_squeeze1x1_2", X = Some(nodefire7_squeeze1x1_1))
      )
      nodefire7_expand1x1_1 <- List(
        Conv.Conv1(
          "fire7_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire7_squeeze1x1_2),
          W = Some(nodefire7_expand1x1_w_0),
          B = Some(nodefire7_expand1x1_b_0)
        )
      )
      nodefire7_expand1x1_2 <- List(
        Relu.Relu6("fire7_expand1x1_2", X = Some(nodefire7_expand1x1_1))
      )
      nodefire7_expand3x3_1 <- List(
        Conv.Conv1(
          "fire7_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire7_squeeze1x1_2),
          W = Some(nodefire7_expand3x3_w_0),
          B = Some(nodefire7_expand3x3_b_0)
        )
      )
      nodefire7_expand3x3_2 <- List(
        Relu.Relu6("fire7_expand3x3_2", X = Some(nodefire7_expand3x3_1))
      )
      nodefire7_concat_1 <- List(
        Concat.Concat4(
          "fire7_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire7_expand1x1_2), Some(nodefire7_expand3x3_2))
        )
      )
      nodefire8_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire8_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire7_concat_1),
          W = Some(nodefire8_squeeze1x1_w_0),
          B = Some(nodefire8_squeeze1x1_b_0)
        )
      )
      nodefire8_squeeze1x1_2 <- List(
        Relu.Relu6("fire8_squeeze1x1_2", X = Some(nodefire8_squeeze1x1_1))
      )
      nodefire8_expand1x1_1 <- List(
        Conv.Conv1(
          "fire8_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire8_squeeze1x1_2),
          W = Some(nodefire8_expand1x1_w_0),
          B = Some(nodefire8_expand1x1_b_0)
        )
      )
      nodefire8_expand1x1_2 <- List(
        Relu.Relu6("fire8_expand1x1_2", X = Some(nodefire8_expand1x1_1))
      )
      nodefire8_expand3x3_1 <- List(
        Conv.Conv1(
          "fire8_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire8_squeeze1x1_2),
          W = Some(nodefire8_expand3x3_w_0),
          B = Some(nodefire8_expand3x3_b_0)
        )
      )
      nodefire8_expand3x3_2 <- List(
        Relu.Relu6("fire8_expand3x3_2", X = Some(nodefire8_expand3x3_1))
      )
      nodefire8_concat_1 <- List(
        Concat.Concat4(
          "fire8_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire8_expand1x1_2), Some(nodefire8_expand3x3_2))
        )
      )
      nodefire9_squeeze1x1_1 <- List(
        Conv.Conv1(
          "fire9_squeeze1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire8_concat_1),
          W = Some(nodefire9_squeeze1x1_w_0),
          B = Some(nodefire9_squeeze1x1_b_0)
        )
      )
      nodefire9_squeeze1x1_2 <- List(
        Relu.Relu6("fire9_squeeze1x1_2", X = Some(nodefire9_squeeze1x1_1))
      )
      nodefire9_expand1x1_1 <- List(
        Conv.Conv1(
          "fire9_expand1x1_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire9_squeeze1x1_2),
          W = Some(nodefire9_expand1x1_w_0),
          B = Some(nodefire9_expand1x1_b_0)
        )
      )
      nodefire9_expand1x1_2 <- List(
        Relu.Relu6("fire9_expand1x1_2", X = Some(nodefire9_expand1x1_1))
      )
      nodefire9_expand3x3_1 <- List(
        Conv.Conv1(
          "fire9_expand3x3_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(1, 1, 1, 1))),
          kernel_shape = Some((Array(3, 3))),
          X = Some(nodefire9_squeeze1x1_2),
          W = Some(nodefire9_expand3x3_w_0),
          B = Some(nodefire9_expand3x3_b_0)
        )
      )
      nodefire9_expand3x3_2 <- List(
        Relu.Relu6("fire9_expand3x3_2", X = Some(nodefire9_expand3x3_1))
      )
      nodefire9_concat_1 <- List(
        Concat.Concat4(
          "fire9_concat_1",
          axis = Some((1)),
          inputs = Seq(Some(nodefire9_expand1x1_2), Some(nodefire9_expand3x3_2))
        )
      )
      nodefire9_concat_2 <- List(
        Dropout.Dropout7("fire9_concat_2", data = Some(nodefire9_concat_1))
      )
      nodeconv10_1 <- List(
        Conv.Conv1(
          "conv10_1",
          strides = Some((Array(1, 1))),
          pads = Some((Array(0, 0, 0, 0))),
          kernel_shape = Some((Array(1, 1))),
          X = Some(nodefire9_concat_2._1),
          W = Some(nodeconv10_w_0),
          B = Some(nodeconv10_b_0)
        )
      )
      nodeconv10_2 <- List(Relu.Relu6("conv10_2", X = Some(nodeconv10_1)))
      nodepool10_1 <- List(
        GlobalAveragePool.GlobalAveragePool1("pool10_1", X = Some(nodeconv10_2))
      )
      nodesoftmaxout_1 <- List(
        Softmax.Softmax1("softmaxout_1", input = Some(nodepool10_1))
      )
    } yield (nodesoftmaxout_1)
}
