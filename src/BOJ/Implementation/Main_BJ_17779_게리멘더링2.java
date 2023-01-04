package BOJ.Implementation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17779_게리멘더링2 {

    public static int N, minGap;
    public static int[][] population, district;
    public static int[] distictPopulation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1][N + 1];
        district = new int[N + 1][N + 1];
        distictPopulation = new int[5];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int x, y, d1, d2;
        minGap = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                x = i;
                y = j;
                for (int k = 1; k <= y - 1; k++) {
                    d1 = k;
                    for (int l = 1; l <= N - x - d1; l++) {
                        if (l > N - y) {
                            continue;
                        }
                        d2 = l;
                        getDistrict(x, y, d1, d2);
                        clear();
                    }
                }
            }
        }
        bw.write(Integer.toString(minGap));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void getDistrict(int x, int y, int d1, int d2) {
        setBoundary(x, y, d1, d2);
        setDistrict1(x, y, d1, d2);
        setDistrict2(x, y, d1, d2);
        setDistrict3(x, y, d1, d2);
        setDistrict4(x, y, d1, d2);
        minGap = Math.min(minGap, getGap());
    }

    private static int getGap() {
        Arrays.fill(distictPopulation, 0);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                distictPopulation[district[i][j] - 1] += population[i][j];
            }
        }
        Arrays.sort(distictPopulation);
        return distictPopulation[4] - distictPopulation[0];
    }

    private static void setBoundary(int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            district[x + i][y - i] = 5;
            district[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            district[x + i][y + i] = 5;
            district[x + d1 + i][y - d1 + i] = 5;
        }
        setDistrict5(x, y, d1, d2);
    }

    private static void setDistrict1(int x, int y, int d1, int d2) {
        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (district[i][j] != 5) {
                    district[i][j] = 1;
                }
            }
        }
    }

    private static void setDistrict2(int x, int y, int d1, int d2) {
        for (int i = 1; i <= x + d2; i++) {
            for (int j = y + 1; j <= N; j++) {
                if (district[i][j] != 5) {
                    district[i][j] = 2;
                }
            }
        }
    }

    private static void setDistrict3(int x, int y, int d1, int d2) {
        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (district[i][j] != 5) {
                    district[i][j] = 3;
                }
            }
        }
    }

    private static void setDistrict4(int x, int y, int d1, int d2) {
        for (int i = x + d2 + 1; i <= N; i++) {
            for (int j = y - d1 + d2; j <= N; j++) {
                if (district[i][j] != 5) {
                    district[i][j] = 4;
                }
            }
        }
    }

    private static void setDistrict5(int x, int y, int d1, int d2) {
        for (int i = x + 1; i < x + d1 + d2; i++) {
            for (int j = y - d1; j < y + d2; j++) {
                if (district[i][j] == 5) {
                    while (j < N) {
                        if (district[i][j + 1] != 5) {
                            district[i][++j] = 5;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    private static void clear() {
        for (int i = 0; i <= N; i++) {
            Arrays.fill(district[i], 0);
        }
    }
}
