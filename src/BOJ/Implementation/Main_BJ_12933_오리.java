package BOJ.Implementation;

import java.io.*;

public class Main_BJ_12933_오리 {

    public static final String DUCK_SOUND = "quack";
    public static final char COUNTED = '_';

    public static char[] sound;
    public static int remains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sound = br.readLine().toCharArray();
        int ducks = 0;
        remains = sound.length;
        boolean findStatus;
        if (remains % DUCK_SOUND.length() == 0) {
            while (true) {
                findStatus = findDuck();
                if (!findStatus) {
                    break;
                } else {
                    ducks++;
                }
            }
        }
        if (ducks == 0 || remains > 0) {
            ducks = -1;
        }
        bw.write(Integer.toString(ducks));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean findDuck() {
        int index = 0;
        int count = 0;
        char currentSound;
        for (int i = 0; i < sound.length; i++) {
            currentSound = sound[i];
            if (currentSound == COUNTED) {
                continue;
            }
            if (currentSound == DUCK_SOUND.charAt(index)) {
                sound[i] = COUNTED;
                remains--;
                if (++index == DUCK_SOUND.length()) {
                    index = 0;
                    count++;
                }
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
}
