public class Test{
  /*
  Judith Dick jdick 215045081
  Eric Marcantonio ericmarc 216206393
  Muhammad Haris harism91  216359770
   */
  public static void main(String[] args)throws Exception
  {
    int testPart = Integer.parseInt(args[0]);
    
    Assignment1 a1 = new Assignment1();
    int n = 8;
    int[][] matrix;
    
    if(testPart==1){
      matrix=a1.initMatrix(n);
      a1.printMatrix(n, matrix);
    }
    else if(testPart==2){
      matrix=a1.readMatrix("matrix1.txt",n);
      a1.printMatrix(n,matrix);
    }
    else if(testPart==3){
      int nextPart = Integer.parseInt(args[1]);
      if(nextPart==1){
        int[][] matrix1=a1.readMatrix("matrix1.txt",n);
        int[][] matrix2=a1.readMatrix("matrix2.txt",n);
        int[][] sumMatrix = a1.sum(matrix1, matrix2, 1, 1, 0, 1, 3);
        a1.printMatrix(n,matrix1);
        a1.printMatrix(n,matrix2);
        a1.printMatrix(3,sumMatrix);
      }
      else{
        int[][] matrix1=a1.readMatrix("matrix1.txt",n);
        int[][] matrix2=a1.readMatrix("matrix2.txt",n);
        int[][] sumMatrix = a1.sub(matrix1, matrix2, 1, 1, 0, 1, 3);
        a1.printMatrix(n,matrix1);
        a1.printMatrix(n,matrix2);
        a1.printMatrix(3,sumMatrix);
      }
    }
    else{
      int[][] matrix1=a1.readMatrix("matrix1.txt",n);
      int[][] matrix2=a1.readMatrix("matrix2.txt",n);
      int[][] resultingMatrix=a1.denseMatrixMult(matrix1, matrix2, n);
      a1.printMatrix(n,resultingMatrix);
    }
  }
}