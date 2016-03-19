package gem.day4.homework;
/**
 * 
 *
 *
 */

//复数类
public class Complex {
	private double real;	//实部
	private double im;		//虚部
	//构造方法
	public Complex(double real,double im) {
		this.real = real;
		this.im = im;
	}
	public Complex() {//复数：1+1i
		this(1,1);
	}
	//get/set方法
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public double getIm() {
		return im;
	}
	public void setIm(double im) {
		this.im = im;
	}
	//加复数的方法 ,,表示当前Complex 对象与参数c 对象相加
	public Complex add(Complex c)  {
		//新的复数的实部
		return 
			new Complex(   this.getReal() + c.getReal(),
		    this.getIm() + c.getIm());
	}
	//表示当前Complex 对象与实数real 相加
	public Complex add(double real) {
		return add(new Complex(real,0));
	}
	
}
