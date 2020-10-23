import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class CourseData {
	public static void main (String args[]) throws FileNotFoundException
	{
		String[][] test = new String[100][7];
		String a,b,c,d,e,f,g;
		int y = 0;
		int z =0;
		Scanner x = new Scanner(new File("CourseData.txt"));
		x.useDelimiter("[,\n]");
		while(x.hasNext())
		{		
				a=x.next().trim();
				b=x.next().trim();
				c=x.next().trim();
				d=x.next().trim();
				e=x.next().trim();
				f=x.next().trim();
				g=x.next().trim();
				test[y][0] = a; // Course Code
				test[y][1] = b; // Course Name
				test[y][2] = c; // Course Index
				test[y][3] = d; // Vacancies
				test[y][4] = e; // Lecture
				test[y][5] = f; // Lab
				test[y][6] = g; // Tutorial
				y++;
				
		}
		for(y = 0;y<4;y++)
		{
			for(z=0;z<7;z++)
			{
				System.out.print(test[y][z] + " ");
			}
			System.out.println("");
			
		}
		/*
		System.out.println("Get Lecture");
		getLecture("J01",test);
		System.out.println("Get Lab");
		getLab("J01",test);
		System.out.println("Get tutorial");
		getTutorial("J01",test);
		System.out.println("Get Index");
		getIndex("Algorithms",test);
		*/
		

	}
	public static void getLecture (String index_no ,String test[][])
	{
		int x,y =0;
		for(x=0;x<4;x++)
		{
			if(test[x][2].equals(index_no))
			{
				System.out.println(test[x][4]);
			}
		}
	}
	
	public static void getLab (String index_no ,String test[][])
	{
		int x,y =0;
		for(x=0;x<4;x++)
		{
			if(test[x][2].equals(index_no))
			{
				System.out.println(test[x][5]);
			}
		}
	}
	
	public static void getTutorial (String index_no ,String test[][])
	{
		int x,y =0;
		for(x=0;x<100;x++)
		{
			if(test[x][2] == null)
			{
				break;
			}
			if(test[x][2].equals(index_no))
			{
				System.out.println(test[x][6]);
			}
		}
	}
	
	public static void getIndex (String course_code, String test[][])
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(test[x][1] == null)
			{
				break;
			}
			if(test[x][1].equals(course_code))
			{
				System.out.println(test[x][2]);
			}
		}
	}
	public static String [][] addCourse (String Course_Code, String Course_Name,String []Course_index, String[] vacancy, String lecutre, String [] tutorial, String [] Lab, String test[][], int a)
	{
		int x,y,z=0;
		y=Course_index.length;
		for(x=0;x<100;x++)
		{
			if(test[x][0] == null)
			{
				for(z=0;z<a;z++)
				{
					test[x][0] = Course_Code;
					test[x][1] = Course_Name;
					test[x][2] = Course_index[z];
					test[x][3] = vacancy[z];
					test[x][4] = lecutre;
					test[x][5] = tutorial[z];
					test[x][6] = Lab[z];
					x++;
				}
				return test;
			}
		}
		return test;
	}
	
	public static boolean checkClash(String time1, String time2) {
		if(time1.contains("NULL")||time2.contains("NULL")) {
			return false;
		}
		//05-1030-1230 DD-HHMM-HHMM 
		String[] time1_split = time1.split("-");
		String[] time2_split = time2.split("-");
		//System.out.println(time1_split[0] + "test");
		//check if same day 
		int day1 = Integer.parseInt(time1_split[0]);
		int day2 = Integer.parseInt(time2_split[0]);
		if(day1==day2) {
			//get HH for start and end of time1 since assuming all classes start at xx.30 
			int time1_start = Integer.parseInt(time1_split[1].substring(0,2));
			int time1_end = Integer.parseInt(time1_split[2].substring(0,2));
			int time2_start = Integer.parseInt(time2_split[1].substring(0,2));
			int time2_end = Integer.parseInt(time2_split[2].substring(0,2));

			if (time1_start == time2_start) return true; //same day, exact same start time
			
			//find duration 
			int time1_dur = time1_end - time1_start;
			int time2_dur = time2_end - time2_start;
			for(int i = 0; i < time1_dur; i++) {
					if((time1_start+i==time2_start) || (time2_start+i==time1_start)){
						return true;
					}
					else {
						continue;
				}
			}
		}
		
		return false;
		
	}
	
}
