package Algorithm


trait UnionFind {
    def input: Array[Int]
    
    def connected(start:Int, end:Int) : Boolean
    def union(start:Int, end:Int) : UnionFind
    def printUF() = {
      println(input.deep.mkString(" "))
    }
}

// eagle method
trait QuickFindT extends UnionFind {
   
   override def connected(start:Int, end:Int) : Boolean = {
        input(start) == input(end)
   }
   
   override def union(start:Int, end:Int) : UnionFind = {
       val unionedElement = input(start)
       
       for ( i <- 0 to (input.length - 1)) {
          if (input(i) == unionedElement) {
              input(i) = input(end)
          }
       }
       
       print(f"Union $start - $end: ")
       printUF
       this
   }
    
}

trait QuickUnionT extends UnionFind {
   var size = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
   
   override def connected(start:Int, end:Int) : Boolean = {
        if (input(start) == input(end)) {
           true
        } else {
           connected(input(start), end)
        }
   }
   
   override def union(start:Int, end:Int) : UnionFind = {
       
       val i = findRoot(start)
       val j = findRoot(end)
       
       print(f" Union $start - $end: ")
       print(f" root[$start]: $i root[$end]: $j ")
       
       val size1 = size(i)
       val size2 = size(j)
       println(f"before union: size[$i]: $size1 size[$j]: $size2")
       
       //connect small tree to big tree
       if (i != j) {
	       if (size1 < size2) {
	            input(i) = j
	            size(j) += size1
	       } else {
	            input(j) = i
	            size(i) += size2
	       }
       }
       
       val size3 = size(i)
       val size4 = size(j)
       println(f"after union: size[$i]: $size3 size[$j]: $size4")
       printUF
       this
   }
   
   def findRoot(node:Int): Int = {
       if (input(node) == node) {
          node
       } else {
          findRoot(input(node))
       }
   }
}

case class QuickFind(input:Array[Int]) extends QuickFindT
case class QuickUnion(input:Array[Int]) extends QuickUnionT

object UF {
  
   def quickFind(input:Array[Int]):UnionFind = {
       new QuickFind(input)
   }
   
   def quickUnion(input:Array[Int]):UnionFind = {
      new QuickUnion(input)
   }
  
}