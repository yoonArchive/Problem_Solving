package Jungol.array;

import java.util.Scanner;

public class JUNGOL562 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];

        int sum1 = 0;
        int sum2 = 0;
        int count = 0;

        for (int i = 1; i <= 10; i++) {
            int num = sc.nextInt();
            arr[i - 1] = num;
            if (i % 2 == 0) {
                sum1 += num;
            } else {
                sum2 += num;
                count++;
            }
        }
        System.out.println("sum : " + sum1);
        System.out.printf("avg : %.1f", (double) sum2 / count);
        sc.close();
    }

}
