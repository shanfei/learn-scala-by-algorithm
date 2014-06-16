package Algorithm

object UnionFind_Main {
    def main(args: Array[String]) {
        val input = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val quickFind = UF.quickFind(input);
        
        // 6-0 8-3 6-7 7-5 0-9 4-5
        quickFind.union(6, 0)
                 .union(8, 3)
                 .union(6, 7)
                 .union(7, 5)
                 .union(0, 9)
                 .union(4, 5);
        
    
        println("Expected Result: 9 1 2 3 9 9 9 9 3 9 ")
        println("-------------------------------------")
        print("Actual Result: ")
        quickFind.printUF
        
        println
        
        val input2 = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val quickFind2 = UF.quickFind(input2);
        
        //  2-7 9-0 4-8 5-4 5-9 2-0 
        quickFind2.union(2, 7)
                 .union(9, 0)
                 .union(4, 8)
                 .union(5, 4)
                 .union(5, 9)
                 .union(2, 0);
        
       
        println("Expected Result: 0 1 0 3 0 0 6 0 0 0 ")
        println("-------------------------------------")
        print("Actual Result: ")
        quickFind2.printUF
        
        println
        
        val input3 = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val quickUnion = UF.quickUnion(input3)
        
        // 1-8 7-6 6-4 2-5 9-6 1-2 7-3 7-5 0-2
        quickUnion.union(1, 8)
                  .union(7, 6)
                  .union(6, 4)
                  .union(2, 5)
                  .union(9, 6)
                  .union(1, 2)
                  .union(7, 3)
                  .union(7, 5)
                  .union(0, 2)
//        
        println("Expected Result: 7 7 1 7 7 2 7 7 1 7 ")
        println("-------------------------------------")
        print("Actual Result: ")       
        quickUnion.printUF
      
    }
}


