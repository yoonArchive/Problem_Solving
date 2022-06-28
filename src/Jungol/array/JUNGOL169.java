package Jungol.array;

import java.util.Scanner;

public class JUNGOL169 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] chars = new char[3][5];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                chars[i][j] = sc.next().charAt(0);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                System.out.printf("%c ", chars[i][j] + 32);
            }
            System.out.println();
        }

        sc.close();
    }

}
