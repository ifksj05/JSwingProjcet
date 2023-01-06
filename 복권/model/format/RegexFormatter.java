package format;

import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

public class RegexFormatter extends DefaultFormatter {

	private FormatModel[] models;

	public RegexFormatter(int length) {
		this(new FormatModel(length));
	}

	public RegexFormatter(FormatModel... models) {
		this.models = models;
		setOverwriteMode(false);
	}

	@Override
	public Object stringToValue(String arg0) throws ParseException {
		
		if (arg0 == null || arg0.equals("")) {
			return null;
		}
		
		for (int i = 0; i < models.length; i++) {
			
			boolean isMatch = models[i].getMatcher().reset(arg0).matches();
			
			if (!isMatch) {
				throw new ParseException(models[i].getErrorMessage(), 0);
			}
			
		}
		
		return super.stringToValue(arg0);
	}

}
