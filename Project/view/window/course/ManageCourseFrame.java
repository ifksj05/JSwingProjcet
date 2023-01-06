package window.course;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import base.IRefresh;
import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.LocalPanel;
import course.Course;
import course.CourseFactory;
import format.FormatField;
import manager.table.TableManager;
import message.MessageManager;
import module.course.AddCourse;
import module.course.DeleteCourse;
import provider.PubProivider;
import window.app.MainFrame;

public class ManageCourseFrame extends BaseFrame implements IRefresh {

	private JPanel jpNorth;
	
		private JPanel jpInput;
		
			private FormatField jtCourse, jtTeach;
			private JComboBox<Integer> jcPrice;
			private JButton jbSearch;
	
		private JPanel jpButton;
		
			private JButton jbAdd, jbModify, jbDelete, jbShowAll, jbClose;
		
	private JPanel jpCenter;
			
		private JPanel jpTitle;
			
			private JCheckBox jckAll;
	
		private JPanel jpList;
		
			private JScrollPane jspList;
			
				private JTable jtList;
				private DefaultTableModel jtModel;
				
	private boolean isShowAll = true;
	private List<Course> cList = null;
	
	public ManageCourseFrame() {
		super(600, 600, "���� ����");
		refresh(); 
	}

	@Override
	public void setComp() {
		
		jpNorth = new LocalPanel(95, new BorderLayout());
		
			jpInput = new LocalPanel(new GridBagLayout());
			jpInput.setBorder(new EmptyBorder(5, 20, 5, 20));
			
				jtCourse = new FormatField(30);
				jtTeach = new FormatField(20);
			
				jcPrice = new JComboBox<>();
				
				for (int i = 60000; i <= 130000; i += 10000) {
					jcPrice.addItem(i);
				}
				
				jbSearch = new JButton("��ȸ");
				
				String[] cols = new String[] {"*�� �� �� ", "*�� �� ��", "�� �� ��", ""};
				JComponent[] jcs = new JComponent[] {jtCourse, jtTeach, jcPrice, jbSearch};
				
			for (int i = 0; i < jcs.length; i++) {
				jpInput.add(new JLabel(cols[i], JLabel.CENTER), new Constraint(i, 0, 1, 1, 0, 0, 0));
				jpInput.add(jcs[i], new Constraint(i, 1, 1, 1, 1, 1, 0));
			}
				
			jpButton = new LocalPanel(36, new GridBagLayout());
			
				jbAdd = new JButton("���");
				jbModify = new JButton("����");
				jbDelete = new JButton("����");
				jbShowAll = new JButton("��ü����");
				jbClose = new JButton("�ݱ�");
			
				jcs = new JComponent[] {jbAdd, jbModify, jbDelete, jbShowAll, jbClose};
				
			for (int i = 0; i < jcs.length; i++) {
				jpButton.add(jcs[i], new Constraint());
			}
			
		jpNorth.add(jpInput, BorderLayout.CENTER);
		jpNorth.add(jpButton, BorderLayout.SOUTH);
			
		jpCenter = new LocalPanel();
		
			jpTitle = new LocalPanel(35, new BorderLayout());
		
				jckAll = new JCheckBox("����All");
			
			jpTitle.add(jckAll, BorderLayout.WEST);
			jpTitle.add(new JLabel("<�� �� �� Ȳ>", JLabel.CENTER), BorderLayout.CENTER);
				
			jpList = new LocalPanel();
				
				jspList = new JScrollPane();
				
					jtModel = new DefaultTableModel(new String[] {"����All", "coursename", "teachername", "price"}, 0);
					
					jtList = new JTable(jtModel) {
						public boolean isCellEditable(int r, int c) {
							return false;
						}
					};
					
					for (int i = 0; i < jtList.getColumnCount(); i++) {
						jtList.getColumnModel().getColumn(i).setCellRenderer(new ManageCourseCellRenderer());
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
		
			
		jbModify.addActionListener(e -> {
			
			try {
				
				Vector<Integer> rows = getSelectedRows();
				
				if (rows.size() == 0) {
					throw new Exception("������ ���¸� �����ϼ���");
				} else if (rows.size() > 1) {
					throw new Exception("������ ���¸� �� �� �� �����ϼ���");
				}
				
				dispose();
				
				ModifyCourseFrame mcf = new ModifyCourseFrame();
				mcf.setCourse(cList.get(rows.get(0)));
				mcf.setVisible(true);
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "�޽���");
			}
			
		});
		
		jbDelete.addActionListener(e -> {
			
			try {
				
				Vector<Integer> rows = getSelectedRows();
				
				if (rows.size() == 0) {
					throw new Exception("������ ���¸� �����ϼ���");
				}
				
				String text = ((rows.size() > 1) ? "�����Ͻ� ���" : cList.get(rows.get(0)).getCourseName()) + " ���¸� �����Ͻðڽ��ϱ�?";
				
				if (MessageManager.getConfirm(text, "���� ����")) {
					
					DeleteCourse dc = new DeleteCourse();
					
					for (int i = 0; i < rows.size(); i++) {
						dc.deleteCourse(cList.get(rows.get(i)));
					}
					
					MessageManager.showMsg("�����Ǿ����ϴ�.");
					isShowAll = true;
					refresh();
					
				}
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "�޽���");
			}
			
		});
		
