package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_10775_공항 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int GateNum = Integer.parseInt(br.readLine());
        int planeNum = Integer.parseInt(br.readLine());
        int dockingTriedNum[] = new int[GateNum + 1];

        int dockedPlane = 0;
        for (int i = 0; i < planeNum; i++) {
            int curGate = Integer.parseInt(br.readLine());
            while (dockingTriedNum[curGate] > 0) {
                int tried = dockingTriedNum[curGate];
                dockingTriedNum[curGate]++;
                curGate = curGate - tried;
                if (curGate < 1) break;
            }
            if (curGate < 1) break;
            else {
                dockingTriedNum[curGate]++;
                dockedPlane++;
            }
        }

        bw.write(Integer.toString(dockedPlane));
        br.close();
        bw.flush();
        bw.close();

    }

}
