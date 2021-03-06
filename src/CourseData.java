import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class CourseData {
	// test
	/**
	 * @Author Isaac Soh
	 * @version 1.0
	 * @Since November 11th 2020
	 * 
	 * @Param student_list refers to the data of each students
	 * @Param Index_data refers to the data of each classes
	 * @Param course_data refers to the data of each courses
	 */
		
	
	/**
	 * This method prints the lecturing timing of a specific class's index
	 * @Param index_no refers to the class's index the user wants to find
	 */	
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
	/**
	 * This method prints the lab timing of a specific class's index
	 * @Param index_no refers to the class's index the user wants to find
	 */
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
	/**
	 * This method prints the tutorial timing of a specific class's index
	 * @Param index_no refers to the class's index the user wants to find
	 */
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
	/**
	 * This method prints the list of class index for a course
	 * @Param course_code refers to the course code the user wants to find
	 */
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
	/**
	 * This method adds a new course 
	 * @Param vacancy refers to the number of available vacancies for each index
	 * @Param course_index refers to the list of indexes the course will have
	 * @Param lecture refers to the lecture timing
	 * @Param tutorial refers to the tutorial timing
	 * @Param Lab refers to the Lab timing
	 * @return returns nested array of course_data
	 */
	public static String [][] addCourse (String Course_Code, String Course_Name,String []Course_index, String[] vacancy, String lecutre, String [] tutorial, String [] Lab, String course_data[][], int a)
	{
		int x,y,z=0;
		y=Course_index.length;
		
		for(x=0;x<100;x++)
		{
			
			
			if(course_data[x][0] == null)
			{
				for(z=0;z<a;z++)
				{
					course_data[x][0] = Course_Code;
					course_data[x][1] = Course_Name;
					course_data[x][2] = Course_index[z];
					course_data[x][3] = vacancy[z];
					course_data[x][4] = lecutre;
					course_data[x][5] = tutorial[z];
					course_data[x][6] = Lab[z];
					x++;
				}
				return course_data;
			}
			if(course_data[x][0].equals(Course_Code))
			{
				System.out.println("Error! Course code already exist");
				break;
			}
		}
		return course_data;
	}
	
	/**
	 * 
	 * @param time1 this is the first timeslot to check against the second
	 * @param time2 this is the second timeslot to check against the first
	 * @return true if there is a clash, false if no clash 
	 */
	
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
						System.out.println("Error! You can't add this course as it clashes with your other courses");
						return true;
					}
					else {
						continue;
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * This method adds a new index to an existing course
	 * @Param Course_code refers to the course code the newly index will be long to
	 * @Param index_code refers to the index code of the new class
	 * @Param lecture refers to the lecture timing
	 * @Param tutorial refers to the tutorial timing
	 * @Param Lab refers to the Lab timing
	 * @return nested array of index_data
	 */
	public static String[][] addNewIndex (String course_code, String index_code, String vacancy, String lab, String tutorial, String[][] course_data )
	{	
		String lecture = null;
		boolean course_exist = false;
		int x,y = 0,z=0;
		for(x=0;x<100;x++)
		{
			if(course_data[x][0] == null)
				break;
			if(course_data[x][0].equals(course_code))
			{
				y=x;
				lecture = course_data[x][4];
				course_exist = true;
				
				break;
			}
		}
		if(course_exist == false)
		{
			System.out.println("Error! No such Course Code");
			return course_data;
		}
		for(x=0;x<100;x++)
		{
			if(course_data[x][0] == null)
			{
				z = x;
				break;
			}
			if(course_data[x][2].equals(index_code))
			{
				System.out.println("Index code already exist");
				return course_data;
			}
		}
		course_data[x][0] = course_code;
		course_data[x][1] = course_data[y][1];
		course_data[x][2] = index_code;
		course_data[x][3] = vacancy;
		course_data[x][4] = lecture;
		course_data[x][5] = lab;
		course_data[x][6] = tutorial;
		return course_data;
	}
	/**
	 * This methods changes the courseName of a course code
	 * @Param Course_code refers to the course code of the one we want to give a new name to
	 * @Param new_name refers to the new name of the course
	 * @return nested array of course_data
	 */
	public static String[][] changeCourseName (String course_code, String new_name, String[][] course_data)
	{
		
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(course_data[x][0]==null)
			{
				break;
			}
			if(course_data[x][0].equals(course_code))
			{
				course_data[x][1] = new_name;
				y=1;
			}
		}
		if(y==0)
		{
			System.out.println("Error! No such Course code");
		}
		return course_data;
	}
	 /**
	 * This methods changes the number of vacancies of a course code
	 * @Param Course_code refers to the course code of the one we want 
	 * @Param vacancies refers to the new total number of vacancies
	 * @return nested array of course_data
	 */
	public static String[][] changeVacancies (String index_code, String vacancies, String [][] course_data)
	{
		
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(course_data[x][0] == null)
			{
				System.out.println("No such index code");
				break;
			}
			if(course_data[x][2].equals(index_code))
			{
				course_data[x][3] = vacancies;
				break;
			}
		}
		return course_data;
	}
	/**
	 * 
	 * @return data from "CourseData.txt" file
	 * @throws FileNotFoundException
	 */
	
	public static String [][] getCourseDataArray () throws FileNotFoundException
	{	
		
		String[][] courseDatarray = new String[100][7];
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
				courseDatarray[y][0] = a; // Course Code
				courseDatarray[y][1] = b; // Course Name
				courseDatarray[y][2] = c; // Course Index
				courseDatarray[y][3] = d; // Vacancies
				courseDatarray[y][4] = e; // Lecture
				courseDatarray[y][5] = f; // Lab
				courseDatarray[y][6] = g; // Tutorial
				y++;
				
		}
		return courseDatarray;
	}
	/**
	 * This methods print the entire catalog of courses
	 * @param course_data is the nested array storing info for all courses
	 */
	public static void printCourse(String [][] course_data)
	{
		
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(course_data[x][0]==null)
				break;
			System.out.print(course_data[x][0]);
			System.out.print(" ");
			System.out.print(course_data[x][1]);
			System.out.print(" Index : ");
			System.out.println(course_data[x][2]);
		}
	}
	
}
