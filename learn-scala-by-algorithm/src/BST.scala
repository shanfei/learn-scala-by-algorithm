package tree

trait TreeOps[T,L] {
    var root:T
    def insert(node: T)(compare: (L,L) => Boolean)
    //find the largest node smaller than the parameter
    def delete(node: (T,T))
    def find(value:L):(T,T)
    def findRecursivly(parent:T, current:T, value:L):(T,T)
    def printTree();
}

case class BSTNode (var value: Int, var leftChild: BSTNode, var rightChild: BSTNode, var parent:BSTNode)

class BST extends TreeOps[BSTNode,Int] {
  
  var root:BSTNode = null;
  
  override def insert(node:BSTNode)(compare: (Int,Int) => Boolean)  = { 
     println("insert into tree:" + node);
     if (root == null) {
        root = node
     } else {
        _insert(root,node)(compare)
     }
  }
  
  override def delete(nodes: (BSTNode, BSTNode)) = {
      val node = nodes._2
      if (_isLeaf(node)) {
         _compareAndDelete(nodes)
      } else {
         var candidate_nodes = _findLargestSmallNode(node)
         node.value = candidate_nodes._2.value
         _compareAndDelete(candidate_nodes)
      }
  }
  
  override def find(value: Int) : (BSTNode,BSTNode) = {
      var current = root
      var parent: BSTNode = null
      while (current.value != value) {
         parent = current
         if (current.value > value) {
           current = current.leftChild 
         } else {
           current = current.rightChild
         }
      }
      (parent,current)
  }
  
  override def findRecursivly(parent:BSTNode, current:BSTNode, value: Int) : (BSTNode,BSTNode) = {
     if (current.value == value) {
       (parent,current)
     } else if (current.value > value) {
       findRecursivly(current, current.leftChild, value)
     } else {
       findRecursivly(current,current.rightChild, value)
     }
     
  }
  
  
  
  def _compareAndDelete(nodes: (BSTNode, BSTNode)) = {
     val parentNode = nodes._1
     val childNode = nodes._2
     if (parentNode  == null) {
            root = null
         } else if (parentNode.value > childNode.value) {
           parentNode.leftChild = null;
         } else {
           parentNode.rightChild = null;
         }
  }
  
  def _findLargestSmallNode(node:BSTNode):(BSTNode, BSTNode) = {
    var currentNode = node.leftChild  
    var parentNode = node
    while (! _isLeaf(currentNode)) {
       parentNode = currentNode
       currentNode = currentNode.rightChild 
    }
    (parentNode,currentNode)
  }
  
  def _isLeaf(node: BSTNode) : Boolean = {
     node.leftChild  == null && node.rightChild == null
  }
  
  override def printTree() {
     _travseInorder(root)(_printNode)
  }
  
  def _printNode(node:BSTNode) {
     Option(node) match {
       case Some(node) => {
          print(node.value + " ");
       }
     }
  }
  def _travseInorder(node:BSTNode)(ops:(BSTNode) => Unit) {
       
       
       Option(node) match {
         case Some(node) => {
           _travseInorder(node.leftChild)(ops);
           ops(node);
           _travseInorder(node.rightChild)(ops);
         }
         case None => {
           
         }
       }
       
  } 
  
  def _insert(parent: BSTNode, node: BSTNode)(compare: (Int,Int) => Boolean) : Unit = {
      Option(parent) match {
        case Some(parent) => {
          compare(parent.value, node.value) match {
	        case true => {
	          if (Option(parent.rightChild) == None) {
	            parent.rightChild = node
	            node.parent = parent
	          } else {
	             _insert(parent.rightChild, node)(compare)
	          }
	        }
	        case false => {
	          if (Option(parent.leftChild) == None) {
	             parent.leftChild = node
	             node.parent = parent
	          } else {
	              _insert(parent.leftChild, node)(compare)
	          }
	          
	        }
	      }
        }
      }   
  }
}  

object BST {
    def less(x:Int, y:Int) : Boolean = {
      x < y
    }
    def apply(nodes: Array[Int]) = {
        val bst:BST = new BST();
        for (node_value <- nodes) {
           val node = new BSTNode(node_value, null, null, null)
           bst.insert(node)(less)
        }
        bst
    }
}






