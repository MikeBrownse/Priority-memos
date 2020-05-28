package window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.*;

import data.Database;
import data.Events;
import data.QuickSort;

class newEventDialog extends JDialog {
	protected newEventDialog(JFrame upperFrame) {
		JDialog jd = new JDialog(upperFrame, "新待办", true);
		Container jdc = jd.getContentPane();
		jdc.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JTextField title = new JTextField("", 10);
		final JTextField details = new JTextField("", 10);
		final JTextField year = new JTextField("", 4);
		final JTextField month = new JTextField("", 2);
		final JTextField day = new JTextField("", 2);
		final JTextField hour = new JTextField("", 2);
		final JTextField min = new JTextField("", 2);
		final JButton submmit = new JButton("生成"); 
		
		String[] priority = new String[] {"请选择", "重要且紧急", "重要但不紧急", "不重要但紧急", "不重要且不紧急"};
		final JComboBox<String> priCombo = new JComboBox<String>(priority);
		
		submmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				Calendar c = Calendar.getInstance();
				//若jtextfield内容为空，出错
				c.set(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(min.getText()));
				c.add(c.MONTH, -1);
				Events e = new Events(0, title.getText(), (String)priCombo.getSelectedItem(), "未完成", c, details.getText());
//				e.getEventInfo(e);
				
				Database db = new Database();
				try {
					db.init();
					db.save(e);
					db.close();
				} catch (ClassNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				jd.dispose();
			}
		});
		
		p1.add(new JLabel("待办标题："));
		p1.add(title);
		p2.add(new JLabel("重要程度："));
		p2.add(priCombo);
		p3.add(new JLabel("截止时间："));
		p3.add(year);
		p3.add(new JLabel("年"));
		p3.add(month);
		p3.add(new JLabel("月"));
		p3.add(day);
		p3.add(new JLabel("日 "));
		p3.add(hour);
		p3.add(new JLabel(":"));
		p3.add(min);
		p4.add(new JLabel("待办详情："));
		p4.add(details);
		p5.add(submmit);
		
		jdc.add(p1);
		jdc.add(p2);
		jdc.add(p3);
		jdc.add(p4);
		jdc.add(p5);
		
		jd.setSize(600, 300);
		jd.setResizable(false);
		jd.setLocationRelativeTo(null);
		jd.setVisible(true);
	}
}

public class MainProceed extends JFrame {

	MainProceed() throws NumberFormatException, ClassNotFoundException, SQLException {
		JFrame f = new JFrame("待办");
		Container con = f.getContentPane();
		con.setLayout(new GridLayout(3, 1));
		
		Container c1 = new Container();
		c1.setLayout(new GridLayout(2, 2));
		Container c2 = new Container();
		c2.setLayout(new GridLayout(2, 2));
		
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel text1 = new JPanel(new BorderLayout());
		JPanel text2 = new JPanel(new BorderLayout());
		JPanel text3 = new JPanel(new BorderLayout());
		JPanel text4 = new JPanel(new BorderLayout());
		
		final JButton newEvent = new JButton("新待办");
		final JButton refresh = new JButton("刷新");
		
		newEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new newEventDialog(f);
				f.dispose();
				try {
					new MainProceed();
				} catch (NumberFormatException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				f.dispose();
				try {
					new MainProceed();
				} catch (NumberFormatException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		
		//test in db
		Database db = new Database();
		db.init();
		Events e = db.read();
		int iaecnt = 0, ibucnt = 0, uaucnt = 0, ubecnt = 0;
		Events[] iaearr = new Events[100];
		Events[] ibuarr = new Events[100];
		Events[] uauarr = new Events[100];
		Events[] ubearr = new Events[100];
		
		while(e != null) {
			switch(e.getPriority()) {
			case"重要且紧急": 
			case"iae":
				iaearr[iaecnt] = e;
				iaecnt++;
				break;
			case"重要但不紧急": 
			case"ibu":
				ibuarr[ibucnt] = e;
				ibucnt++;
				break;
			case"不重要且不紧急": 
			case"uau":
				uauarr[uaucnt] = e;
				uaucnt++;
				break;
			case"不重要但紧急":
			case"ube":
				ubearr[ubecnt] = e;
				ubecnt++;
				break;
			}
			e = db.read();
		}
		db.close();
		
		QuickSort.quickSort(iaearr, 0, iaecnt - 1);
		QuickSort.quickSort(ibuarr, 0, ibucnt - 1);
		QuickSort.quickSort(ubearr, 0, ubecnt - 1);
		QuickSort.quickSort(uauarr, 0, uaucnt - 1);		
		
		JTable iaetable = GetTable.getTable(iaearr, iaecnt);
		JTable ibutable = GetTable.getTable(ibuarr, ibucnt);
		JTable uautable = GetTable.getTable(uauarr, uaucnt);
		JTable ubetable = GetTable.getTable(ubearr, ubecnt);
		
//		JCheckBox jc1 = new JCheckBox();
//		iaetable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jc1));
		
		iaetable.setRowHeight(22);
		ibutable.setRowHeight(22);
		uautable.setRowHeight(22);
		ubetable.setRowHeight(22);
		
		JScrollPane iaescrollPane = new JScrollPane(iaetable);
		JScrollPane ibuscrollPane = new JScrollPane(ibutable);
		JScrollPane uauscrollPane = new JScrollPane(uautable);
		JScrollPane ubescrollPane = new JScrollPane(ubetable);
		
		p5.add(newEvent);
		p5.add(refresh);
		text1.add(new JLabel("重要且紧急", JLabel.CENTER), BorderLayout.SOUTH);
		text2.add(new JLabel("重要但不紧急", JLabel.CENTER), BorderLayout.SOUTH);
		text3.add(new JLabel("不重要但紧急", JLabel.CENTER), BorderLayout.SOUTH);
		text4.add(new JLabel("不重要且不紧急", JLabel.CENTER), BorderLayout.SOUTH);
		
		c1.add(text1);
		c1.add(text2);
		c1.add(iaescrollPane);
		c1.add(ibuscrollPane);
		c2.add(text3);
		c2.add(text4);
		c2.add(ubescrollPane);
		c2.add(uauscrollPane);
		
		con.add(c1);
		con.add(c2);
		con.add(p5);
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(1200, 400);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			new MainProceed();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
