package task

import java.util.NoSuchElementException

import scala.collection.immutable._
import scala.collection.mutable


/**
 * Created by daria on 18.12.14.
 */
class NGraph[T](val adj: Map[T, Set[T]]) extends Graph[T] {

  def dfs(v: T): scala.collection.mutable.HashMap[T, T] = {
    def dfsHelp(v: T,
                marked: scala.collection.mutable.HashMap[T, Boolean],
                edgeTo: scala.collection.mutable.HashMap[T, T]): scala.collection.mutable.HashMap[T, T] = {
      marked.put(v, true)
      for (w <- getNodes(v)) {
        if (!marked.getOrElse(w, false)) {
          edgeTo.put(w, v)
          dfsHelp(w, marked, edgeTo)
        }
      }

      edgeTo
    }

    dfsHelp(v, new scala.collection.mutable.HashMap[T, Boolean], new scala.collection.mutable.HashMap[T, T])
  }

  def bfs(v: T): scala.collection.mutable.HashMap[T, T] = {
    def bfsHelp(v: T,
                marked: scala.collection.mutable.HashMap[T, Boolean],
                edgeTo: scala.collection.mutable.HashMap[T, T]): scala.collection.mutable.HashMap[T, T] = {
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

      edgeTo
    }

    bfsHelp(v, new scala.collection.mutable.HashMap[T, Boolean], new scala.collection.mutable.HashMap[T, T])
  }

  def search(from: T, to: T, f: T => scala.collection.mutable.HashMap[T, T]) = {
    if (!(adj.contains(from) && adj.contains(to))) {
      throw new NoSuchElementException("no such element")
    }

    val edgeTo = f(from)

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

  def depthSearch(from: T, to: T) = search(from, to, dfs)

  def broadSearch(from: T, to: T) = search(from, to, bfs)

  def getNodes(key: T): Set[T] = adj(key)

}
