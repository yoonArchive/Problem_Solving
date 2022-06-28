package Jungol.array;

public class JUNGOL556 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = i;
            System.out.print(arr[i - 1] + " ");
        }
    }

}
