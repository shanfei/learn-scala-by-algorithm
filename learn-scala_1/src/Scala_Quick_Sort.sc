object Scala_Quick_Sort {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def quickSort(xs:Array[Int]): Array[Int] = {
     if (xs.length <= 1) xs
     else {
         val pivot = xs(xs.length / 2)
         Array.concat(
            quickSort(xs filter (pivot > _)),
            xs filter (pivot == _),
            quickSort(xs filter (pivot < _))
         )
     }
  }                                               //> quickSort: (xs: Array[Int])Array[Int]
  
  val content = Array(2,1,100,7,9,10)             //> content  : Array[Int] = Array(2, 1, 100, 7, 9, 10)
  quickSort(content)                              //> res0: Array[Int] = Array(1, 2, 7, 9, 10, 100)
  
  implicit def compare(x:Int, y:Int) = {
    x < y
  }                                               //> compare: (x: Int, y: Int)Boolean
  
  def quickSort2(xs:Array[Int], low:Int, high:Int)(implicit compare: (Int, Int) => Boolean) : Unit = {
  
      if (low >= high) {
         return
      }
      
      def swap(xs:Array[Int], i:Int, j:Int) = {
         val t = xs(i); xs(i) = xs(j); xs(j) = t;
      }
      
      var pivotIndex = high;
      var i = low;
      var j = high - 1 ;
      while (i < j) {
         
        while (i < high && i < j && compare(xs(i), xs(pivotIndex))) {
            i += 1;
        }
        
        while (j > low && i < j && compare(xs(pivotIndex), xs(j))) {
             j -= 1;
        }
         
         swap(xs, i, j)
      }
     
      //exange pivotIndex with j
      swap(xs, j, pivotIndex)
      
      println(xs.deep)
     
      quickSort2(xs, low, j - 1)(compare)
      quickSort2(xs, j + 1, high)(compare)
  }                                               //> quickSort2: (xs: Array[Int], low: Int, high: Int)(implicit compare: (Int, I
                                                  //| nt) => Boolean)Unit
  
  val content2 = Array(2,1,100,7,9,10)            //> content2  : Array[Int] = Array(2, 1, 100, 7, 9, 10)
  quickSort2(content2, 0, content.length - 1)     //> Array(2, 1, 9, 7, 10, 100)
                                                  //| Array(2, 1, 7, 9, 10, 100)
                                                  //| Array(1, 2, 7, 9, 10, 100)
  content2                                        //> res1: Array[Int] = Array(1, 2, 7, 9, 10, 100)
}