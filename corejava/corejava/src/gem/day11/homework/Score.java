package gem.day11.homework;

public class Score {
	//1:平时成绩、2:期中考试成绩、3:实习成绩、
	//4:期末考试成绩,5、总评成绩(计算出来的)？？
	int scoreType;	//成绩的类型
	int score;	//分数
	public Score(int scoreType,int score) {
		this.scoreType = scoreType;
		this.score = score;
	}
	public int getScoreType() {
		return scoreType;
	}
	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "类型："+scoreType+",成绩："+score;
	}
}
