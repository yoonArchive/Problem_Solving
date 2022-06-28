package Jungol.array;

import java.util.Scanner;

public class JUNGOL561 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];

        int min = 9999;
        int max = 1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] < min && arr[i] >= 100)
                min = arr[i];
            if (arr[i] > max && arr[i] < 100)
                max = arr[i];
        }

        if (max == 1)
            System.out.print(100 + " ");
        else
            System.out.print(max + " ");

        if (min == 9999)
            System.out.print(100 + " ");
        else
            System.out.print(min + " ");

        sc.close();
    }

}
