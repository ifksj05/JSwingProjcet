package admin;

import java.time.LocalDateTime;

public class Admin {

	private String id;
	private String passwd;
	private String Name;
	private int pID;
	private int failCnt;
	private LocalDateTime allowLoginDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public int getFailCnt() {
		return failCnt;
	}

	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}

	public LocalDateTime getAllowLoginDate() {
		return allowLoginDate;
	}

	public void setAllowLoginDate(LocalDateTime allowLoginDate) {
		this.allowLoginDate = allowLoginDate;
	}

}
