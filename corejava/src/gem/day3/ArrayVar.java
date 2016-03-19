package gem.day3;

public class ArrayVar {

	public static void main(String[] args) {
		//数组是一种引用类型
		int[] arr1 = {10,20,30,40,50};
		int[] arr2 = arr1;

		arr2[1] = 2;
		System.out.println(arr1[1]);
		//
		People[] peoples = new People[]{
				new People(),
				new People()
		};
		
	}

}

class People{}