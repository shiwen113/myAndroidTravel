package gem.day7;

public interface MyMap {
	//类似于静态内部类，定义一个内部的静态接口
	public static interface Entry {
		//定义两个方法，方法获得键，
		public abstract String getKey();
		public abstract String getValue();
	}

}
