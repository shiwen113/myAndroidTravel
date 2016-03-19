package gem.day3;

public class BinarySearch {

	public static void main(String[] args) {
		//二分查找的基本算法是，对排序的数组，
		//首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，
		//如果两者相等，则查找成功；否则利用中间位置记录将表分成前、后两个子表，
		//如果中间位置记录的关键字大于查找关键字，则进一步查找前一子表，
		//否则进一步查找后一子表。重复以上过程，直到找到满足条件的记录，使查找成功，
		//或直到子表不存在为止，此时查找不成功。
		int[] arr = {2,3,5,7,11,13,17,19,23,29};
		int key = 13;
		int start = 0;	//开始索引
		int end = arr.length - 1;	//结束索引  9
		boolean flag = false;
		while(start<=end) {
			int middle = (end + start) /2;  //中间位置，4
			//判断中间这个数arr[middle]与要找的数的关系
			//相等，找到，退出
			if(key == arr[middle]) {
				System.out.println("找到，索引:"+middle);
				flag = true;
				break;
			}else if(key < arr[middle]) {
				//key < arr[middle],在左半区间找
				end = middle - 1;
			}else if(key > arr[middle]) {
				//key > arr[middle],在右半区间找
				start = middle + 1;
			}
		}
		if(!flag) {
			System.out.println("没有找到!!!");
		}
		
		
//		start = middle + 1;             //5 
//		middle = (end + start) /2 ;     //7
//		end = middle - 1;				//6
//		middle = (end + start) /2 ;     //5
//		start = middle + 1;             
		
		
		
		//System.out.println(arr.length);
		
		
		

	}

}
