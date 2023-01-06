package window.reserve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import base.IRefresh;
import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.LocalPanel;
import comp.CourseList;
import comp.MemberList;
import course.Course;
import course.CourseFactory;
import course.sub.Summary;
import course.sub.SummaryFactory;
import coursemanage.CourseManage;
import coursemanage.CourseManageFactory;
import manager.table.TableManager;
import member.Member;
import message.MessageManager;
import module.course.AddCourse;
import module.course.DeleteCourse;
import module.reserve.DeleteReserve;
import module.reserve.SaveReserve;
import provider.PubProivider;
import window.app.MainFrame;

public class ManageReserveFrame extends BaseFrame implements IRefresh {

	private JPanel jpNorth;
	
		private JPanel jpInput;
		
			private MemberList mList;
			private CourseList cList;
			
			private JTextField jtName;
		
		private JPanel jpButton;
		
			private JButton jbAdd, jbSave, jbChart, jbDelete, jbShowAll, jbClose;
		
	private JPanel jpCenter;
			 
		private JPanel jpTitle;
	
			private JPanel jpOption;
			
				private JRadioButton jrbDefault, jrbCouseName, jrbYear;
		
		private JPanel jpList;
		
			private JScrollPane jspList;
			
				private JTable jtList;
				private DefaultTableModel jtModel;
				
	private boolean isShowAll = true;
	private List<CourseManage> cmList = null;
	
	public ManageReserveFrame() {
		
		super(700, 700, "수강신청및관리");
		refresh(); 
		
		if (mList.getItemCount() > 0) {
			mList.setSelectedIndex(0);
		}
		
	}

	@Override
	public void setComp() {
		
		jpNorth = new LocalPanel(95, new BorderLayout());
		
			jpInput = new LocalPanel(new GridBagLayout());
			jpInput.setBorder(new EmptyBorder(10, 20, 10, 20));
			
				mList = new MemberList();
				mList.setPreferredSize(new Dimension(0, 0));
				
				cList = new CourseList();
				cList.setPreferredSize(new Dimension(0, 0));
				
				jtName = new JTextField();
				jtName.setPreferredSize(new Dimension(0, 0));
				
				jtName.setEditable(false);
				jtName.setBorder(new LineBorder(Color.black));
				jtName.setBackground(Color.white);
				
				String[] cols = new String[] {"강 좌 명 :", "회 원 코 드 :", "회 원 명 :"};
				JComponent[] jcs = new JComponent[] {cList, mList, jtName};
				
			for (int i = 0; i < jcs.length; i++) {
				jpInput.add(new JLabel(cols[i], JLabel.CENTER), new Constraint(i * 2, 0, 1, 1, 0, 0, 3));
				jpInput.add(jcs[i], new Constraint((i * 2) + 1, 0, 1, 1, 1, 1, 3));
			}
				
			jpButton = new LocalPanel(36, new GridBagLayout());
			
				jbAdd = new JButton("수강신청");
				jbDelete = new JButton("삭제");
				jbShowAll = new JButton("전체보기");
				jbSave = new JButton("파일로저장");
				jbChart = new JButton("강좌별차트");
				jbClose = new JButton("닫기");
			
				jcs = new JComponent[] {jbAdd, jbDelete, jbShowAll, jbSave, jbChart, jbClose};
				
			for (int i = 0; i < jcs.length; i++) {
				jpButton.add(jcs[i], new Constraint());
			}
			
		jpNorth.add(jpInput, BorderLayout.CENTER);
		jpNorth.add(jpButton, BorderLayout.SOUTH);
			
		jpCenter = new LocalPanel();
		
			jpTitle = new LocalPanel(65, new BorderLayout());
		
				jpOption = new LocalPanel(30, new GridBagLayout());
				
					ButtonGroup bg = new ButtonGroup();
					
						jrbDefault = new JRadioButton("Default");
						jrbDefault.setSelected(true);
						
						jrbCouseName = new JRadioButton("CourseName Summary");
						jrbYear = new JRadioButton("Yearly Summary");
					
					bg.add(jrbDefault);
					bg.add(jrbCouseName);
					bg.add(jrbYear);
						
				jpOption.add(jrbDefault);
				jpOption.add(jrbCouseName);
				jpOption.add(jrbYear);
					
			jpTitle.add(jpOption, BorderLayout.SOUTH);
			jpTitle.add(new JLabel("<수 강 신 청 현 황>", JLabel.CENTER), BorderLayout.CENTER);
				
			jpList = new LocalPanel();
				
				jspList = new JScrollPane();
				
					jtModel = new DefaultTableModel(new String[] {"registercode", "membercode", "membername", "coursename", "courseprice", "period", "regprice", "crregdate"}, 0);
					
					jtList = new JTable(jtModel) {
						public boolean isCellEditable(int r, int c) {
							return false;
						}
					};
					
					for (int i = 0; i < jtList.getColumnCount(); i++) {
						DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
						dtcr.setHorizontalAlignment(SwingConstants.CENTER);
						jtList.getColumnModel().getColumn(i).setCellRenderer(dtcr);
					}
				
					jtList.getTableHeader().setReorderingAllowed(false);
					jtList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
					
				jspList.getViewport().setView(jtList);
			
			jpList.add(jspList);
		
		jpCenter.add(jpTitle, BorderLayout.NORTH);
		jpCenter.add(jpList, BorderLayout.CENTER);
			
	}

