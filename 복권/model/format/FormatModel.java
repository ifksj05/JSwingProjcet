package format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatModel {

	private Matcher matcher;
	private String errorMessage;

	public FormatModel(int length) {
		this("^.{1," + length + "}", length + "자 이내로 입력해주십시오.");
	}

	public FormatModel(String patternStr, String errorMessage) {

		this.matcher = Pattern.compile(patternStr).matcher("");
		this.errorMessage = errorMessage;

	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
