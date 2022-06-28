package Jungol.array;

import java.util.Scanner;

public class JUNGOL154 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] weight = new double[6];
        double sum = 0;
        for (int i = 0; i < weight.length; i++) {
            weight[i] = sc.nextDouble();
            sum += weight[i];
        }
        System.out.printf("%.1f", sum / weight.length);
        sc.close();
    }

}
