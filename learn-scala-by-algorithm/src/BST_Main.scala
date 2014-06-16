import tree.BST

object BST_Main{
 def main(args: Array[String]) {
    val bst = BST(Array(1,7,4,10,6))                   
    bst.printTree 
    println
    bst.delete(bst.findRecursivly(null, bst.root, 7))
    bst.printTree
  }
}