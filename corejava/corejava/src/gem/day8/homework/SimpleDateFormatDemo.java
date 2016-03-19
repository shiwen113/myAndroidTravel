package gem.day8.homework;

import java.text.SimpleDateFormat;
import java.util.Date;

//查API了解创建SimpleDateFormat对象时可以使用的格式字符。
//创建一个Date对象，按类似于2013年1月1日的格式显示其日期，
//按11:11:11的格式显示其时间。
//将表示日期的字符串2013-7-1日转换为一个日期对象。

public class SimpleDateFormatDemo {

	public static void main(String[] args) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		System.out.println("当前日期是："+sdf1.format(d));
		System.out.println("当前时间是："+sdf2.format(d));

	}

}
