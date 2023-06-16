/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mulencadenadomatriz;

/**
 *
 * @author xaxbe
 */


public class MulEncadenadoMatriz {
    public static void main(String[] args) {
        int[][] A = { { 1, 2 }, { 3, 4 } };
        int[][] B = { { 5, 6 }, { 7, 8 } };
        int[][] C = { { 9, 10 }, { 11, 12 } };
        int[][] D = { { 13, 14 }, { 15, 16 } };
        int[][] E = { { 17, 18 }, { 19, 20 } };

        int[][] result = matrixChainMultiply(new int[][][] { A, B, C, D, E });

        System.out.println("Result: ");
        printMatrix(result);
    }

    public static int[][] matrixChainMultiply(int[][][] matrices) {
        int n = matrices.length;
        int[][][] dp = new int[n][n][2];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j][0] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int temp = dp[i][k][0] + dp[k + 1][j][0] + matrices[i].length * matrices[k][0].length * matrices[j][1].length;
                    if (temp < dp[i][j][0]) {
                        dp[i][j][0] = temp;
                        dp[i][j][1] = k;
                    }
                }
            }
        }

        return matrixChainMultiplyHelper(matrices, dp, 0, n - 1);
    }

    public static int[][] matrixChainMultiplyHelper(int[][][] matrices, int[][][] dp, int i, int j) {
        if (i == j) {
            return matrices[i];
        }

        int[][] left = matrixChainMultiplyHelper(matrices, dp, i, dp[i][j][1]);
        int[][] right = matrixChainMultiplyHelper(matrices, dp, dp[i][j][1] + 1, j);

        return multiply(left, right);
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, p = B[0].length;
        int[][] result = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
