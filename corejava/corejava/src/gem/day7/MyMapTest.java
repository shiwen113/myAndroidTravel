package gem.day7;

public class MyMapTest {
	//写一个静态内部类，实现MyMap中的Entry接口
	static class EntryImpl implements MyMap.Entry {
		private String key;
		private String value;
		//
		public EntryImpl(String key,String value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public String getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return value;
		}
	}

	public static void main(String[] args) {
		//面向接口编程：
		MyMap.Entry entry = new EntryImpl("tom","corejava");
		System.out.println(entry.getKey()+","+entry.getValue());
	}

}
