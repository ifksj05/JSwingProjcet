package dao.dml.select;

import java.util.Vector;

import jdbc.DbManager;

public class TableDataShowDao {
	private DbManager db;

	Vector<Vector<String>> data = new Vector<>();

	public TableDataShowDao() {
		db = new DbManager();
		data = db.getData("SELECT u_no, n_title, n_mkdate FROM ghas_notice.notice;");

	}

	public Vector<Vector<String>> getData() {
		return data;
	}

}
