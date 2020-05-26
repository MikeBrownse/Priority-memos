package data;

import java.util.Calendar;

public class Event {

	private String title, detials;
	private Calendar startime, deadline;
	private int priority, state;
		
	Event(String title, Calendar startime, Calendar deadline, int priority, int state){
		this.setTitle(title);
		this.setStartime(startime);
		this.setDeadline(deadline);
		this.setPriority(priority);
		this.setState(state);
	}
	
	//写入
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setPriority(int newPriority) {
		priority = newPriority;
	}
	
	public void setState(int newState) {
		state = newState;
	}
	
	public void setDetials(String newDetials) {
		detials = newDetials;
	}
	
	public void setStartime(Calendar newStartime) {
		startime = newStartime;
	}
	
	public void setDeadline(Calendar newDeadline) {
		deadline = newDeadline;
	}
	
	//读取
	public String getTitle() {
		return title;
	}
	
	public String getDetials() {
		return detials;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public int getState() {
		return state;
	}
	
	public Calendar getStartime() {
		return startime;
	}
	
	public Calendar getDeadline() {
		return deadline;
	}
	
	//时间
	public String getStringTime(Calendar c) {
//		return String.format("%04d年%02d月%02日 %02d:%02d", c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH),
//				c.get(c.HOUR_OF_DAY), c.get(c.MINUTE));
		return (c.get(c.YEAR) + "年" + c.get(c.MONTH) + "月" + c.get(c.DAY_OF_MONTH) + "日 "
				+ c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//event类测试
//		Event eve = new Event("hello", "today", "tomorrow", 1, 0);
//		System.out.println(eve.getTitle() + eve.getState());
		
		//日期生成、转换测试
//		Calendar c = Calendar.getInstance();
//		c.set(2020, 5, 25, 23, 8);
//		System.out.println(c.get(c.YEAR) + "-" + c.get(c.MONTH) + "-" + c.get(c.DAY_OF_MONTH) + " "
//				+ c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
//		Date d = c.getTime();
//		Calendar c1 = Calendar.getInstance();
//		c1.setTime(d);
//		System.out.println(c1.get(c.YEAR) + "-" + c1.get(c.MONTH) + "-" + c1.get(c.DAY_OF_MONTH) + " "
//				+ c1.get(c.HOUR_OF_DAY) + ":" + c1.get(c.MINUTE) + ":" + c1.get(c.SECOND));
		
		//event + calendar test
		Calendar stt = Calendar.getInstance();
		Calendar ddl = Calendar.getInstance();
		stt.set(2020, 5, 25, 23, 8);
		ddl.set(2020, 5, 30, 8, 10);
		Event e = new Event("competetion", stt, ddl, 1, 1);
		System.out.println(e.getStringTime(e.startime));
		System.out.println(e.getStringTime(e.deadline));
	}

}
