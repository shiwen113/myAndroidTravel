package gem.day11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Comparable<Student>{
	private String sno;	//学号
	private Date birthday;	//出生日期
	private int score;		//成绩
	public Student(String sno,Date birthday,int score) {
		this.sno = sno;
		this.birthday = birthday;
		this.score = score;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return "Student [sno=" + sno + ", birthday=" + 
			sdf.format(birthday) + ", score="
				+ score + "]";
	}
	@Override
	public int compareTo(Student o) {
		//排序规则：先按出生日期，再按成绩排序
		// 自定义自然排序规则,按成绩排序 
		return this.getScore() - o.getScore();
	}
	
	
	
}
