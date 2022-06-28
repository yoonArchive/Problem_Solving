package Jungol.array;

import java.util.Scanner;

public class JUNGOL558 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[100];
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] == 0)
                break;
            size++;

        }
        for (int i = size - 1; i >= 0; i--)
            System.out.print(arr[i] + " ");
        sc.close();
    }

}
