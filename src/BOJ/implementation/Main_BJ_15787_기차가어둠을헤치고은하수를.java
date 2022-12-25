package BOJ.implementation;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_15787_기차가어둠을헤치고은하수를 {

    public static int[] trainStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int trains = Integer.parseInt(st.nextToken());
        int commands = Integer.parseInt(st.nextToken());
        trainStatus = new int[trains + 1];
        int commandNumber, trainNumber, seatNumber;
        for (int i = 0; i < commands; i++) {
            st = new StringTokenizer(br.readLine());
            commandNumber = Integer.parseInt(st.nextToken());
            trainNumber = Integer.parseInt(st.nextToken());
            seatNumber = commandNumber <= 2 ? Integer.parseInt(st.nextToken()) : 0;
            changeStatus(commandNumber, trainNumber, seatNumber);
        }
        Set<Integer> trainSet = new HashSet<>();
        for (int i = 1; i <= trains; i++) {
            trainSet.add(trainStatus[i]);
        }
        bw.write(Integer.toString(trainSet.size()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void changeStatus(int commandNumber, int trainNumber, int seatNumber) {
        switch (commandNumber) {
            case 1:
                trainStatus[trainNumber] |= (1 << seatNumber);
                break;
            case 2:
                trainStatus[trainNumber] &= ~(1 << seatNumber);
                break;
            case 3:
                trainStatus[trainNumber] <<= 1;
                trainStatus[trainNumber] &= ((1 << 21) - 1);
                break;
            case 4:
                trainStatus[trainNumber] >>= 1;
                trainStatus[trainNumber] &= (~1);
                break;
        }
    }
}
