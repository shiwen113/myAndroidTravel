package gem.day10.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 其中，classNum 表示学生的班号，例如“class05”。实现Student类
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
public class StudentList {
	//1） 计算所有学生的平均年龄
	public static double getAvgAge(List<Student> students) {
		if(students.size() == 0) {
			return 0;
		}
		int sumAge = 0;
		for(Student s:students) {
			sumAge += s.getAge();
		}
		return 1.0 * sumAge / students.size();
		
	}
	//2） 计算各个班级的平均分
	public static Map<String,Double> getClassAvgScore(List<Student> students) {
		//各班级总分
		Map<String,Double> classSumScore = new
				HashMap<String,Double>();
		//各班级人数
		Map<String,Integer> classNumber = new
				HashMap<String,Integer>();
		for(Student s:students) {
			String classNum = s.getClassNum();
			if(classSumScore.get(classNum) == null) {
				classSumScore.put(classNum, s.getScore());
				classNumber.put(classNum, 1);
			}else {
				classSumScore.put(classNum, 
						classSumScore.get(classNum) + s.getScore());
				classNumber.put(classNum, 
						classNumber.get(classNum)+1);
			}
		}
		Set<String> classes = classSumScore.keySet();
		Map<String,Double> classAvgScore = new
				HashMap<String,Double>();
		for(String c : classes) {
			classAvgScore.put(c, classSumScore.get(c)/classNumber.get(c));
		}
		return classAvgScore;
		
	}
	

	public static void main(String[] args) {
		// 
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("Tom", 18, 100, "class05"));
		list.add(new Student("Jerry", 22, 70, "class04"));
		list.add(new Student("Owen", 25, 90, "class05"));
		list.add(new Student("Jim", 30,80 , "class05"));
		list.add(new Student("Steve", 28, 66, "class06"));
		list.add(new Student("Kevin", 24, 100, "class04"));
		System.out.println("平均年龄："+getAvgAge(list));
		Map<String,Double> avgScore = getClassAvgScore(list);
		for(Map.Entry<String, Double> score:avgScore.entrySet())  {
			System.out.println(score.getKey()+"的平圴分是："+score.getValue());
		}
		
	}

}
