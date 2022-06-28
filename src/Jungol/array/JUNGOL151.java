package Jungol.array;

import java.util.Scanner;

public class JUNGOL151 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[5];
        int sum = 0;
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = sc.nextInt();
            if (i == 1 || i == 3 || i == 5)
                sum += arr[i - 1];
        }
        System.out.println(sum);
        sc.close();
    }

}
