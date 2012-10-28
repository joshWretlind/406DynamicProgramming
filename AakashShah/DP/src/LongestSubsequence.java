
public class LongestSubsequence {
	
	private char[] data;
	private int len;
	
	private int[][] subdata;
	
	public LongestSubsequence(char[] data) {
		this.data = data;
		this.len = data.length;
		
		subdata = new int[len][len];
		initTable();
	}
	public void initTable() {
		for (int i = 0; i < len; ++i)
			subdata[i][i] = 1;
	}
	
	public int max(int x, int y) {
		if (x > y)
			return x;
		return y;
	}
	
	public void compute() {
		for (int whichDiag = 1; whichDiag <= len; ++whichDiag) {
			for (int i = 0; i < (len - whichDiag); ++i) {
				int diag = i + whichDiag;
				
				if (data[i] == data[diag] && whichDiag == 1)
					subdata[i][diag] = 2;
				else if (data[i] == data[diag])
					subdata[i][diag] = subdata[i+1][diag-1] + 2;
				else
					subdata[i][diag] = max(subdata[i][diag-1], subdata[i+1][diag]);
			}
		}
	}
	
	public void printSubData() {
		System.out.print("    ");
		for (int i = 0; i < len; ++i) {
			System.out.print(data[i] + " | ");
		}
		System.out.print("\n");
		for (int i = 0; i < len; ++i) {
			System.out.print(data[i] + " | ");
			for (int j = 0; j < len; ++j) {
				System.out.print(subdata[i][j] + " | ");
			}
			System.out.print("\n");
		}
	
	}
	public static void main(String[] args) {
		char[] data = "ababa".toCharArray();
		LongestSubsequence ls = new LongestSubsequence(data);
		ls.compute();
		ls.printSubData();

	}

}
