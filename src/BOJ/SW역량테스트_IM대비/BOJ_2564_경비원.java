package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2564_경비원 {

    public static class Location {
        int dir;
        int x;
        int y;

        public Location(int dir, int x, int y) {
            super();
            this.dir = dir;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int store = Integer.parseInt(br.readLine());

        Location[] storeLoc = new Location[store];
        Location guard = null;
        for (int i = 0; i <= store; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int Dir = Integer.parseInt(st.nextToken());
            int Distance = Integer.parseInt(st.nextToken());

            Location nowLoc;
            if (Dir == 1)
                nowLoc = new Location(Dir, Distance, height);
            else if (Dir == 2)
                nowLoc = new Location(Dir, Distance, 0);
            else if (Dir == 3)
                nowLoc = new Location(Dir, 0, height - Distance);
            else
                nowLoc = new Location(Dir, width, height - Distance);

            if (i < store)
                storeLoc[i] = nowLoc;
            else
                guard = nowLoc;
        }

        int gDir = guard.dir, gX = guard.x, gY = guard.y;
        int minDistance = 0;
        if (gDir == 1 || gDir == 2) { // 동근이가 북 or 남쪽에 위치
            for (int i = 0; i < store; i++) {
                int sDir = storeLoc[i].dir, sX = storeLoc[i].x, sY = storeLoc[i].y;
                if (gDir == 1) sY = height - sY;

                if ((gDir == 1 && sDir == 1) || (gDir == 2 && sDir == 2)) minDistance += Math.abs(gX - sX);
                if ((gDir == 1 && sDir == 2) || (gDir == 2 && sDir == 1))
                    minDistance += Math.min((width - gX) + height + (width - sX), gX + height + sX);
                if (sDir == 3) minDistance += (gX + sY);
                if (sDir == 4) minDistance += (width - gX) + sY;
            }
        } else { // 동근이가 서 or 동쪽에 위치
            for (int i = 0; i < store; i++) {
                int sDir = storeLoc[i].dir, sX = storeLoc[i].x, sY = storeLoc[i].y;
                if (gDir == 4) sX = width - sX;

                if (sDir == 1) minDistance += (height - gY) + sX;
                if (sDir == 2) minDistance += (gY + sX);
                if ((gDir == 3 && sDir == 3) || (gDir == 4 && sDir == 4)) minDistance += Math.abs(gY - sY);
                if ((gDir == 3 && sDir == 4) || (gDir == 4 && sDir == 3))
                    minDistance += Math.min((height - gY) + width + (height - sY), gY + width + sY);
            }
        }

        bw.write(Integer.toString(minDistance));
        br.close();
        bw.flush();
        bw.close();
    }

}
