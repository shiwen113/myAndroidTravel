package gem.day11.homework;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreStat {
	//方法:列出某门课程每个学生的总评成绩，及所有学生总评成绩的总评分；
	//获得每个学生的总评成绩
	public  static Map<Student,Double>  getStudentTotalScore(List<Student> students,
				String courseName) {
	    	 Map<Student,Double> ss= new HashMap<Student,Double>();
	    	 for(Student s:students) {
	    		 //得到这个学生的这门课程的总成绩
	    		 //double == > Double
	    		 ss.put(s, s.getCourseTotalScore(courseName));
	    	 }
	    	 return ss;
	}
	//列出某门课程所有学生总评成绩的总评分
	public static double getToatalScore(List<Student> students,String courseName) {
		double sum = 0;
		Map<Student,Double> scores =  getStudentTotalScore(students,courseName);
		Collection<Double> ss = scores.values();
		for(Double d:ss) {
			sum += d;
		}
		return sum;
	}
	//分区段统计某门课程的学生总评成绩，例如60 分以下的学生人数、60 至70 分的学生人数等。

	public  int[] stat(List<Student> students,String courseName,int[]arr) {
		//60,70,80,90,
		//返回统计出的人数
		return null;
		
	}
	
	
	
	
	
	
	
	
}
