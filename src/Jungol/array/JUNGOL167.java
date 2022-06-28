package Jungol.array;

import java.util.Scanner;

public class JUNGOL167 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[][] = new int[4][2];
        int r_sum[] = new int[4];
        int c_sum[] = new int[2];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = sc.nextInt();
                r_sum[i] += arr[i][j];
                c_sum[j] += arr[i][j];
                sum += arr[i][j];
            }
        }
        for (int i = 0; i < r_sum.length; i++)
            System.out.print(r_sum[i] / 2 + " ");
        System.out.println();
        for (int i = 0; i < c_sum.length; i++)
            System.out.print(c_sum[i] / 4 + " ");
        System.out.println();
        System.out.println(sum / (arr.length * arr[0].length));

        sc.close();
    }

}
