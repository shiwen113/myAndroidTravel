package gem.day10.homework;
/*
 * 7、（**综合）有如下Student 对象
Student
	name : String
	age : int
	score : double
	classNum : String
其中，classNum 表示学生的班号，例如“class05”。实现Student类
有如下List
List list = new ArrayList();
list.add(new Student(“Tom”, 18, 100, “class05”));
list.add(new Student(“Jerry”, 22, 70, “class04”));
list.add(new Student(“Owen”, 25, 90, “class05”));
list.add(new Student(“Jim”, 30,80 , “class05”));
list.add(new Student(“Steve”, 28, 66, “class06”));
list.add(new Student(“Kevin”, 24, 100, “class04”));
在这个list 的基础上，完成下列要求：
1） 计算所有学生的平均年龄
2） 计算各个班级的平均分
 */
public class Student {
	private String name;
	private int age;
	private double score;
	private String classNum;
	public Student(String name,int age,double score,String classNum) {
		this.name = name;
		this.age = age;
		this.score = score;
		this.classNum = classNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	
}
