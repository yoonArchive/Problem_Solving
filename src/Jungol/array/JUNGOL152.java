package Jungol.array;

import java.util.Scanner;

public class JUNGOL152 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];
        int odd = 0, even = 0;
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = sc.nextInt();
            if (i % 2 == 0) {
                even += arr[i - 1];
            } else {
                odd += arr[i - 1];
            }
        }
        System.out.println("odd : " + odd);
        System.out.println("even : " + even);
        sc.close();
    }

}
