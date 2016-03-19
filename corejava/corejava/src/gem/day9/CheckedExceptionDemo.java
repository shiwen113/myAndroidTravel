package gem.day9;

import java.io.FileNotFoundException;
import java.io.*;
import java.sql.SQLException;

public class CheckedExceptionDemo {
	//抛出一个编译期异常，如何处理编译异常
	//throws：异常类型  》= throw出的异常对象的类型（父类型）
	public static int method(int i) throws IOException,SQLException{
		//正常
		if(i>0) {
			i = i+100;
			System.out.println("i="+i);
			return i;
		}//不正常的流程，抛出一个编译期异常，IOException
		//可以抛出多个异常
		if(i==0) {//抛出一个SQLException
			throw new SQLException("这是一个SQLException");
		}
		IOException ioe = new IOException("这是一个编译期异常");
		//编译器会要求我们去处理该异常,checked
		//处理编译期异常的方法：在方法的声明中抛出异常
		throw ioe;
	}
	
	public static void main(String[] args) {
		String o;
		//处理编译期异常的第二种方法，
		//try-catch-finally语句块处理的运行流程
		try {
			int i = method(1);//抛出异常之后，后面语句不会运行
			System.out.println("i="+i);
			return;
		}catch(IOException | SQLException ex) {//catch多个不同类型的异常
			//获得throw出的异常对象,处理该异常
			//当try语句块中抛出异常时会运行
			//System.out.println("ioExceptiom catch");
			//显示异常相关的信息
			String mess = ex.getMessage();
			System.out.println(mess);
			ex.printStackTrace();
			//转换===》系统定义的异常，抛出
			throw new RuntimeException("........................");
			
		}
//		catch(SQLException ex) {
//			System.out.println("SQL EXception catch");
//		}
		catch(Exception e) {
			//catch多个异常，父类的异常写在后面
		}finally {
			//一定会运行到
			System.out.println("finally");
			try {}
			catch(Exception e) {}
		}
		System.out.println("end");
		
	}

}
