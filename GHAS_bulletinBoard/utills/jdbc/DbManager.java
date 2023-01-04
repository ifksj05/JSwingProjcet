package jdbc;

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

	public DbManager() {
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			System.out.println("DbManager 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DbManager 연결 실패");
		}
	}

	public int setData(String sql, Object... values) {
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			System.out.println("setData 성공");
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("setData 실패");
			return -1;
		}
	}

	public Vector<Vector<String>> getData(String sql, Object... values) {
		Vector<Vector<String>> data = new Vector<>();
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				Vector<String> row = new Vector<>();
				for (int i = 1; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i) + "");
				}

				data.add(row);

			}

			System.out.println("getData 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getData 실패");
		}
		return data;
	}

}
