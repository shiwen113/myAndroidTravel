package gem.day8;

import java.math.BigDecimal;

public class BigDecimalDemo {

	public static void main(String[] args) {
		//浮点数在计算机中的表示
		//整数在计算机中的表示，正数用源码表示，负数用补码表示
		System.out.println(0.05 + 0.01);
		System.out.println(0.05 - 0.02);
		
		//创建BigDecimal的三种基本方法
		BigDecimal d1 = new BigDecimal("0.05");
		BigDecimal d2 = new BigDecimal("0.01");
		//valueOf方法
		BigDecimal d3 = BigDecimal.valueOf(0.07);
		//运算，+ - * /
		BigDecimal d = d1.add(d2);
		System.out.println(d1+"+"+d2+"="+d);
		//
		d = d1.subtract(d2);
		System.out.println(d1+"-"+d2 +"="+d );
		d = d1.multiply(d2);
		System.out.println(d1+"*"+d2 +"="+d );
		d = d1.divide(d2);
		System.out.println(d1+"/"+d2 +"="+d );
		
	}

}
