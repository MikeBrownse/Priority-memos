package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	public static String imp1, imp2, imp3, imp4, isFirstUse;
	
	public void read() throws FileNotFoundException, IOException {
		String file = "setting.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(file));
		
		isFirstUse = prop.getProperty("first_use");
		imp1 = prop.getProperty("most_important");
		imp2 = prop.getProperty("the_sec");
		imp3 = prop.getProperty("the_third");
		imp4 = prop.getProperty("the_forth");
	}
	
	public void set() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("first_use", isFirstUse);
		prop.setProperty("most_important", imp1);
		prop.setProperty("the_sec", imp2);
		prop.setProperty("the_third", imp3);
		prop.setProperty("the_forth", imp4);
		
		prop.store(new FileOutputStream("setting.properties")
				, "important_and_emergency -> iae\n"
				+ "important_but_notemergency -> ibn\n"
				+ "notimportant_but_emergency -> nbe\n"
				+ "notimportant_and_notemergency -> nan\n");
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO 自动生成的方法存根
		Settings set = new Settings();
		set.read();
		System.out.println(Settings.isFirstUse + Settings.imp1 + Settings.imp2 + Settings.imp3 + Settings.imp4);
		Settings.isFirstUse = "false";
		Settings.imp1 = "iae";
		Settings.imp2 = "ibn";
		set.set();
	}

}
