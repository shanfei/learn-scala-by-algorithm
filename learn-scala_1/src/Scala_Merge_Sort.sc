object Scala_Merge_Sort {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val content = List(3,7,6,9,10);                 //> content  : List[Int] = List(3, 7, 6, 9, 10)
  
  def compare(x:Int, y:Int) : Boolean = {
     x < y
  }                                               //> compare: (x: Int, y: Int)Boolean
  
  def mergeSort[T](less: (T,T) => Boolean)(xs:List[T]):List[T] = {
       def merge(xs:List[T], ys:List[T], acc:List[T]) : List[T] = {
          (xs, ys) match {
              case (Nil, _) => ys.reverse ::: acc
              case (_, Nil) => xs.reverse ::: acc
              case (x::xs1, y::ys1) =>
                 if (less(x, y)) {
                     merge(xs1, ys, x::acc)
                 } else {
                     merge(xs, ys1, y::acc)
                 }
          }
       }
       
       val n = xs.length / 2
       if (n == 0) xs
       else {
          val (ys, zs) = xs splitAt n
          merge(mergeSort(less)(ys), mergeSort(less)(zs), Nil).reverse
       }
    }                                             //> mergeSort: [T](less: (T, T) => Boolean)(xs: List[T])List[T]
    
    println(mergeSort[Int](compare)(content))     //> List(3, 6, 7, 9, 10)
}