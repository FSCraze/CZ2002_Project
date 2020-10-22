import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Map.Entry;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;
import java.io.FileReader;

public class MainPage {
	public static void main(String args[]) throws NoSuchAlgorithmException, IOException
	{
		String a,b,c,d,e,f,g,h,i;
		String [][] course_data = getCourseDataArray();
		String [][] index_data = getIndexData();
		String [][] student_data = getStudentData();
		String [] noOfIndex = new String[10];
		String [] LabTiming = new String[10];
		String [] TutorialTiming = new String[10];
		String [] Vacancies = new String[10];
		int choice;
		int x,y,z;
		String buffer;
		String temp = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username");
		String userName = sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		if((LoginPage.verifyLogin(userName,password)) == "s") // password is password1
		{
			System.out.println("");
			System.out.println("Student login successful");
			System.out.println("Welcome student " + userName);
			System.out.println("(1) Add Course");
			System.out.println("(2) Drop Course");
			System.out.println("(3) Display registered Course"); // Done;
			System.out.println("(4) Check vacancies available");
			System.out.println("(5) Change Index number of course");
			System.out.println("(6) Swap index Number with another student");
			System.out.println("(7) Exit");
			
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();
			
			while(choice != 7)
			{
				switch(choice)
				{
				case 1:
					break;
				case 2:
					buffer = sc.nextLine();
					System.out.println("Please insert the course index that you want to drop : ");
					a=sc.nextLine();
					student_data=StudentData.dropClass(a, userName, student_data);
					break;
				case 3:
					StudentData.getClasses(userName, student_data);
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:		
					break;
				}
				System.out.println();
				System.out.print("Enter the number of your choice: ");
				choice = sc.nextInt();
				
			}
			
		}
		else if((LoginPage.verifyLogin(userName,password)) == "a")
		{
			System.out.println("");
			System.out.println("Admin login successful");
			System.out.println("Welcome admin " + userName);
			System.out.println("(1) Edit student access period");
			System.out.println("(2) Add a student"); // Done
			System.out.println("(3) Add a new course"); // Done
			System.out.println("(4) Update a course"); 
			System.out.println("(5) Check avilable slot for a index"); // Done
			System.out.println("(6) Print student list by index number"); // Done
			System.out.println("(7) Print Student list by course"); // Done
			System.out.println("(8) Exit");
			
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();
			
			while(choice != 9)
			{
				switch(choice)
				{
				case 1:
					break;
				case 2:
					buffer = sc.nextLine();
					System.out.println("Enter name");
					String newName = sc.nextLine();
					System.out.println("Enter Password");
					String newPassword = sc.nextLine();
					System.out.println("Enter Gender");
					String newGender = sc.nextLine();
					System.out.println("Enter matriculation number ");
					String newMnum = sc.nextLine();
					System.out.println("Enter Nationality");
					String nationality = sc.nextLine();
					student_data = StudentData.addStudent(newName, newPassword, newGender, newMnum, nationality, student_data);
					// System.out.println(LoginPage.returnPassword(newPassword));
					for(x = 0;x<16;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(student_data[x][y] + " ");
						}
						System.out.println("");					
					}
					// write to login file
					break;
				case 3:
					
					buffer = sc.nextLine();
					System.out.println("Please enter the Course Code of the new Course : ");
					a=sc.nextLine();
					System.out.println("Please enter the Course Name of the new Course : ");
					b=sc.nextLine();
					System.out.println("Please enter the number of indexes the course will have : ");
					x=sc.nextInt();
					buffer = sc.nextLine();
					for(y=0;y<x;y++)
					{
						System.out.println("Please enter the "+(y+1)+ " index code : ");
						noOfIndex[y] = sc.nextLine();
						System.out.println("Please enter the number of vancancies for index code : "+ noOfIndex[y]);
						Vacancies[y]= sc.nextLine();
						System.out.println(Vacancies[y]);
						System.out.println("Please enter the lab date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
						LabTiming[y] = sc.nextLine();
						System.out.println(LabTiming[y]);
						System.out.println("Please enter the Tutorial date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
						TutorialTiming[y] = sc.nextLine();
						System.out.println(TutorialTiming[y]);
					}
					System.out.println("Please enter the lecture timing : ");
					c=sc.nextLine();
					course_data=CourseData.addCourse(a, b, noOfIndex, Vacancies, c, TutorialTiming, LabTiming, course_data,x);									
					break;
					
				case 4:
					buffer = sc.nextLine();
					System.out.println("Please input the course code that you want to change : ");
					a=sc.nextLine();
					System.out.println("Enter 1 if you want to change the course Name ");
					System.out.println("Enter 2 if you want to add a course index ");
					break;
				case 5:
					buffer = sc.nextLine();
					System.out.println("Enter index number of the course : ");
					temp = sc.nextLine();
					IndexData.getVancanciesLeft(temp,index_data);
					break;
				case 6:	
					buffer = sc.nextLine();
					System.out.println("Enter index number of the course : ");
					temp = sc.nextLine();
					IndexData.getStudentList(temp,index_data);
					break;
				case 7:		
					buffer = sc.nextLine();
					System.out.println("Please enter the course code : ");
					temp =sc.nextLine();
					IndexData.CourseStudentList(temp, index_data);
					break;
				case 8:
					setCourseData(course_data);
					setIndexData(index_data);
					setStudentData(student_data);
					break;
				}
				
				
				for(y = 0;y<100;y++)
				{
					if(course_data[y][0]==null)
						break;
					for(z=0;z<7;z++)
					{
						System.out.print(course_data[y][z] + " ");
					}
					System.out.println("");
					
				}
				System.out.println();
				System.out.print("Enter the number of your choice: ");
				choice = sc.nextInt();
				
			}
		}
		else
		{
			System.out.println("Login Error!");
		}
		
	}
	

	
	public static String [][] getCourseDataArray () throws FileNotFoundException
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
		return test;
	}
	
