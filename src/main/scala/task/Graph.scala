package task

/**
  * Created by daria on 18.12.14.
  */
trait Graph[T] {

   def depthSearch(from: T, to: T): scala.collection.mutable.Set[T]
   def broadSearch(from: T, to: T): scala.collection.mutable.Set[T]

 }
