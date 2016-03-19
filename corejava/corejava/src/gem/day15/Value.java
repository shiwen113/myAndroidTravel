package gem.day15;

public class Value {
	//对比 普通变量  ThreadLocal变量
	int i = 0 ;			//i:0
	//包了一个值，整数,初始化这个值
	ThreadLocal<Integer> t =
			new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			//返回初值,int==>Integer
			return 0;
		};
	};	//t:null
	//对变量i加1
	void inc() {
		i++;
	}
	//对变量t中包装的整数加1
	void inc2() {
		t.set(t.get() + 1);
	}
	
}
