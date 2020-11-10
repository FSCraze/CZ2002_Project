package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.FileReader;
//test
public class MainPage {
	public static void main(String args[]) throws NoSuchAlgorithmException, IOException
	{
		String a,b,c,d,e,f,g,h,i;
		String [][] course_data = CourseData.getCourseDataArray();
		String [][] index_data = IndexData.getIndexData();
		String [][] student_data = StudentData.getStudentData();
		String [][] login_data = getLogin();
		String [] noOfIndex = new String[10];
		String [] LabTiming = new String[10];
		String [] TutorialTiming = new String[10];
		String [] Vacancies = new String[10];
		int choice;
		boolean check = LoginPage.verifyAccess();
		int x,y,z;
		String buffer;
		String temp = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username");
		String userName = sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		if((LoginPage.verifyLogin(userName,password)) == "s" && LoginPage.verifyAccess()== true) // password is password1
		{
			System.out.println("");
			System.out.println("=========================");
			System.out.println("Student login successful");
			System.out.println("Welcome student " + userName);
			System.out.println("=========================");
			System.out.println("(1) Add Course"); //Done
			System.out.println("(2) Drop Course"); //Done
			System.out.println("(3) Display registered Course"); // Done;
			System.out.println("(4) Check vacancies available"); // Done
			System.out.println("(5) Change Index number of course"); // Done
			System.out.println("(6) Swap index Number with another student"); 
			System.out.println("(7) Exit");
			
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();
			
			while(choice != 7)
			{
				switch(choice)
				{
				case 1:
					buffer =sc.nextLine();
					System.out.println("Please insert the course index that you want to add : ");
					a = sc.nextLine();
					student_data = StudentData.addClass(a, userName, student_data, course_data);
					index_data = IndexData.addStudent(a, userName, index_data);
					/*
					for(x=0;x<15;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(student_data[x][y]);
						}
						System.out.println("");
					}
					
					for(x=0;x<12;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(index_data[x][y]);
							System.out.print(" ");
						}
						System.out.println("");
					}
					*/
					break;
				case 2:
					buffer = sc.nextLine();
					System.out.println("Please insert the course index that you want to drop : ");
					a=sc.nextLine();	
					index_data = IndexData.dropStudent(a, userName, index_data, student_data);
					student_data=StudentData.dropClass(a, userName, student_data, index_data);
					for(x=0;x<12;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(index_data[x][y]);
							System.out.print(" ");
						}
						System.out.println("");
					}
					
					for(x=0;x<17;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(student_data[x][y]);
							System.out.print(" ");
						}
						System.out.println("");
					}
				
					break;
				case 3:
					StudentData.getClasses(userName, student_data);
					break;
				case 4:
					buffer =sc.nextLine();
					System.out.println("Please insert the course index that you want to retrieve : ");
					a=sc.nextLine();
					IndexData.getVancanciesLeft(a, index_data);
					break;
				case 5:
					buffer=sc.nextLine();
					System.out.println("Please enter the current index that you want to swap : ");
					a=sc.nextLine();
					System.out.println("Please enter the future index that you want to changed to : ");
					b=sc.nextLine();
					index_data = IndexData.swapIndex(a, b, userName, index_data,student_data);
					student_data = StudentData.swapIndex(a, b, userName, student_data);
					/*
					for(x=0; x<16;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(student_data[x][y]+" ");
						}
						System.out.println("");
					}
					
					for(x=0; x<12;x++)
					{
						for(y=0;y<5;y++)
						{
							System.out.print(index_data[x][y]+" ");
						}
						System.out.println("");
					}
					*/
					break;
				case 6:	
					buffer=sc.nextLine();
					System.out.println("Please enter the index that you want to be swapped : ");
					a=sc.nextLine();
					System.out.println("Please enter the Matriculation number of the 2nd student : ");
					b=sc.nextLine();
					System.out.println("Please enter the index that you want from the 2nd student : ");
					c=sc.nextLine();
					
					index_data = IndexData.swapIndexWithStudents(userName, b , a , c, index_data, course_data, student_data);
					student_data = StudentData.swapIndexWithStudents(userName, b, a, c, index_data, student_data);
					/*
					for(int abc = 0; abc<100; abc++)
					{
						if(index_data[abc][0] == null)
							break;
						System.out.print(index_data[abc][0] + " ");
						System.out.print(index_data[abc][1] + " ");
						System.out.print(index_data[abc][2] + " ");
						System.out.print(index_data[abc][3]);
						System.out.println("");
					}
					
					for(int abcd = 0; abcd<100; abcd++)
					{
						if(student_data[abcd][0] == null)
							break;
						System.out.print(student_data[abcd][0] + " ");
						System.out.print(student_data[abcd][1] + " ");
						System.out.print(student_data[abcd][2] + " ");
						System.out.print(student_data[abcd][3] + " ");
						System.out.print(student_data[abcd][4]);
						System.out.println("");
					}
					*/
					break;
				case 8:
					setCourseData(course_data);
					setIndexData(index_data);
					setStudentData(student_data);
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
			System.out.println("=========================");
			System.out.println("Admin login successful");
			System.out.println("Welcome admin " + userName);
			System.out.println("=========================");
			System.out.println("(1) Edit student access period");
			System.out.println("(2) Add a student"); // Done
			System.out.println("(3) Add a new course"); // Done
			System.out.println("(4) Update a course"); 
			System.out.println("(5) Check avilable slot for a index"); // Done
			System.out.println("(6) Print student list by index number"); // Done
			System.out.println("(7) Print Student list by course"); // Done
			System.out.println("(8) Save Data");// Done
			System.out.println("(9) Exit");
			
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();
			
			while(choice != 9)
			{
				switch(choice)
				{
				case 1:
					buffer=sc.nextLine();
					System.out.println("Enter start of access period: (YYYY/MM/DD/HH/MM)");
					String startAccess = sc.nextLine();
					System.out.println("Enter end of access period: (YYYY/MM/DD/HH/MM");
					String endAccess = sc.nextLine();
					while(setAccessPeriod(startAccess,endAccess)!=0) {
						System.out.println("Error, please enter again:");
						System.out.println("Enter start of access period: (YYYY/MM/DD/HH/MM)");
						 startAccess = sc.nextLine();
						System.out.println("Enter end of access period: (YYYY/MM/DD/HH/MM)");
						 endAccess = sc.nextLine();
						 setAccessPeriod(startAccess,endAccess);
					}
					System.out.println("Student access period edited and saved.");
					break;
				case 2:
					buffer = sc.nextLine();
					System.out.println("Enter matriculation number ");
					String newMnum = sc.nextLine();
					System.out.println("Enter name");
					String newName = sc.nextLine();
					System.out.println("Enter Password");
					String newPassword = sc.nextLine();
					System.out.println("Enter Gender");
					String newGender = sc.nextLine();
					System.out.println("Enter Nationality");
					String nationality = sc.nextLine();
					student_data = StudentData.addStudent(newName, newPassword, newGender, newMnum, nationality, student_data);
					LoginPage.newStudent(newMnum,newPassword);
					a = (LoginPage.returnPassword(newPassword));
				
					break;
				case 3:
					String tempTimeLab = "";
					String tempTimeTut = "";
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
						System.out.println("Please enter the lab date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
						tempTimeLab = sc.nextLine();
						while(checkisTime(tempTimeLab)!=0) {
							System.out.println("Please enter the lab date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
							tempTimeLab = sc.nextLine();
							checkisTime(tempTimeLab);
						}
						LabTiming[y] = tempTimeLab;
						System.out.println("Please enter the Tutorial date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
						tempTimeTut = sc.nextLine();
						while(checkisTime(tempTimeTut)!=0) {
							System.out.println("Please enter the lab date and timing in the format of DD-HHMM-HHMM for index " + noOfIndex[y]);
							tempTimeTut = sc.nextLine();
							checkisTime(tempTimeTut);
						}
						TutorialTiming[y] = tempTimeTut;
					}
					System.out.println("Please enter the lecture timing : ");
					c=sc.nextLine(); // lecture timing
					course_data=CourseData.addCourse(a, b, noOfIndex, Vacancies, c, TutorialTiming, LabTiming, course_data,x);	
					index_data = IndexData.newCourse(index_data,a,Vacancies, noOfIndex,x);

					break;
					
				case 4:
					buffer = sc.nextLine();
					System.out.println("Please input the course code that you want to change : ");
					System.out.println("Enter 1 if you want to change the course Name ");
					System.out.println("Enter 2 if you want to add a course index ");
					System.out.println("Enter 3 if you want to update a index's vacancy");
					x=sc.nextInt();
					if(x==1)
					{
						System.out.println("Please enter the course code : ");
						a = sc.nextLine();
						System.out.println("Please enter the new name for "+a+" : ");
						b = sc.nextLine();
						course_data=CourseData.changeCourseName(a, b, course_data);
					}
					else if(x==2)
					{
						System.out.println("Please enter the course code : ");
						a = sc.nextLine();
						System.out.println("Please enter the index code : ");
						b = sc.nextLine();
						System.out.println("Please enter the vacancy : ");
						c = sc.nextLine();
						System.out.println("Please enter the Lecture Timing : ");
						d = sc.nextLine();
						System.out.println("Please enter the Lab Timing : ");
						e = sc.nextLine();
						System.out.println("Please enter the Tutorial Timing : ");
						f = sc.nextLine();
						course_data = CourseData.addNewIndex(a, b, c, d, e, f, course_data);
						index_data = IndexData.newIndex(a, b, c, index_data);
					}
					else if(x==3)
					{
						System.out.println("Please enter the index code : ");
						a = sc.nextLine();
						System.out.println("Please enter the new number of vacancies : ");
						b = sc.nextLine();
						course_data = CourseData.changeVacancies(a, b, course_data);
						index_data = IndexData.updateVacancies(a, b, index_data);
					}
					else
					{
						System.out.println("Error input!");
					}
					
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
						
				
				System.out.println();
				System.out.print("Enter the number of your choice: ");
				choice = sc.nextInt();
			}
			}
		else if(check == false)
		{
			System.out.println("Error! You're not able to login as you are out of the access period");
		}
		else
		{
			System.out.println("Error! Invalid Username or Password");
		}
		
	}
	
	/* 	Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);      
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
		SimpleDateFormat dFormat = new SimpleDateFormat("YYYY,MM,d,HH,mm");
		Calendar dateNow,startAccess,endAccess;
		 dateNow = new GregorianCalendar(year,month,day,hour,minute);
		 endAccess = new GregorianCalendar(acc[5],acc[6],acc[7],acc[8],acc[9]); //change this to end of access period 
		 startAccess = new GregorianCalendar(acc[0],acc[1],acc[2],acc[3],acc[4]); //change this to start of access period*/
	//2020,11,01,13,30

public static int  setAccessPeriod(String start, String end ) throws IOException {
	String[]startS = new String[10];
	String[] endS = new String[10];
	startS=start.split("/");
	endS=end.split("/");
	int[] startArray = new int[10];
	int[] endArray = new int[10];
	try {
	for(int i = 0; i < startS.length;i++) {
		startArray[i]=Integer.parseInt(startS[i]);
		endArray[i] = Integer.parseInt(endS[i]);
	}}
	catch(NumberFormatException e) {
		return 1;
	}
	
	int temp = 0;
	temp = startArray[1];
	startArray[1]=temp-1;
	temp=endArray[1];
	endArray[1]=temp-1;

	
		try {
		Calendar startAccess, endAccess;
		startAccess = new GregorianCalendar(startArray[0],startArray[1],startArray[2],startArray[3],startArray[4]);
		endAccess = new GregorianCalendar(endArray[0],endArray[1],endArray[2],endArray[3],endArray[4]);
		startAccess.setLenient(false);
		endAccess.setLenient(false);
		endAccess.getTime();
		startAccess.getTime();
		if(startAccess.after(endAccess)) {
			return 1;
		}
		}
		catch(IllegalArgumentException e) {
			return 1;
		}
	
	//since month is 0 - 11, minus one to store into txt file 

	
	
			
	FileWriter fwAccess = new FileWriter("AccessPeriod.txt", true);
	FileWriter flushAccess = new FileWriter("AccessPeriod.txt", false);
	PrintWriter pwAccess = new PrintWriter(flushAccess, false);
	pwAccess.flush();
	pwAccess.close();
	flushAccess.close();
	fwAccess.write(startArray[0]+","+startArray[1]+","+startArray[2]+","+startArray[3]+","+startArray[4]+"\n");
	fwAccess.write(endArray[0]+","+endArray[1]+","+endArray[2]+","+endArray[3]+","+endArray[4]);
	fwAccess.close();
	return 0;
	
}

	
public static int checkisTime(String d) {
	//D-HHMM-HHMM
	String[]timeSplit = new String[10];
	timeSplit=d.split("-");
	int[] timeInt = new int[10];
	try {
	for(int i = 0; i < timeSplit.length;i++) {
		timeInt[i]=Integer.parseInt(timeSplit[i]);
	}
	if(timeInt[0]>7 || timeInt[0]<1) {
		System.out.println("Wrong day format (1 = Mon, 2 = Tues, ..., 7 = Sun)");
		return 1; //not monday to sunday 
		
	}
	else if(timeInt[1]>2359 || timeInt[2]>2359 ||timeInt[1]<0000 || timeInt[2]<0000) {
		System.out.println("Invalid time, must be within 0000 - 2359");
		return 1; //invalid time 
	}
	
	else if(timeInt[1] == timeInt[2]) {
		System.out.println("Start time cannot be the same as end time");
		return 1; //same start and end time 
	}
	else if (timeInt[1]>timeInt[2]) {
		System.out.println("Start time cannot be later than end time");
		return 1; //end earlier than start 
	}
	
	}catch(NumberFormatException e){
		System.out.println("Wrong format, please input D-HHMM-HHMM");
		return 1; //not numbers 
		
	}
	
	return 0;
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
		fwStudent.write(student_data[i][0]+","+student_data[i][1]+","+student_data[i][2]+","+student_data[i][3]+","+student_data[i][4]+","+student_data[i][5]+"\n");
		}
	}
	fwStudent.close();
	
	
}

	
	
	public static String [][] getLogin() throws FileNotFoundException
	{
		String [][] test = new String[100][2];
		String a,b;
		int y = 0;
		Scanner x = new Scanner(new File("login.txt"));
		x.useDelimiter("[,\n]");
		while(x.hasNext())
		{
			a=x.next().trim();
			b=x.next().trim();
			test[y][0] = a;
			test[y][1] = b;
			y++;
		}
		return test;
	}
}
