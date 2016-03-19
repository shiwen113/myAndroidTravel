package gem.day3;

public class TwoDimensionArray {

	public static void main(String[] args) {
		String[] [] courses = new String[][] {
				{"Java","JSP","Servlet","Spring","Android"},
				new String[3],
				new String[]{"HTML","Photshop","CSS"}
		};
		//给IOS定义课程：ObjectC MySQL PHP
		courses[1][0] = "ObjectC";
		courses[1][1] = "MySQL";
		courses[1][2] = "PHP";
		System.out.println(courses.length);
		//=new String[] {"Android","Ios","UIUE"};
		//遍历二维数组,普通for语句，
		for(int i=0;i<courses.length;i++) {
			for(int j=0;j<courses[i].length;j++) {
				System.out.print(courses[i][j]+",");
			}
			System.out.println();
		}
		//for-each语句
		for(String[] course:courses) {
			for(String s:course) {
				System.out.print(s+",");
			}
			System.out.println();
		}

	}

}
