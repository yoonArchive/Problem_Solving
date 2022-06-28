package Jungol.array;

import java.util.Scanner;

public class JUNGOL158 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[100];

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[count++] = sc.nextInt();
            if (arr[i] == 0) break;
        }
        System.out.println(count - 1);

        for (int i = 0; i < count - 1; i++) {
            if (arr[i] % 2 == 0) System.out.print(arr[i] / 2 + " ");
            else System.out.print(arr[i] * 2 + " ");
        }
        sc.close();
    }

}
