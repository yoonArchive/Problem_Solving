package Jungol.array;

import java.util.Scanner;

public class JUNGOL150 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = new char[10];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = sc.next().charAt(0);
        }
        for (int i = chars.length - 1; i >= 0; i--)
            System.out.print(chars[i] + " ");
        sc.close();
    }

}
