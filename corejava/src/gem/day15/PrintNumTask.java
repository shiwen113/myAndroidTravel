package gem.day15;

import java.util.concurrent.RecursiveAction;

//示例说明：ForkJoinPool及RecursiveAction
public class PrintNumTask extends RecursiveAction {
	final static int THRESHOLD = 10;
	int start; 	//开始数字
	int end;	//最后的数字
	public PrintNumTask(int start,int end) {
		this.start = start;
		this.end = end;
	}

	//并行计算
	@Override
	protected void compute() {
		// 大的任务==》小的任务完成,小任务只打印10个数字
		if(end - start <= THRESHOLD) {
			//输出从start到end的数字：
			for(int i=start;i<end;i++) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
			
		}else {//分解任务，用新的线程完成各个任务
			//分解成左，右
			int middle = (start+end)/2;  //start,middle
			PrintNumTask left = new PrintNumTask(start,middle);
			PrintNumTask right = new PrintNumTask(middle,end);
			//
			left.fork();	//新的线程，打印
			right.fork();  	//join
		}
		
		
	}
	
}
