package gem.day11.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	//学生的属性包括学号、姓名、年龄等
	private String sno;
	private String name;
	private int age;
	//集合，表示学生的各科成绩
	//科目：用字符串表示
	Map<String,List<Score>> scores = 
			new HashMap<String,List<Score>>();
	// CoreJava: score1,score2,score3,...	
	// MySQL:
	// Andorid:
	public Student(String sno,String name,int age) {
		this.sno = sno;
		this.name = name;
		this.age = age;
	}
	//加某门课程的学生成绩
	public void addScore(String courseName,Score score) {
		//判断Map集合中是否已存在这门课程，如果不存在
		//要新建List集合放成绩，如果存在，则将成绩加到原List集合中
		List<Score> list;
		if(scores.containsKey(courseName)) {
			//取出放这门课程的成绩的集合
			list = scores.get(courseName);
		}else {
			list = new ArrayList<Score>();
		}
		list.add(score);
		//加到Map集合
		scores.put(courseName, list);
		
	}
	//提供一个方法，计算某门课程的总评成绩，这门课程的总评成绩，是
	//是其它成绩的平均分
	public double getCourseTotalScore(String courseName) {
		//
		double t = 0;
		//获得这个课程所有的成绩
		List<Score> list = scores.get(courseName);
		for(Score s:list) {
			t = t+s.getScore();
		}
		return 1.0*t/list.size();
	}
	
	
	
	//1）列出某个学生的所有成绩；列出 -- 显示（System.out）
	//获得我的每门课程的所有成绩
	public Map<String, List<Score>> getScores() {
		return scores;
	}
	
	
	
	
	public void setScores(Map<String, List<Score>> scores) {
		this.scores = scores;
	}
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
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
	
	
	
	
}
