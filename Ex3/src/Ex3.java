


public class Ex3 {
	// CrossWords first function to build the random matrix
	public static char [][] craeteWordSearch(int n, int m) {
		if (n < 1 || m < 1) {
			return null;
		}
		else {
		char mat [][] = new char [n][m];
		char ch;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int r = (int)((Math.random()*(124-98))+97);
				ch = (char)r;
				mat [i][j] = ch;
			}
		}
		return mat;
		}
	}
	

	// CrossWords second function to check if a word appears in the matrix
	public static Boolean findWord(String str, char [][] mat) {
		if(str.length() == 0) {
			return null;
		}
		else {
	    boolean flag = false;
		int length = str.length();
		int count = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						// row from left to right
						if (j + k < mat[0].length)
							if (mat[i][j + k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// row from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0)
							if (mat[i][j - k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// Column from up
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (i + k < mat.length)
							if (mat[i + k][j] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// column from down
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (i - k >= 0)
							if (mat[i - k][j] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// Main diagonal from left to right
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j + k < mat[0].length && i + k < mat.length)
							if (mat[i + k][j + k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// main diagonal from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0 && i - k >= 0)
							if (mat[i - k][j - k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// Secondary diagonal from left to right
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j + k < mat[0].length && i - k >= 0)
							if (mat[i - k][j + k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
				// Secondary diagonal from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0 && i + k < mat.length)
							if (mat[i + k][j - k] == str.charAt(k))
								count++;
					}
					if (count == length)
						flag = true;
					count = 0;
				}
			}
		}
		return flag;
		}
	}
	
	//secondary function to check if a word is palindrom
	private static boolean palindrom(String str) {
		int a = 0;
        int b = str.length()-1;
        
        while (a < b) {
            if (str.charAt(a) != str.charAt(b))
                return false;
            a++;
            b--;
        }
        return true;
	}
	
	// CrossWords third function to check how many times a word apears in the matrix
	public static int countWord(String str, char [][] mat) {
		if(str.length() == 0) {
			return 0;
		}
		else {
		int count = 0;
		int count2 = 0;
		int length = str.length();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						// row from left to right
						if (j + k < mat[0].length)
							if (mat[i][j + k] == str.charAt(k))
								count++; 
						
					}
					if (count == length) {
						count2++;}
					count = 0; 
				}
				// row from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0)
							if (mat[i][j - k] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						 count2++;}
					count = 0;
				}
				// Column from up
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (i + k < mat.length)
							if (mat[i + k][j] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						 count2++;}
					count = 0; 
				} 
				// column from down
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (i - k >= 0)
							if (mat[i - k][j] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						 count2++;}
					count = 0; 
				}
				// Main diagonal from left to right
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j + k < mat[0].length && i + k < mat.length)
							if (mat[i + k][j + k] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						 count2++;}
					count = 0; 
				}
				// main diagonal from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0 && i - k >= 0)
							if (mat[i - k][j - k] == str.charAt(k))
								count++; 
					} 
					if (count == length) {
						 count2++;}
					count = 0; 
				}
				// Secondary diagonal from left to right
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j + k < mat[0].length && i - k >= 0)
							if (mat[i - k][j + k] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						 count2++;}
					count = 0; 
				}
				// Secondary diagonal from right to left
				if (mat[i][j] == str.charAt(0)) {
					for (int k = 0; k < length; k++) {
						if (j - k >= 0 && i + k < mat.length)
							if (mat[i + k][j - k] == str.charAt(k))
								count++; 
					}
					if (count == length) {
						count2++;}
					count = 0; 
				}
			}
		}
		if (palindrom (str) == true) {
			count2 = count2/2;
		}
		if (str.length()==1) {
			count2 = count2/4;
		}
		return count2;
		}
	}
	
	
	// MineSwiper first function to place a mine in the matrix
	public static void PlaceMine(char[][]mat ) {
		int r1 = (int)(Math.random()*(mat.length));
		int r2 = (int)(Math.random()*(mat[0].length));
		if (mat [r1][r2] != '*') 
			mat [r1][r2] = '*';
		else
			PlaceMine(mat);
	}
	
	
	// MineSwiper second function to place all mines in the matrix
	public static void PlaceMines(char[][]mat, int mines) {
		for (int i = 0; i < mines; i++){
			PlaceMine(mat);
			
		}
	}
	
	
	// MineSwiper third function to place all other elements in the matrix
	public static void PlaceNumbers(char[][]mat) {
		int counter = 48;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++){
				int temp1 = i;
				int temp2 = j;
				// -1,-1
				if(temp1 -1 >= 0 && temp2 -1 >= 0) {
					if (mat[temp1-1][temp2-1] == '*') {
						counter ++;
					}
				}
				// -1,0
				if(temp1 -1 >= 0) {
					if (mat[temp1-1][temp2] == '*') {
						counter ++;
					}
				}
				// -1,+1
				if(temp1 - 1 >= 0 && temp2 + 1 < mat[0].length) {
					if (mat[temp1-1][temp2+1] == '*') {
						counter ++;
					}
				}
				// 0,-1
				if(temp2 - 1 >= 0) {
					if (mat[temp1][temp2-1] == '*') {
						counter ++;
					}
				}
				// 0,+1
				if(temp2 + 1 < mat[0].length) {
					if (mat[temp1][temp2+1] == '*') {
						counter ++;
					}
				}
				// +1,-1
				if(temp1 + 1 < mat.length && temp2 - 1 >= 0) {
					if (mat[temp1+1][temp2-1] == '*') {
						counter ++;
					}
				}
				// +1,0
				if(temp1 + 1 < mat.length) {
					if (mat[temp1+1][temp2] == '*') {
						counter ++;
					}
				}
				// +1,+1
				if(temp1 + 1 < mat.length && temp2 + 1 < mat[0].length) {
					if (mat[temp1+1][temp2+1] == '*') {
						counter ++;
					}
				}
				if (mat [i][j] == 0) {
					if (counter != 48) {
						mat[temp1][temp2] = (char)counter;
					}
					else
						mat[temp1][temp2] = '_';
				}
				counter = 48;
			}
		}
	}
	
	
	// MineSwiper main function
	public static char[][] initGame(int mines, int width, int height){
		if(mines < 0 || width < 1 || height < 1) {
			return null;
		}
		else {
		char mat[][] = new char [height][width];
		if (mines >= width*height) {
			PlaceMines(mat,width*height);
		}
		else {
			PlaceMines(mat,mines);
			PlaceNumbers(mat);
		}
			return mat;
		}
	}
}	

//	public static void main(String[] args) {
//		char [][] mtx = craeteWordSearch(2,2);
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(mtx [i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		 char[][] grid = {{'n','p','n','a','n','p','n','a','a','a','a','a','l'}, 
//                         { 'a','a','a','a','l','a','a','l','a','a','a','a','a'}, 
//                         { 'a','z','a','a','l','a','n','p','n','a','a','a','a'}};
//		
//		String str = "zaa";
//		System.out.println(findWord(str, grid));
//		System.out.println();
//		
//		System.out.println(countWord(str, grid));
//		System.out.println();
//		
//		
//		System.out.println(palindrom("pmmmp"));
//		System.out.println();
//		
//		PlaceMine(mtx);
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(mtx [i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		
//		char mat [][] = new char [5][5];
//		PlaceMines(mat,5);
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(mat [i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		PlaceNumbers(mat);
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(mat [i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		char[][]mat1 = initGame(8,5,5);
//		if (mat1 == null) {
//			System.out.println("error");
//		}
//		else {
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(mat1 [i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//  }
//}