		jckAll.addActionListener(e -> {
			
			for (int i = 0; i < jtList.getRowCount(); i++) {
				
				jtList.setValueAt(jckAll.isSelected(), i, 0);

			}
			
		});
		
		jtList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				if (arg0.getButton() == 1) {
					
					int rowIndex = jtList.rowAtPoint(arg0.getPoint());
					int colIndex = jtList.columnAtPoint(arg0.getPoint());
					
					if (colIndex == 0) {
						
						jtList.setValueAt(!(boolean) jtList.getValueAt(rowIndex, 0), rowIndex, 0);
						jckAll.setSelected(jtList.getRowCount() == getSelectedRows().size());
						
					}
					
					jtList.clearSelection();
					
				}
				
			}
			
		});
		
		jbAdd.addActionListener(e -> {
			
			try {
				
				if (jtCourse.getText().equals("") || jtTeach.getText().equals("")) {
					throw new Exception("�ʼ��׸�(*)�� ��� �Է��ϼ���");
				}
				
				
				String cName = jtCourse.getText();
				String tName = jtTeach.getText();
				
				int price = (int) jcPrice.getSelectedItem();
				
				Course course = new Course();
				
					course.setCourseName(cName);
					course.setTeachName(tName);
					course.setPrice(price);
					
				new AddCourse().addCourse(course);

				isShowAll = true;
				refresh();
				
				MessageManager.showMsg("�߰��� �Ϸ�Ǿ����ϴ�.", "�޽���");
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(),"�޽���");
			}
			
		});
		
		jbModify.addActionListener(e -> {});
		
		jbDelete.addActionListener(e -> {});
		
		
		jbShowAll.addActionListener(e -> {
			isShowAll = true;
			refresh();
		});
		
		jbSearch.addActionListener(e -> {
			isShowAll = false;
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
		
		String cName = jtCourse.getText();
		String tName = jtTeach.getText();
		
		int price = (int) jcPrice.getSelectedItem();
		
		try {
			
			String sql = "select * from course where true = ? or ( "
					+ "(coursename like concat('%', ?, '%') or '' = ?) and "
					+ "(teachname like concat('%', ?, '%') or '' = ?) and "
					+ "(price >= ? and price <= ?))";
			
			
			cList = PubProivider.getModelList(new CourseFactory(), sql, isShowAll, cName, cName, tName, tName, price, price);
			
			TableManager.clearRows(jtModel);
			
			for (Course course : cList) {
				
				jtModel.addRow(new Object[] {
						false,
						course.getCourseName(),
						course.getTeachName(),
						course.getPrice()
				});
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}
	
	public class ManageCourseCellRenderer extends DefaultTableCellRenderer {
		
		public ManageCourseCellRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {
			
			Component c = super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
			
			if (arg5 == 0) {
				
				JCheckBox chk = new JCheckBox();
				
				chk.setSelected((boolean) jtList.getValueAt(arg4, 0));
				chk.setHorizontalAlignment(JCheckBox.CENTER);
				
				return chk;
				
			}
			
			return c;
			
		}
		
		
		
	}

}
