import java.io.*;
import java.util.Scanner;

public class Assignment1 {
    /**
     *
     * @param A
     * @param B
     * @param n
     * @return
     */
    public int[][] denseMatrixMult(int[][] A, int[][] B, int n) {

        int[][] C = initMatrix(n);

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {

            // Split the arrays into quarters
            /*
             * A0 A1 A2 A3
             *
             * B0 B1 B2 B3
             */

            /*
             * I think this is works, the idea for the singles is to add a blank array to
             * whatever part of the array, I just need to figure out where to put the n/2, I
             * am not sure if it happens each time. I will most likely get a array out of
             * bound exception
             *
             */
            /*
             * M 0 = (A 0,0 + A 1,1 )(B 0,0 + B 1,1 ) 
             * M 1 = (A 1,0 + A 1,1 )B 0,0 
             * M 2 = A 0,0 (B 0,1 − B 1,1 ) 
             * M 3 = A 1,1 (B 1,0 − B 0,0 ) 
             * M 4 = (A 0,0 + A 0,1 )B 1,1
             * M 5 = (A 1,0 − A 0,0 )(B 0,0 + B 0,1 ) 
             * M 6 = (A 0,1 − A 1,1 )(B 1,0 + B 1,1 )
             */
            // int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, n / 2, n / 2, n / 2), sum(B, B,
            // 0, 0, n / 2, n / 2, n / 2), n / 2);
            // int[][] M1 = denseMatrixMult(sum(A, A, 0, n / 2, n / 2, n / 2, n / 2), sum(B,
            // initMatrix(n / 2), 0, 0, 0, 0, n / 2), n / 2);
            // int[][] M2 = denseMatrixMult(sum(A, initMatrix(n), 0, 0, 0, 0, n / 2), sub(B,
            // B, n / 2, 0, n / 2, n / 2, n / 2), n / 2);
            // int[][] M3 = denseMatrixMult(sub(B, B, 0, n / 2, 0, 0, n / 2), sum(A,
            // initMatrix(n), n / 2, n / 2, n / 2, n / 2, n / 2), n / 2);
            // int[][] M4 = denseMatrixMult(sum(A, A, 0, 0, n / 2, 0, n / 2), sum(B, B, n /
            // 2, n / 2, n / 2, n / 2, n / 2), n/2);
            // int[][] M5 = denseMatrixMult(sub(A, A, 0, n/2, 0,0,n/2), sum(B, B, 0,0, n/2,
            // 0, n/2), n/2);
            // int[][] M6 = denseMatrixMult(sub(A, A, n/2, 0, n/2,n/2, n/2), sum(B,B, 0,
            // n/2, n/2, n/2, n/2), n/2);

            // sorry it's late now and I cant figure out how to sum quadrant (1,0) vs (0,1)
            int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, n / 2, n / 2, n / 2), sum(B, B, 0, 0, n / 2, n / 2, n / 2),
                    n / 2);
            int[][] M1 = denseMatrixMult(sum(A, A, n / 2, 0, n / 2, n / 2, n / 2),
                    sum(B, initMatrix(n / 2), 0, 0, 0, 0, n / 2), n / 2);
            int[][] M2 = denseMatrixMult(sum(A, initMatrix(n), 0, 0, 0, 0, n / 2),
                    sub(B, B, 0, n / 2, n / 2, n / 2, n / 2), n / 2);
            int[][] M3 = denseMatrixMult(
                    sum(A, initMatrix(n), n / 2, n / 2, n / 2, n / 2, n / 2), sub(B, B, n / 2, 0, 0, 0, n / 2), n / 2);
            int[][] M4 = denseMatrixMult(sum(A, A, 0, 0, 0, n / 2, n / 2),
                    sum(B, initMatrix(n / 2), n / 2, n / 2, 0, 0, n / 2), n / 2);
            int[][] M5 = denseMatrixMult(sub(A, A, n / 2, 0, 0, 0, n / 2), sum(B, B, 0, 0, 0, n / 2, n / 2), n / 2);
            int[][] M6 = denseMatrixMult(sub(A, A, 0, n / 2, n / 2, n / 2, n / 2),
                    sum(B, B, n / 2, 0, n / 2, n / 2, n / 2), n / 2);

