package task

/**
  * Created by daria on 18.12.14.
  */
object Main {
   def main (args: Array[String]) {
     val initGraph = Map(0 -> Set(2, 1, 5), 1 -> Set(0, 2), 2 -> Set(1, 0, 3, 4), 3 -> Set(2, 4, 5), 4 -> Set(2, 3), 5 -> Set(0, 3))
     val graph = new NGraph[Int](initGraph)
     /*
     * Поиск в глубину не подразумевает нахождение кратчайшего пути
     * так как его результат зависит от того каким образом представлено множество смежности для каждой вершины
     * Таким образом тест кейсы для поиска в глубину
     * Graph_1: Map(0 -> Set(2, 1, 5), 1 -> Set(0, 2), 2 -> Set(1, 0, 3, 4), 3 -> Set(2, 4, 5), 4 -> Set(2, 3), 5 -> Set(0, 3))
     * from 0 to 5: 0-2-3-5
     * from 0 to 4: 0-2-3-4
     * from 0 to 3: 0-2-3
     * from 0 to 2: 0-2
     * from 0 to 1: 0-2-1
     * Graph_2: Map(0 -> Set(5, 1, 2), 1 -> Set(0, 2), 2 -> Set(1, 0, 3, 4), 3 -> Set(2, 4, 5), 4 -> Set(2, 3), 5 -> Set(0, 3))
     * в этом тесте для исходного графа другой порядок в множесте смежности для вершины 0
     * from 0 to 5: 0-5
     * from 0 to 4: 0-5-3-2-4
     * from 0 to 3: 0-5-3
     * from 0 to 2: 0-5-3-2
     * from 0 to 1: 0-5-3-2-1
     * */
     val dfrom = 0
     val dto = 5
     val testDepthSearch = graph.depthSearch(dfrom, dto).toList.reverse
     print("Path from " + dfrom + " to " + dto + " by depth search: ")
     for (i <- testDepthSearch) {
       print(i + " ")
     }

     /* Поиск в ширину позволяет найти кратчайший путь от вершины к вершине
     * и не зависит от порядка элементов в множестве смежности вершины графа
     * Тест кейс
     * Graph: Map(0 -> Set(2, 1, 5), 1 -> Set(0, 2), 2 -> Set(1, 0, 3, 4), 3 -> Set(2, 4, 5), 4 -> Set(2, 3), 5 -> Set(0, 3))
     * from 0 to 5: 0-5
     * from 0 to 4: 0-2-4
     * from 0 to 3: 0-2-3
     * from 0 to 2: 0-2
     * from 0 to 1: 0-1
     * */

     print("\n")

     val bfrom = 0
     val bto = 5
     val testBroadSearch = graph.broadSearch(bfrom, bto).toList.reverse
     print("Path from " + bfrom + " to " + bto + " by broad search: ")
     for (i <- testBroadSearch) {
       print(i + " ")
     }
   }
 }
