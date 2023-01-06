package module.admin;

import java.sql.ResultSet;

import admin.Admin;
import admin.AdminFactory;
import jdbc.DbManager;
import message.MessageManager;

public class Login {

	private static Admin admin;

	public void login(String id, String pw) throws Exception {

		if (id.equals("") || pw.equals("")) {
			throw new Exception("아이디와 비밀번호가 공백입니다.");
		}

		try (DbManager dbMgr = new DbManager()) {

			String sql = "select * from admin where binary(id) = ?";
			ResultSet rs = dbMgr.getResultSet(sql, id);

			if (rs.next()) {

				rs.close();

				checkLogin(dbMgr, id);

				String sql2 = "select * from admin where binary(id) = ? and binary(passwd) = ?";
				ResultSet rs2 = dbMgr.getResultSet(sql2, id, pw);

				if (rs2.next()) {

					admin = new AdminFactory().getModel(rs2);
					return;

				} else {
					writeFailLog(dbMgr, id);
				}

			}

			throw new Exception("아이디와 비밀번호를 확인해주세요.");

		} catch (Exception e) {
			throw e;
		}

	}

	public void checkLogin(DbManager dbMgr, String id) throws Exception {

		String sql = "select allowLoginDate - now() from admin where binary(id) = ?";
		ResultSet rs = dbMgr.getResultSet(sql, id);

		if (rs.next()) {

			int second = rs.getInt(1);
			if (second >= 1) {
				throw new Exception(second + "초 후 다시 시도 할 수 있습니다.");
			}

		}

	}

	public void writeFailLog(DbManager dbMgr, String id) throws Exception {

		String sql = "update admin set failCnt = failCnt + 1 where binary(id) = ?";
		dbMgr.getUpdateResult(sql, id);

		ResultSet rs = dbMgr.getResultSet("select failCnt from admin where binary(id) = ?", id);

		if (rs.next()) {

			if (rs.getInt(1) >= 3) {
				
				dbMgr.getUpdateResult("update admin set failCnt = 0, allowLogindate = date_add(now(), interval 10 second) where binary(id) = ?", id);
				checkLogin(dbMgr, id);

			}

		}

	}

	public static Admin getAdmin() throws Exception {

		if (admin == null) {
			throw new Exception("로그인이 필요합니다.");
		}

		return admin;

	}

}
