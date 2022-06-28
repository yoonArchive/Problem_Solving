package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10163_색종이 {
	private static final int SIZE=1001;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new  BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int N=Integer.parseInt(br.readLine());
		int grid[][]=new int[SIZE][SIZE];
		for(int i=1;i<=N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			int c=Integer.parseInt(st.nextToken()); 
			int r=SIZE-1-Integer.parseInt(st.nextToken()); 
			int width=Integer.parseInt(st.nextToken()); 
			int height=Integer.parseInt(st.nextToken());

			for(int j=r;j>r-height;j--) 
				for(int k=c;k<c+width;k++) 
					grid[j][k]=i;
		}
		int area[]=new int[N];
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				int paperNum=grid[i][j];
				if(paperNum!=0) area[paperNum-1]++;
			}
		}
		Arrays.stream(area).forEach(n->sb.append(n+"\n"));
		System.out.println(sb.toString());
		br.close();
	}

}
