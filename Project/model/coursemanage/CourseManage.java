package coursemanage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import base.IModel;

public class CourseManage implements IModel, Comparable<CourseManage> {

	private int RegisterCode;
	private int MemberCode;
	private int CoursePrice;
	private int Period;
	private int RegPrice;
	private String MemberName;
	private String CourseName;
	private LocalDate crregdate;

	
	
	public int getRegisterCode() {
		return RegisterCode;
	}



	public void setRegisterCode(int registerCode) {
		RegisterCode = registerCode;
	}



	public int getMemberCode() {
		return MemberCode;
	}


	public void setMemberCode(int memberCode) {
		MemberCode = memberCode;
	}



	public int getCoursePrice() {
		return CoursePrice;
	}



	public void setCoursePrice(int coursePrice) {
		CoursePrice = coursePrice;
	}



	public int getPeriod() {
		return Period;
	}



	public void setPeriod(int period) {
		Period = period;
	}



	public int getRegPrice() {
		return RegPrice;
	}



	public void setRegPrice(int regPrice) {
		RegPrice = regPrice;
	}



	public String getMemberName() {
		return MemberName;
	}



	public void setMemberName(String memberName) {
		MemberName = memberName;
	}



	public String getCourseName() {
		return CourseName;
	}



	public void setCourseName(String courseName) {
		CourseName = courseName;
	}



	public LocalDate getCrregdate() {
		return crregdate;
	}



	public void setCrregdate(LocalDate crregdate) {
		this.crregdate = crregdate;
	}



	@Override
	public Object[] getObjectArray() {
		return new Object[] {RegisterCode, MemberCode, MemberName, CourseName, CoursePrice, Period, RegPrice, crregdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))};
	}

	@Override
	public int compareTo(CourseManage arg0) {
		return Integer.compare(RegisterCode, arg0.getRegisterCode());
	}
	
}