            // C0 C1
            // C2 C3

            // C0,0 = M0 + M3 − M4 + M6
            // man this is some ugly subtraction/addition
            int[][] C0 = sum(sub(sum(M0, M3, 0, 0, 0, 0, n / 2), M4, 0, 0, 0, 0, n / 2), M6, 0, 0, 0, 0, n / 2);
            // C0,1 = M2 + M4
            int[][] C1 = sum(M2, M4, 0, 0, 0, 0, n / 2);
            // C1,0 = M1 + M3
            int[][] C2 = sum(M1, M3, 0, 0, 0, 0, n / 2);
            // C1,1 = M0 − M1 + M2 + M5
            int[][] C3 = sum(sum(sub(M0, M1, 0, 0, 0, 0, n / 2), M2, 0, 0, 0, 0, n / 2), M5, 0, 0, 0, 0, n / 2);

            // now, combine the four C quadrants
            // quadrant 0
            for (int row = 0; row < n / 2; row++) {
                for (int col = 0; col < n / 2; col++) {
                    C[row][col] = C0[row][col];
                }
            }

            // quadrant 1
            for (int row = 0; row < n / 2; row++) {
                for (int col = 0; col < n / 2; col++) {
                    C[row][col + n / 2] = C1[row][col];
                }
            }

            // quadrant 2
            for (int row = 0; row < n / 2; row++) {
                for (int col = 0; col < n / 2; col++) {
                    C[row + n / 2][col] = C2[row][col];
                }
            }

            // quadrant 3
            for (int row = 0; row < n / 2; row++) {
                for (int col = 0; col < n / 2; col++) {
                    C[row + n / 2][col + n / 2] = C3[row][col];
                }
            }
        }

        return C;
    }

    /**
     * @param A
     * @param B
     * @param xa
     * @param ya
     * @param xb
     * @param yb
     * @param n
     * @return
     */
    // The test function only works when x=row and y=col (unlike the usual cartesean
    // plane where C[y][x])
    public int[][] sum(int[][] A, int[][] B, int xa, int ya, int xb, int yb, int n) {
        int[][] C = initMatrix(n);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                C[x][y] = A[x + xa][y + ya] + B[x + xb][y + yb];
            }
        }
        return C;
    }

    /**
     * I changed the variable names becasue they did not match the assignment. x1 is
     * misleading, its actually xa
     *
     * @param A
     * @param B
     * @param xa
     * @param ya
     * @param xb
     * @param yb
     * @param n
     * @return
     */
    public int[][] sub(int[][] A, int[][] B, int xa, int ya, int xb, int yb, int n) {
        int[][] C = initMatrix(n);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                C[x][y] = A[x + xa][y + ya] - B[x + xb][y + yb];
            }
        }
        return C;
    }

    /**
     * @param n
     * @return
     */
    public int[][] initMatrix(int n) {
        int[][] A = new int[n][n];
        return A;
    }

    /**
     * @param n
     * @param A
     */
    public void printMatrix(int n, int[][] A) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                System.out.printf("%d ", A[row][col]);
            }
            System.out.print("\n");
        }
    }

    /**
     * @param filename
     * @param n
     * @return
     * @throws Exception
     */
    public int[][] readMatrix(String filename, int n) throws Exception {
        // basic syntax int[row][column]
        int[][] IntMatrix = initMatrix(n);

        File FileMatrix = new File(filename);
        Scanner scanner = new Scanner(FileMatrix);

        int row = -1;
        int col;
        while (scanner.hasNextLine()) {
            row++;
            col = -1;
            String[] arrayOfStrings = scanner.nextLine().split(" ");
            for (String eachElement : arrayOfStrings) {
                col++;
                IntMatrix[row][col] = Integer.parseInt(eachElement);
            }
        }
        scanner.close();
        return IntMatrix;
    }

}