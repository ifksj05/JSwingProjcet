
package base;

import java.sql.ResultSet;

public interface IFactory<T> {
	
	void bindModel(T model, ResultSet rs);
	T getModel(ResultSet rs);
	
}
