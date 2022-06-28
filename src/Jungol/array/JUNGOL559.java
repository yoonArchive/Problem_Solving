package Jungol.array;

import java.util.Scanner;

public class JUNGOL559 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] avg = {85.6, 79.5, 83.1, 80.0, 78.2, 75.0};
        int class1 = sc.nextInt();
        int class2 = sc.nextInt();

        System.out.printf("%.1f", (avg[class1 - 1] + avg[class2 - 1]));
        sc.close();
    }


}
