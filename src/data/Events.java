package data;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Events {

	private String title, detials, priority, state;
	private Calendar deadline;
	private int eventID;
		
	public Events(int eventID, String title, String priority, String state, Calendar deadline, String details){
		this.setEventID(eventID);
		this.setTitle(title);
		this.setPriority(priority);
		this.setState(state);
		this.setDeadline(deadline);
		this.setDetials(details);
	}
	
	//д��
	public void setEventID(int neweventID) {
		eventID = neweventID;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setPriority(String newPriority) {
		priority = newPriority;
	}
	
	public void setState(String newState) {
		state = newState;
	}
	
	public void setDetials(String newDetials) {
		detials = newDetials;
	}
	//����ʱ��//ע��calendar��ĳ�ʼ�·�Ϊ0
	public void setDeadline(Calendar newDeadline) {
		deadline = newDeadline;
	}
	
	//��ȡ
	public int getEventID() {
		return eventID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDetials() {
		return detials;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public String getState() {
		return state;
	}
	
	public Calendar getDeadline() {
		return deadline;
	}
	
	public void getEventInfo() {
		System.out.println(this.getEventID() + " "
				+ this.getTitle() + " "
				+ this.getPriority() + " "
				+ this.getState() + " "
				+ this.getStringTime(this.getDeadline()) + " "
				+ this.getDetials());
	}
	//ʱ��
	public String getStringTime(Calendar c) {
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��  HH:mm");
		return sdf.format(d);
	}
}
