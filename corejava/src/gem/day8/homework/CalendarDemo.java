package gem.day8.homework;

import java.util.Calendar;

public class CalendarDemo {
	/*
	 * 通过Calendar获得当前日期的年份、月份、几号、星期几、一年中的第几天
	 */
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("当前日期年份："+c.get(Calendar.YEAR));
		System.out.println("当前日期月份："+(c.get(Calendar.MONTH)+1));
		System.out.println("当前日期："+c.get(Calendar.DAY_OF_MONTH));
		System.out.println("当前星期几："+(c.get(Calendar.DAY_OF_WEEK)-1));
		System.out.println("当前日期是一年中第必天："+c.get(Calendar.DAY_OF_YEAR));

	}

}
