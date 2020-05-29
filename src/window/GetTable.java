package window;

import javax.swing.JTable;
import data.Events;

public class GetTable {
	public static JTable getTable(Events[] arr, int listcnt) {
		String[] column = new String[] {"待办ID", "标题", "完成", "截止时间", "详情"};
		Object[][] row = new Object[listcnt][5];
		
		for(int cnt = 0; cnt < listcnt; cnt++) {
			row[cnt][0] = arr[cnt].getEventID();
			row[cnt][1] = arr[cnt].getTitle();
			row[cnt][2] = arr[cnt].getState();
			row[cnt][3] = arr[cnt].getStringTime(arr[cnt].getDeadline());
			row[cnt][4] = arr[cnt].getDetials();
		}
		
		JTable j = new JTable(row, column);
		j.setFillsViewportHeight(true);
		
		return j;
	}
}
