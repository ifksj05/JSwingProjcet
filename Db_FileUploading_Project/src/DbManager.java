import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DbManager {
	private String URL = "jdbc:mysql://localhost/?" + "CharacterEncoding=UTF-8&" + "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&" + "allowLoadLocalInfile=true&" + "allowMultiQueries=true";
	private String ID = "root";
	private String PW = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	private Vector<String> row;
	private Vector<Vector<String>> data;

	public DbManager() {
		try {
			con = DriverManager.getConnection(URL, ID, PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int setData(String sql, Object... values) {
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	private Vector<Vector<String>> getData(String sql, Object... values) {
		data = new Vector<Vector<String>>();
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				row = new Vector<String>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i) + "");
				}

				data.add(row);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
