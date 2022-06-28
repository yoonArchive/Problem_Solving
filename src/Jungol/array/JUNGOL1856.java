package Jungol.array;

import java.util.Scanner;

public class JUNGOL1856 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[][] = new int[n][m];
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                for (int j = 0; j < m; j++) {
                    arr[i - 1][j] = ++num;
                }
            } else {
                for (int j = m - 1; j >= 0; j--) {
                    arr[i - 1][j] = ++num;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }

}
