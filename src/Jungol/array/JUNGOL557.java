package Jungol.array;

import java.util.Scanner;

public class JUNGOL557 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = new char[10];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = sc.next().charAt(0);
        }
        for (int i = 1; i <= chars.length; i++) {
            if (i == 1 || i == 4 || i == 7)
                System.out.print(chars[i - 1] + " ");
        }
        sc.close();
    }

}
