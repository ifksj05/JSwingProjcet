package window.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import base.IRefresh;
import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.LocalPanel;
import manager.table.TableManager;
import member.Member;
import member.MemberFactory;
import message.MessageManager;
import module.member.DeleteMember;
import module.member.ModifyMember;
import provider.memberr.MemberProvider;
import window.app.MainFrame;

public class ManageMemberFrame extends BaseFrame implements IRefresh {

	private JPanel jpNorth;
	
		private JButton jbSearch, jbRefresh, jbClose;
		private JTextField jtName;
		
	private JPanel jpList;
	
		private JScrollPane jspList;
		
			private JTable jtList;
			private DefaultTableModel jtModel;
			
	private boolean isShowAll = false;
	private int selectRow = -1;
	
	private JPopupMenu menu;
	
		private JMenuItem itemDelete, itemModify;
		
	public ManageMemberFrame() {
		super(600, 600, "회원 관리");
		refresh();
	}

	@Override
	public void setComp() {
		
		this.menu = new JPopupMenu();
		
			itemModify = new JMenuItem("수정");
			itemDelete = new JMenuItem("삭제");
			
		this.menu.add(itemModify);
		this.menu.addSeparator();
		this.menu.add(itemDelete);
			
		jpNorth = new LocalPanel(36, new GridBagLayout());
		jpNorth.setBorder(new EmptyBorder(0, 30, 0, 30));	
		
			jbSearch = new JButton("조회");
			jbRefresh = new JButton("새로고침");
			jbClose = new JButton("닫기");
		
			jtName = new JTextField();
			
			JComponent[] jcs = new JComponent[] {new JLabel("회원명"), jtName, jbSearch, jbRefresh, jbClose};
			
		for (int i = 0; i < jcs.length; i++) {
			int w = (i == 1) ? 1 : 0;
			jpNorth.add(jcs[i], new Constraint(i, 0, 1, 1, w, w, 3));
		}
		
		jpList = new LocalPanel();
		
			jspList = new JScrollPane();
			
				jtModel = new DefaultTableModel(new String[] {"membercode", "membername", "phone", "address", "regdate"}, 0) {

					@Override
					public Class<?> getColumnClass(int arg0) {
						return arg0 == 0 ? Integer.class : String.class;
					}
					
				};
				
				jtList = new JTable(jtModel) {
					
					public boolean isCellEditable(int r, int c) {
						return false;
					}
					
				};
				
				for (int i = 0; i < jtList.getColumnCount(); i++) {
					jtList.getColumnModel().getColumn(i).setCellRenderer(new ManageMemberCellRenderer());
				}
			
				jtList.getColumnModel().getColumn(2).setPreferredWidth(110);				
				jtList.getColumnModel().getColumn(3).setPreferredWidth(110);				
				jtList.getColumnModel().getColumn(4).setPreferredWidth(150);
				
				jtList.getTableHeader().setReorderingAllowed(false);
				jtList.setAutoCreateRowSorter(true);
				jtList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
				
			jspList.getViewport().setView(jtList);
		
		jpList.add(jspList);
		
		
	}

	@Override
	public void setDesign() {
		add(jpNorth, BorderLayout.NORTH);
		add(jpList, BorderLayout.CENTER);
	}

	@Override
	public void setAction() {
		
		itemModify.addActionListener(e -> {
			
			try {
				
				Member member = MemberProvider.getMember((int) jtList.getValueAt(selectRow, 0));
				
				dispose();
				
				ModifyMemberFrame mmf = new ModifyMemberFrame();
				mmf.setMember(member);
				mmf.setVisible(true);
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			} finally {
				clearSelection();
			}
			
		});
		
		itemDelete.addActionListener(e -> {
			
			try {
				
				Member member = new Member();
				member.setMemberCode((int) jtList.getValueAt(selectRow, 0));
				member.setMemberName((String) jtList.getValueAt(selectRow, 1));
				
				if (MessageManager.getConfirm(member.getMemberName() + "회원의 정보를 삭제하시겠습니까?", "회원 삭제")) {
					new DeleteMember().deleteMember(member);
					refresh();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			} finally {
				clearSelection();
			}
			
		});
		
		this.jtList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				if (arg0.getButton() == 3 ) {
					
					selectRow = jtList.rowAtPoint(arg0.getPoint());
					menu.show(jtList, arg0.getX(), arg0.getY());
					repaint();
					
				} else {
					clearSelection();
				}
 				
			}
			
		});
 		
		this.jbSearch.addActionListener(e -> {
			isShowAll = false;
			refresh();
		});
		
		this.jbRefresh.addActionListener(e -> {
			isShowAll = true;
			refresh();
		});
		
		this.jbClose.addActionListener(e -> {
			close();
		});
			
	}

	public void clearSelection() {
		
		selectRow = -1;
		repaint();
		
	}
	
	@Override
	public void close() {
		dispose();
		new MainFrame().setVisible(true);
	}

	@Override
	public void refresh() {
		
		try {
				
			clearSelection();
			TableManager.fillRows(jtModel, new MemberFactory(), "select * from member where true = ? or membername like concat('%', ?, '%')", isShowAll, jtName.getText() );
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}
	
	public class ManageMemberCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {
			
			Component c = super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
			
			if (selectRow == arg4) {
				c.setBackground(Color.yellow);
			} else {
				c.setBackground(Color.white);
			}
			
			return c;
			
		}
		
	}

}
