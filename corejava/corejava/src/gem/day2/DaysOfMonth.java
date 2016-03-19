package gem.day2;

import java.util.Scanner;

public class DaysOfMonth {

	public static void main(String[] args) {
		// 计算某年某月的天数
		// 输 入年份，输入月份
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入年份：");
		int year = scanner.nextInt();
		System.out.println("请输入月份：");
		//month:1-12 
		int month = scanner.nextInt();
		int days = 30;	//总天数
		//if-else switch-case
		if(month==1 || month==3 || month==5 || month==7 
				|| month==8 || month==10 || month==12) {
			// 1,3,5,7,8,10,12 ==> 31天
			days = 31;
		}else if(month == 4 || month == 6 || month==9 
				|| month==11) {
			// 4,6,9,11        ==> 30天
			days = 30;
		}else if(month == 2) {
			// 2  是闰年，29天    不是闰年，28天
			//如何判断是否是闰年：
			//1） 如果这个年份能够被4 整除，且不能被100 整除，则这一年是闰年。
			//  例如，1996 年是闰年，而相应的，1993 年就不是闰年。
			//     year%4==0     并&&    year%100!=0
			//条件1 || 条件2 
			//2） 如果这个年份能够被100 整除，则这个数必须要能被400 整除，才是闰年。例如，2000 年是闰年，1900 年不是闰年。
			//      year%100==0     &&   year%400 == 0
			if( (year%4==0 && year%100!=0) || 
					(year%100==0 && year%400==0)) {
				days = 29;
			}else {
				days = 28;
			}
		}else {
			System.out.println("月份输入错误，请重输入");
		}
		System.out.println(year+"年"+month+"月的天数是："+days);
	}

}
