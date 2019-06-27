package com.cosmose.demoHotel.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tool {
	public static String readJsonData(String path){

		BufferedReader reader = null;
		StringBuffer sb = null;
        
        try {
        	sb = new StringBuffer();
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			
			reader  = new BufferedReader(inputStreamReader);
			
			String str;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = Tool.readJsonData("src/test/java/com/cosmose/demoHotel/jsonTemplate/reservation.json");
		System.out.print(str);
	}
}
