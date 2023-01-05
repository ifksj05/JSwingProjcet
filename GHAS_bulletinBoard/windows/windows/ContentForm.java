package windows;

import java.util.Vector;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import jdbc.DbManager;

public class ContentForm extends BaseFrame {
	private String n_no;
	private String u_no;
	private String n_title;
	private String n_contents;
	private String n_mkdate;
	private BaseBt updateBt;
	private BaseBt deleteBt;
	private DbManager db;
	private Vector<Vector<String>> tmpData;
	private String u_name;

	public ContentForm(Vector<String> contentsData) {
		db = new DbManager();

		this.n_no = contentsData.get(0);
		this.u_no = contentsData.get(1);
		this.n_title = contentsData.get(2);
		this.n_contents = contentsData.get(3);
		this.n_mkdate = contentsData.get(4);
		
		tmpData = db.getData("SELECT * FROM ghas_notice.user where u_no = ?;", u_no);
		
		this.u_name = tmpData.get(0).get(3);
		
		setFrame(n_title, 500, 500);
	}

	@Override
	public void make() {
		updateBt = new BaseBt("수정");
		deleteBt = new BaseBt("삭제");
	}

	@Override
	public void disign() {
		top.addChild();
		top.left.add(new BaseLb("작성자 : " + u_name));
		top.right.add(new BaseLb("작성일 : " + n_mkdate));
		
		center.add(new BaseLb(n_contents));
		
		bottom.setFlow();
		bottom.add(updateBt);
		bottom.add(deleteBt);
		
		
	}

	@Override
	public void event() {

	}

}