package gem.day8;

public class FileSuffix {
	//写一个方法，获得文件的后缀名
	public static  String getFileSuffix(String fileName) {
		//获得.的位置了
		int pos = fileName.lastIndexOf(".");
		//从.后面下一个位置取子字符，返回之
		return fileName.substring(pos+1);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "E:\\ptcwork\\ja2015112m\\workspace"+
				"\\corejava\\src\\gem\\day8\\StringDemo2.jpg";
		String suffix = getFileSuffix(fileName);
		System.out.println(suffix);
	}

}
