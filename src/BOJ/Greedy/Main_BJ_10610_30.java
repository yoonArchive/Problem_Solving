package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main_BJ_10610_30 {

    public static boolean zeroCheck(ArrayList<Integer> numbers) {
        if (numbers.contains(0)) return true;
        return false;
    }

    public static boolean sumCheck(ArrayList<Integer> numbers) {
        int sum = numbers.stream().mapToInt(i -> i).sum();
        if (sum % 3 == 0) return true;
        return false;
    }

    public static void getMaxNumber(ArrayList<Integer> numbers) {
        Collections.sort(numbers, Comparator.reverseOrder());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0, size = str.length(); i < size; i++) {
            numbers.add(str.charAt(i) - '0');
        }

        if (zeroCheck(numbers) && sumCheck(numbers)) {
            getMaxNumber(numbers);
            numbers.stream().forEach(n -> sb.append(n));
        } else sb.append("-1");
        System.out.println(sb.toString());

    }

}
