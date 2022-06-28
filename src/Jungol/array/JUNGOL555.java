package Jungol.array;

import java.util.Scanner;

public class JUNGOL555 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = new char[10];
        for (int i = 0; i < c.length; i++) {
            c[i] = sc.next().charAt(0);
        }
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
        sc.close();
    }

}
