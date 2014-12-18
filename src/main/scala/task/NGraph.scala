package task

import scala.collection.immutable._
import scala.collection.mutable


/**
 * Created by daria on 18.12.14.
 */
class NGraph[T](val adj: Map[T, Set[T]]) extends Graph[T] {

  def depthSearch(from: T, to: T): scala.collection.mutable.Set[T] = {
    val marked = new scala.collection.mutable.HashMap[T, Boolean]
    val edgeTo = new scala.collection.mutable.HashMap[T, T]
    dfs(from)

    def dfs(v: T): Unit = {
      marked.put(v, true)
      for (w <- getNodes(v)) {
        if (!marked.getOrElse(w, false)) {
          edgeTo.put(w, v)
          dfs(w)
        }
      }
    }

    def pathTo(to: T): scala.collection.mutable.Set[T] = {
      val result = scala.collection.mutable.LinkedHashSet[T]()
      var x = to
      while (x != from) {
        result += x
        x = edgeTo(x)
      }
      result += from
      result
    }

    pathTo(to)
  }


  def broadSearch(from: T, to: T): scala.collection.mutable.Set[T] = {
    val marked = new scala.collection.mutable.HashMap[T, Boolean]
    val edgeTo = new scala.collection.mutable.HashMap[T, T]
    bfs(from)

    def bfs(v: T): Unit = {
      val q = new mutable.Queue[T]()
      marked.put(v, true)
      q.enqueue(v)
      while (!q.isEmpty) {
        val elem = q.dequeue()
        for (w <- getNodes(elem)) {
          if (!marked.getOrElse(w, false)) {
            edgeTo.put(w, elem)
            marked.put(w, true)
            q.enqueue(w)
          }
        }
      }
    }

    def pathTo(to: T): scala.collection.mutable.Set[T] = {
      val result = scala.collection.mutable.LinkedHashSet[T]()
      var x = to
      while (x != from) {
        result += x
        x = edgeTo(x)
      }
      result += from
      result
    }

    pathTo(to)
  }

  def getNodes(key: T): Set[T] = adj(key)

}
