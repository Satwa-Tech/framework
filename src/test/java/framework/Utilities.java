package framework;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class Utilities {
	
	public void createFolder(String folderPath) {
		File file = new File(folderPath);
		
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static boolean isFileExists(String filePath) {
		File file = new File(filePath);
		
		return file.exists();
	}
	
	public void deleteFile(String path) {
		File file = new File(path);
		
		if (file.exists()) {
			file.delete();
		}
	}
	
	
	public void delete_all_files_in_folder(String folderPath) {
		File folder = new File(folderPath);
		
		File[] allFiles = folder.listFiles();
		for (File file:allFiles) {
			try {			
				file.delete();			
			} catch (Exception e) {
				System.out.println("file:"+ file.getName()+" is not deleted.");
			}
		
		}
	}
	
	public String getCurrentTimeStamp() {
		Date dt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		
		return ""+month+day+hour+minute+seconds;
		
	}
	
	public void createNewFile(String filePath) {
		File file = new File(filePath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
