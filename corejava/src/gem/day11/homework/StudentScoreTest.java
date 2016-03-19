package gem.day11.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentScoreTest {

	public static void main(String[] args) {
		// 创建学生对象
		Student s = new Student("112","tom",20);
		// 加一些课程的成绩
		s.addScore("CoreJava", new Score(1,80));//平时
		s.addScore("CoreJava", new Score(1,85));
		s.addScore("CoreJava", new Score(2,90));//期中
		s.addScore("CoreJava", new Score(3,95));//实习
		s.addScore("CoreJava", new Score(4,88));//期末
		
		s.addScore("MySQL", new Score(1,82));//平时
		s.addScore("MySQL", new Score(1,85));
		s.addScore("MySQL", new Score(2,94));//期中
		s.addScore("MySQL", new Score(3,92));//实习
		s.addScore("MySQL", new Score(4,88));//期末
		//总评成绩
		//获得这个学生的各科成绩，输出
		Map<String,List<Score>> scores = s.getScores();
		Set<String> courses = scores.keySet();
		for(String c:courses) {
			//
			System.out.println(c);
			List<Score> list = scores.get(c);
			System.out.println(list);
			System.out.println("总评成绩：");
			double d = s.getCourseTotalScore(c);
			System.out.println(d);
		}
		
		List<Student> students = new ArrayList<Student>();
		students.add(s);
		Map<Student,Double> cs = ScoreStat.getStudentTotalScore(
				students, "MySQL");
		
		
	}

}
