import tree.BST

object test_array {
  val bst = tree.BST(Array(1))                    //> insert into tree:BSTNode(null,null,1)
                                                  //| bst  : tree.BST = tree.BST@786c730
  bst.printTree                                   //> BSTNode(null,null,1)
                                                  //| 1 null
                                                  //| null
}