package Jungol.array;

import java.util.Scanner;

public class JUNGOL168 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][n];
        arr[n - 1][0] = 1;
        for (int i = n - 1; i >= 0; i--) { // 5>4>3>2>1>0
            for (int j = 0; j < n - i; j++) { // 0>1>2>3>4>5
                if (j == 0 || j == n - i - 1)
                    arr[i][j] = 1;
                else {
                    arr[i][j] = arr[i + 1][j - 1] + arr[i + 1][j];
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0)
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }

}
