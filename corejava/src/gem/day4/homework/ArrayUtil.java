package gem.day4.homework;

public class ArrayUtil {
	
	public int  inArr(int[] arr,int n) {
		for(int i=0;i<arr.length;i++) {
			if(n == arr[i]) {
				return i;
			}
		}
		//n不在数组中
		return -1;
	}
	

	public static void main(String[] args) {
		//测试
		int[] arr = {10,20,35,56,98,23};
		ArrayUtil au = new ArrayUtil();
		int i = au.inArr(arr, 100);
		System.out.println(i);

	}

}
