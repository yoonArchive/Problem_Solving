package Programmers;

public class Solution_2020_Kakao_Blind_Recruitment_자물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = {{0, 0, 1}, {0, 0, 1}, {0, 1, 0}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }

    static int rotatedKey[][];

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        int keyLen = key.length;
        int lockLen = lock.length;
        int count = 0; // 자물쇠 홈의 개수

        // 임시 자물쇠 배열 생성 - 홈 : 2 , 돌기: 1
        int tmpLock[][] = new int[lockLen + 2 * keyLen][lockLen + 2 * keyLen];
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                int val = lock[i][j];
                if (val == 0) {
                    count++;
                    val = 2;
                }
                tmpLock[i + keyLen][j + keyLen] = val;
            }
        }

        rotatedKey = new int[keyLen][keyLen];
        for (int i = 0; i < 4; i++) {
            rotate(key);
            answer = canOpen(tmpLock, count);
            if (answer)
                break;
        }
        return answer;
    }

    // 열쇠 시계방향 회전
    public static void rotate(int[][] key) {
        int keyLen = key.length;
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                rotatedKey[i][j] = key[keyLen - j - 1][i];
            }
        }
        // 원본 key에 복사
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                key[i][j] = rotatedKey[i][j];
            }
        }
    }

    // 열쇠 이동하면서 자물쇠 홈에 맞는지 체크
    public static boolean canOpen(int[][] lock, int count) {
        int tmpCount = 0;
        int lockLen = lock.length;
        int keyLen = rotatedKey.length;
        // 자물쇠 시작 좌표
        for (int i = 1; i < lockLen - keyLen; i++) {
            for (int j = 1; j < lockLen - keyLen; j++) {
                tmpCount = 0;
                // 열쇠랑 비교
                outer:
                for (int k = 0; k < keyLen; k++) {
                    for (int l = 0; l < keyLen; l++) {
                        if (rotatedKey[k][l] == 1 && lock[i + k][j + l] == 1) // 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안된다.
                            break outer;
                        if (rotatedKey[k][l] == 1 && lock[i + k][j + l] == 2)
                            tmpCount++;
                        if (tmpCount == count)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
