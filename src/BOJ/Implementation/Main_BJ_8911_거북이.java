package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_8911_거북이 {

    public static int x, y, pX, pY, nX, nY, direction;
    public static int[][] deltas = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            x = y = pX = pY = nX = nY = 0;
            direction = 1; // 서, 북, 동, 남
            String commands = br.readLine();
            for (int i = 0, size = commands.length(); i < size; i++) {
                operate(commands.charAt(i));
            }
            sb.append(getArea()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void operate(char command) {
        switch (command) {
            case 'F':
                x += deltas[direction][0];
                y += deltas[direction][1];
                update(x, y);
                break;
            case 'B':
                x -= deltas[direction][0];
                y -= deltas[direction][1];
                update(x, y);
                break;
            case 'L':
                direction = ((direction - 1) + 4) % 4;
                break;
            case 'R':
                direction = ((direction + 1) + 4) % 4;
                break;
        }
    }

    private static void update(int x, int y) {
        nX = Math.min(nX, x);
        nY = Math.min(nY, y);
        pX = Math.max(pX, x);
        pY = Math.max(pY, y);
    }

    public static int getArea() {
        return (pX + Math.abs(nX)) * (pY + Math.abs(nY));
    }
}
