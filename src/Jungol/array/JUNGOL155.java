package Jungol.array;

import java.util.Scanner;

public class JUNGOL155 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] chars = {'J', 'U', 'N', 'G', 'O', 'L'};
        char c = sc.next().charAt(0);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                System.out.println(i);
                break;
            } else {
                if (i == chars.length - 1)
                    System.out.println("none");
            }
        }
        sc.close();

    }

}
