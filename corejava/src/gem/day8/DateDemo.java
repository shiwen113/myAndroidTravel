package gem.day8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {

	public static void main(String[] args) throws ParseException {
		//
		//java.sql.Date();
		//创建了一个表示当前系统日期时间的对象
		Date now = new Date();
		int year = now.getYear();//过时
		long l = now.getTime();	//
		System.out.println(year+","+l);
		//字符串与日期的转换（重要）,SimpleDateFormat
		//说明字符串表示日期的格式是：
		// yyyy-MM-dd HH:mm:ss
		// 2015-12-9  11:22:12
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//字符串 ==》日期对象  parse
		//日期对象==>字符串   format
		String s = sdf.format(now);
		System.out.println(s);
		String sdate = "1992-2-2 13:10:10";
		Date d = sdf.parse(sdate);
		System.out.println(d.getTime());
		//Calendar对象取得，年、月、日、时、分、秒
		//代表当前的时间
		Calendar c = Calendar.getInstance();
		//指定日期时间:1992-2-2 13:10:10
		c.setTime(d);
		//取出其中的年、月、日
		//1-年份，2-月份
		//Calendar静态常量
		int y = c.get(Calendar.YEAR);
		//得到的月份从0开始
		int m = c.get(Calendar.MONTH) + 1;
		int days = c.get(Calendar.DATE);
		//星期是从星期天开始的
		int w = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(y+"-"+m+"-"+days +",星期："+w);
		
		
		

	}

}
