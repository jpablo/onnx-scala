package org.emergentorder.onnx

import spire.math.UByte
import spire.math.UShort
import spire.math.UInt
import spire.math.ULong
import spire.math.Complex
import spire.math.Numeric
import io.kjaer.compiletime._


import org.emergentorder.compiletime.DimensionDenotation
import org.emergentorder.compiletime.TensorShapeDenotation
import org.emergentorder.compiletime.tensorShapeDenotationOf
import org.emergentorder.compiletime.TSNil
import org.emergentorder.compiletime.##:

object Tensors{

  type Supported = Int | Long | Float | Double | Byte | Short | UByte | UShort | UInt | ULong | 
                   Boolean | String | Float16 | Complex[Float] | Complex[Double]

  type TensorTypeDenotation = String & Singleton

//  case class Axes(ttd: TensorTypeDenotation,td: TensorShapeDenotation, shape: Shape)
  type Axes = Tuple3[TensorTypeDenotation, TensorShapeDenotation, Shape]


  //Need this alias to not conflict with other Tensors
  opaque type Tensor[T <: Supported, Ax <: Axes] = Tuple2[Array[T], Ax]

  type SparseTensor[T <: Supported, A <: Axes] = Tensor[T, A]
 
  type KeepOrReduceDims[S <: Shape, Axis <: None.type | Indices, KeepDims <: (Boolean & Singleton)] <: Shape = (KeepDims) match {
        case true => S
        case false => Shape.Reduce[S, Axis]
  }

  type KeepOrReduceDimDenotations[Td <: TensorShapeDenotation, Axis <: None.type | Indices, KeepDims <: (Boolean & Singleton)] <: TensorShapeDenotation = (KeepDims) match {
        case true => Td
        case false => TensorShapeDenotation.Reduce[Td, Axis]
  }

  /*
  type ConcatLoop[ConcatFromA <: Shape, ConcatFromB <: Shape, ToConcat <: Indices, I <: Index] <: Shape = ConcatFromA match {
    case head #: tail => Indices.Contains[ConcatFromA, I] match {
      case true => ReduceLoop[tail, Indices.RemoveValue[ToRemove, I], S[I]]
      case false => head #: ReduceLoop[tail, ToRemove, S[I]]
    }
    case SNil => ToConcat match {
      case INil => SNil
      //     case head :: tail => Error[
      //         "The following indices are out of bounds: " + Indices.ToString[ToRemove]
      //     ]
    }
  }
*/

  //TODO: shapes to longs
  //TODO: Ensure denotation size matches shape size
  //TODO: random nd access
  //TODO: opaque
  //TODO: Benchmark Array[T] vs ArraySeq[T] vs IArray[T]
  //type OSTensor[T <: Supported, Ax <: Axes] = Tuple2[Array[T], Ax]

  object Tensor {
    extension[T <: Supported,  Tt <: TensorTypeDenotation, Td <: TensorShapeDenotation, S <: Shape](tens: Tensor[T,Tuple3[Tt, Td, S]]) def data = tens._1
//    def apply[T <: Supported] (elem: T): OSTensor[T, Scalar] = new OSTensor[T, ?](Array(elem), Scalar())

    extension[T <: Supported, Tt <: TensorTypeDenotation, Td <: TensorShapeDenotation, S <: Shape](tens: Tensor[T,Tuple3[Tt, Td, S]]) def shape: Array[Int] = tens._2._3.toSeq.toArray 
 
  //TODO: enforce tensor shape denotation size matches shape size
  def tensorRequires[T <: Supported,  Tt <: TensorTypeDenotation, Td <: TensorShapeDenotation, S <: Shape](tens: Tensor[T,Tuple3[Tt,Td,S]]): Tensor[T,Tuple3[Tt, Td, S]] = {
    require(tens.shape.size <= 4)
    require(tens.data.size == tens.shape.foldLeft(1)(_ * _))
    tens
  }
//    def apply[T <: Supported, Tt <: TensorTypeDenotation](element: T, tt: Tt): Tensor[T, Tuple3[Tt, org.emergentorder.compiletime.TSNil, SNil]] = tensorRequires((Array(element), (tt, org.emergentorder.compiletime.TSNil, SNil))) 

    def apply[T <: Supported, Tt <: TensorTypeDenotation, TD <: TensorShapeDenotation, S <: Shape](arr: Array[T], tt0: Tt, td0: TD, d0: S): Tensor[T, Tuple3[Tt, TD, S]] = tensorRequires((arr, (tt0, td0, d0)))


//    def apply[T <: Supported](element: T): Tensor[T, Tuple3["", org.emergentorder.compiletime.TSNil, SNil]] = tensorRequires((Array(element), ("", org.emergentorder.compiletime.TSNil, SNil))) 

    def apply[T <: Supported, TD <: TensorShapeDenotation, S <: Shape](arr: Array[T], td0: TD, d0: S): Tensor[T, Tuple3["", TD, S]] = tensorRequires((arr, ("", td0, d0)))

    def apply[T <: Supported, S <: Shape](arr: Array[T], d0: S): Tensor[T, Tuple3["", "" ##: TSNil, S]] = tensorRequires((arr, ("", "" ##: TSNil, d0)))

  }
}
