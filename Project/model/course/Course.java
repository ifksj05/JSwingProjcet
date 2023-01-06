package course;

public class Course {

	private String CourseName;
	private String TeachName;
	private int Price;

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getTeachName() {
		return TeachName;
	}

	public void setTeachName(String teachName) {
		TeachName = teachName;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String toString() {
		return this.CourseName;
	}
	
}