	@Override
	public void setDesign() {
		add(jpNorth, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
	}

	@Override
	public void setAction() {
		
		jrbDefault.addActionListener(e -> {
			refresh();
		});
		
		jrbCouseName.addActionListener(e -> {
			refresh();
		});
		
		jrbYear.addActionListener(e -> {
			refresh();
		});
		
		jbAdd.addActionListener(e -> {
			dispose();
			new AddReserveFrame().setVisible(true);
		});
		
		jbChart.addActionListener(e -> {
			dispose();
			new ChartFrame().setVisible(true);
		});
		
		jbSave.addActionListener(e -> {
			
			try {
				
				Course course = (Course) cList.getSelectedItem();
				
				String defaultSQL = "select * from coursemanage where true = ? or coursename = ? order by registercode desc";
				cmList = PubProivider.getModelList(new CourseManageFactory (), defaultSQL, isShowAll, course.getCourseName());
				
				List<CourseManage> filterList = cmList.stream().filter(c -> {return c.getCourseName().equals(course.getCourseName());}).sorted(Comparator.reverseOrder()).collect(Collectors.toList()); 
				
				new SaveReserve().saveReserve(filterList, course);
				
				MessageManager.showMsg("저장이 완료되었습니다.");
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			}
			
		});
		
		jbDelete.addActionListener(e -> {
			
			try {
				
				int selectRow = jtList.getSelectedRow();
				if (selectRow == -1) {
					throw new Exception("삭제할 데이터를 선택해주십시오.");
				}
				
				CourseManage cm = cmList.get(selectRow);
				
				if (MessageManager.getConfirm("등록코드" + cm.getRegisterCode() + "를 삭제하시겠습니까?", "수강정보 삭제")) {
					
					new DeleteReserve().deleteReserve(cm);
					refresh();
					
					MessageManager.showMsg("삭제가 완료되었습니다.");
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			}
			
		});
		
		
		mList.addActionListener(e -> {
			
			Member m = (Member) mList.getSelectedItem();
			jtName.setText(m.getMemberName());
			
		});
		
		jbShowAll.addActionListener(e -> {
			isShowAll = true;
			jrbDefault.setSelected(true);
			refresh();
		});
		
		cList.addActionListener(e -> {
			isShowAll = false;
			jrbDefault.setSelected(true);
			refresh();
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	public Vector<Integer> getSelectedRows() {
		
		Vector<Integer> rows = new Vector<>();
		
		for (int i = 0; i < jtList.getRowCount(); i++) {
			
			if ((boolean) jtList.getValueAt(i, 0)) {
				rows.add(i);
			}
			
		}
		
		return rows;
		
	}
	
	@Override
	public void close() {
		dispose();
		new MainFrame().setVisible(true);
	}

	@Override
	public void refresh() {
		
		Course course = (Course) cList.getSelectedItem();
		
		try {
			
			
			
			if (jrbDefault.isSelected()) {
				
				jtModel = new DefaultTableModel(new String[] {"registercode", "membercode", "membername", "coursename", "courseprice", "period", "regprice", "crregdate"}, 0);
				TableManager.clearRows(jtModel);
				
				jtList.setModel(jtModel);
				
				String defaultSQL = "select * from coursemanage where true = ? or coursename = ? order by registercode desc";
				cmList = TableManager.fillRows(jtModel, new CourseManageFactory (), defaultSQL, isShowAll, course.getCourseName());
				
			} else if (jrbCouseName.isSelected()) {
				
				String sql = "select*, sum(regprice) as price, count(coursename) as count  from coursemanage group by coursename;";
				
				jtModel = new DefaultTableModel(new String[] {"강좌명", "등록 수강료합계", "등록 회원 수"}, 0);
				jtList.setModel(jtModel);
				
				List<Summary> sList = PubProivider.getModelList(new SummaryFactory(), sql);
				
				for (Summary summary : sList) {
					jtModel.addRow(new Object[] {summary.getCourseName(), new DecimalFormat("#,##0").format(summary.getPrice()), summary.getCount()});
				}
				
			}
			
			for (int i = 0; i < jtList.getColumnCount(); i++) {
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
				jtList.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}
}
