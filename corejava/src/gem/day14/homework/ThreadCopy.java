package gem.day14.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

//拷贝文件的一部份（start开始位置,end结束位置）
//源文件 ==》 目标文件，文件（文件名，File对象）
public class ThreadCopy implements Runnable {
	private int start;//开始位置
	private int end;	//线束位置
	private File srcFile;	//源文件
	private File destFile;	//目标文件
	//构造这个对象，传入上述4个参数
	public ThreadCopy(int start,int end,File srcFile,File destFile) {
		this.start = start;
		this.end = end;
		this.srcFile = srcFile;
		this.destFile = destFile;
	}
	//选择什么流，RandomAccessFile
	@Override
	public void run() {
		//创建RandomAccessFile：原文件，目标文件
		try {
			//原文件
			RandomAccessFile raf1 = new RandomAccessFile(this.srcFile,"r");
			//目标文件
			RandomAccessFile raf2 = new RandomAccessFile(this.destFile,"rw");
			//设定目标文件的长度，与原文件的长度一样
			raf2.setLength(raf1.length());
			//准备copy，读一个字节数组，copy这个字节数组
			//移动到start位置
			raf1.seek(start);
			raf2.seek(start);
			//创建字节数组
			byte[] buff = new byte[512];
			long len = 0;		//实际读出的长度
			long prePointer = start;	//上次读出的指针位置
			//完成copy,从文件中读出一个字节数组到buff
			while((len = raf1.read(buff))!=-1) {
				//如果读出的数据已越界
				if(raf1.getFilePointer() > end) {
					len = end - prePointer;
					//写数据
					raf2.write(buff, 0, (int)len);
					break;
				}
				prePointer = raf1.getFilePointer();
				//写数据
				raf2.write(buff, 0, (int)len);
			}
			System.out.println(Thread.currentThread().getName()+
					"copy了从"+start+"到"+end+"的数据");
			
			
			//关闭流
			raf1.close();
			raf2.close();
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close
		}
		
		
		
		
		
	}
	
}
