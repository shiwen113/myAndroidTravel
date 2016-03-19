package gem.day4;

public class ValueTest {

	public static void main(String[] args) {
		int i = 10;
		int j = i;
		j = 20;
		//i,j
		System.out.println("i="+i+",j="+j);
		Value1 v1 = new Value1();
		Value1 v2 = v1;
		v2.i = 20;
		System.out.println(v1.i+","+v2.i);
		
	}
}
class Value1 {
	int i = 10;
}