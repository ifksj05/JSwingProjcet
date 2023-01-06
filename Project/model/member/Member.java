package member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import base.IModel;

public class Member implements IModel {

	private int MemberCode;
	private String MemberName;
	private String Phonee;
	private String Address;
	private LocalDateTime RegDate;

	public int getMemberCode() {
		return MemberCode;
	}

	public void setMemberCode(int memberCode) {
		MemberCode = memberCode;
	}

	public String getMemberName() {
		return MemberName;
	}

	public void setMemberName(String memberName) {
		MemberName = memberName;
	}

	public String getPhonee() {
		return Phonee;
	}

	public void setPhonee(String phonee) {
		Phonee = phonee;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public LocalDateTime getRegDate() {
		return RegDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		RegDate = regDate;
	}

	@Override
	public Object[] getObjectArray() {
		return new Object[] {MemberCode, MemberName, Phonee, Address, RegDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.0"))};
	}

	public String toString() {
		return this.MemberCode + "";
	}
	
}
