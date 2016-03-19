package gem.day7;
//说明一下，是可以clone的
//让这个类实现Cloneable:标志接口，
public class Complex implements Cloneable{//复数
	private double real;	//实部
	private double im;		//虚部
	
	public Complex(double real,double im) {
		this.real = real;
		this.im = im;
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
	
	//重写toString方法，实部 + 虚部 i
	@Override
	public String toString() {
		return real+"+" + im + "i";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(im);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(im) != Double.doubleToLongBits(other.im))
			return false;
		if (Double.doubleToLongBits(real) != Double
				.doubleToLongBits(other.real))
			return false;
		return true;
	}
	
	//重写clone方法，clone出和这个对象一样的一个对象
	@Override
	public Object clone() throws CloneNotSupportedException {
		//调用父类的clone,
		return super.clone();
	}
	
	
	
	
}
