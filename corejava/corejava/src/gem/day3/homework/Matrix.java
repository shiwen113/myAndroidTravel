package gem.day3.homework;


//11、（*二维数组）利用二维数组实现矩阵的加法，创建一个名为Matrix的类，：
//1）写一个方法，将两个矩阵的对应项相加，返回相加后的矩阵
//2）写一个方法，将两个矩阵的对应项相减，返回相减后的矩阵
//在main方法中，定义两个二维数组，调用上面的两个方法，并输出相加相减后的数组。


public class Matrix {
//	public static int[][] add(int[][] a, int[][] b) {
//		int[][] c = new int[a.length][a[0].length];
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < a[i].length; j++) {
//				c[i][j] = a[i][j] + b[i][j];
//			}
//		}
//		return c;
//	}
//	
//	public static int[][] sub(int[][] a, int[][] b) {
//		int[][] c = new int[a.length][a[0].length];
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < a[i].length; j++) {
//				c[i][j] = a[i][j] - b[i][j];
//			}
//		}
//		return c;
//	}
	//1）写一个方法，将两个矩阵的对应项相加，返回相加后的矩阵
	public static int[][] add(int[][] x,int[][] y) {
		//x与y的行与列相同.抛出异常
		int[][] result = new  int[x.length][x[0].length];
		//result:   {0,0,0,0}
		//	    	{0,0,0,0}
		//  		{0,0,0,0}
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[0].length;j++) {
				result[i][j] = x[i][j] + y [i][j];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[][] arr1 = {		{1,2,3,4,1},
							{4,5,6,7,1},
							{7,8,9,0,1},
						};
		
		int[][] arr2 = {	{10,20,30,40,2},
							{41,51,61,71,2},
							{72,82,93,02,2}
						};
		
		int[][] z = add(arr1,arr2);
		//
		for(int i=0;i<z.length;i++) {
			for(int j=0;j<z[i].length;j++) {
				System.out.print(z[i][j]+",");
			}
			System.out.println();
		}
		
		
		
		
		
		
	}		

}