	public static void setCourseData(String [][] course_data) throws IOException {
		
		FileWriter fwCourse = new FileWriter("CourseData.txt",true);
		
		//flush and delete existing data since we going to rewrite everything inside 
		FileWriter flushCourse = new FileWriter("CourseData.txt", false);
		PrintWriter pwCourse = new PrintWriter(flushCourse, false);
		pwCourse.flush();
		pwCourse.close();
		flushCourse.close();
		
		for(int i = 0; i < course_data.length; i++) {
			if(course_data[i][0]!=null) {
				fwCourse.write(course_data[i][0]+","+ course_data[i][1]+","+course_data[i][2]+","+course_data[i][3]+","+course_data[i][4]+","+course_data[i][5]+","+course_data[i][6]+"\n");
			}
		}
		fwCourse.close();
		
	

	
}

public static void setIndexData(String [][] index_data) throws IOException {
	
		FileWriter fwIndex = new FileWriter("IndexData.txt",true);
		
		//flush and delete existing data since we going to rewrite everything inside 
		FileWriter flushIndex = new FileWriter("IndexData.txt", false);
		PrintWriter pwIndex = new PrintWriter(flushIndex, false);
		pwIndex.flush();
		pwIndex.close();
		flushIndex.close();
		
		for(int i=0; i < index_data.length; i++) {
			if(index_data[i][0]!=null) {
			fwIndex.write(index_data[i][0]+","+index_data[i][1]+","+index_data[i][2]+","+index_data[i][3]+","+index_data[i][4]+"\n");
			}
		}
		fwIndex.close();

	
}

public static void setStudentData(String [][] student_data) throws IOException{
	FileWriter fwStudent = new FileWriter("StudentData.txt", true);
	
	//flush and delete existing data since we going to rewrite everything inside 
	FileWriter flushStudent = new FileWriter("StudentData.txt", false);
	PrintWriter pwStudent = new PrintWriter(flushStudent, false);
	pwStudent.flush();
	pwStudent.close();
	flushStudent.close();
	
	for(int i=0; i < student_data.length; i++) {
		if(student_data[i][0]!=null) {
		fwStudent.write(student_data[i][0]+","+student_data[i][1]+","+student_data[i][2]+","+student_data[i][3]+","+student_data[i][4]+"\n");
		}
	}
	fwStudent.close();
	
	
}
	public static String [][] getIndexData() throws FileNotFoundException
	{
		String[][] test = new String[100][5];
		String a,b,c,d,e,f,g;
		int y = 0;
		int z =0;
		Scanner x = new Scanner(new File("IndexData.txt"));
		Scanner abc = new Scanner(System.in);
		x.useDelimiter("[,\n]");
		while(x.hasNext())
		{		
				a=x.next().trim();
				b=x.next().trim();
				c=x.next().trim();
				d=x.next().trim();
				e=x.next().trim();
				test[y][0] = a;
				test[y][1] = b;
				test[y][2] = c;
				test[y][3] = d;
				test[y][4] = e;
				y++;
				
		}
		return test;
	}
	public static String[][] getStudentData() throws FileNotFoundException
	{
		String[][] test = new String[100][5];
		String a,b,c,d,e,f;
		int y = 0;
		int z =0;
		Scanner x = new Scanner(new File("StudentData.txt"));
		Scanner abc = new Scanner(System.in);
		x.useDelimiter("[,\n]");
		while(x.hasNext())
		{		
				a=x.next().trim();
				b=x.next().trim();
				c=x.next().trim();
				d=x.next().trim();
				e=x.next().trim();
				test[y][0] = a;
				test[y][1] = b;
				test[y][2] = c;
				test[y][3] = d;
				test[y][4] = e;
				y++;
		}
		return test;
	}
}
