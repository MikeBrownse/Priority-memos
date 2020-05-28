package window;

import java.awt.Checkbox;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import data.Events;

public class GetTable {
	public static JTable getTable(Events[] arr, int listcnt) {
		String[] column = new String[] {"完成", "标题", "截止时间", "详情"};
		Object[][] row = new Object[listcnt][4];
		
		for(int cnt = 0; cnt < listcnt; cnt++) {
			row[cnt][0] = new Checkbox();
			if(arr[cnt].getState() == "完成") {
				row[cnt][0] = (Boolean) true;
			}else {
				row[cnt][0] = (Boolean) false;
			}
			
			row[cnt][1] = arr[cnt].getTitle();
			row[cnt][2] = arr[cnt].getStringTime(arr[cnt].getDeadline());
			row[cnt][3] = arr[cnt].getDetials();
		}
		
		JTable j = new JTable(row, column);
		j.setFillsViewportHeight(true);
		TableColumnModel tcm = j.getColumnModel();
		tcm.getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
//		tcm.getColumn(0).setWidth(10);
//		tcm.getColumn(2).setWidth(20);
		
		return j;
	}
}
