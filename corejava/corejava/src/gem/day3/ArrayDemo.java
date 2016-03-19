package gem.day3;

import java.util.Scanner;

public class ArrayDemo {

	public static void main(String[] args) {
		// 类型：类型用在变量名的前面
		//作用：装什么样的数据（右边可以写什么）
		//（基本数据类型）可以作什么运算，引用类型（可以调用什么方法）
		//创建数组的三种方法
		//方法1
		int[] i = new int[10];	//变成数组类型，数组类型是引用类型
		//初始化时，默认值是0，boolean:false,引用类型：null
		String[] sarr = new String[]{"Java","JSP","Andorid","Servlet"};
		//方法2:定义时即初始化
		int[] iarr = new int[]{1,2,3,4,5,6,7};
		//方法3：定义时初始化的更简洁的写法
		int[] iarr1 = {1,2,3,4,5,6};
		
		
		//将数2放到数组i的第二个位置上
		//将字符串Java放到数组sarr的第二个位置上
		i[1] = 2;
		sarr[1] = "Java";
		//取出第二个元素
		int x = i[1];	
		String s = sarr[1];	//null
		
		System.out.println(x+","+s);
		
		//遍历数组，方法1:普通的for语句
		//length是数组的属性，j是下标
		for(int j=0;j<iarr.length;j++) {
			System.out.print(iarr[j]+",");
		}
		System.out.println();
		//for-each语句,y是数组中的元素
		for(String str:sarr) {
			//str = "123"
			System.out.print(str+",");
		}
		//当数组索引越界时，会出现运行时异常
		//合法的索引的最大值：sarr.length - 1
		String s2 = sarr[sarr.length];
		
		
	}

}
