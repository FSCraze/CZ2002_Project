import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class StudentData {
	/**
	 * @Author Bryan Eng
	 * @version 1.0
	 * @Since November 11th 2020
	 * 
	 * @Param student_list refers to the data of each students
	 * @Param Index_data refers to the data of each classes
	 * @Param course_data refers to the data of each courses
	 */
		
	/**
	 * This methods return the list of classes a student registered
	 * @Param m_num refers to the matriculation of a student
	 */
	public static void getClasses (String m_num, String student_list[][]) // Get a student's classes
	{
		
		int x,y,z = 0;
		for(x=0;x<100;x++)
		{
			if(student_list[x][0] == null)
				break;
			if(student_list[x][0].equals(m_num))
			{
				System.out.println("You are taking the following courses.");
				
				String [] temp = student_list[x][4].split("-");
				y=temp.length;
				for(z=0;z<y;z++)
				{
					System.out.println(temp[z]);
				}
				break;
			}
		}
	}
	/**
	 * This methods return a list of students that belongs to a class
	 * @Param index refers to class index that we want to find
	 * @Param student_list refers to the list list of all students' info
	 */
	public static void getStudentsClass ( String index , String student_list[][]) 
	{
		
		int i =0;
		int x = 0;
		int y = 0;
		boolean t = true;
		String[] temp = new String[5];
		String[] names = new String[5];
		while(t)
		{
			for(int a = 0; a<3;a++)
			{	
				temp = student_list[a][4].split("-");
				x = temp.length;
				for(i=0;i<x;i++)
				{
					if(temp[i].equals(index))
					{
						names[y]=student_list[a][1];
						y++;
					}
				}
			}
			break;
		}
		for(x=0;x<y;x++)
		{
			System.out.println(names[x]);
		}
	}
	/**
	 * This method returns the entire list of students with their Matriculation number
	 * @param student_list refers to the list of all students info
	 */
	public static void getStudent (String student_list[][])
	{
		
		System.out.println("Current list of students ");
		int x,y = 0;
		for(x = 0; x<100;x++)
		{
			if(student_list[x][1] == null)
				break;
			System.out.print(student_list[x][0]);
			System.out.print(" ");
			System.out.println(student_list[x][1]);
		}
	}
	/**
	 * This methods remove a class for a student
	 * @Param class_index refers to the class index that the student want to remove
	 * @Param student_num refers to the Matriculation number of the student
	 * @return student list 
	 */
	public static String[][] dropClass (String class_index, String student_num, String student_list[][])
	{
		
		int x,y,z=0;
		int a = 0;
		String temp = "";
		for(x=0;x<100;x++)
		{
			if(student_list[x][0]== null)
				break;
			if(student_list[x][0].equals(student_num))
			{
				String [] temp1 = student_list[x][4].split("-");
				y = temp1.length;
				for(z=0;z<y;z++)
				{
					if(temp1[z].equals(class_index))
					{					
						System.out.println("You have dropped "+class_index);
						a=1;
					}
					else
					{
						if(z==(y-1))
						{
							temp = temp+temp1[z];
							break;
						}                                                                          
					temp = temp+temp1[z]+"-";						
					}
				}
				
				char last = temp.charAt(temp.length()-1);
				if(last == '-')
				{
					temp = temp.substring(0, temp.length() -1);
					student_list[x][4] = temp;
				}
				else
				{
				student_list[x][4] = temp;
				}
			}
				
			
		}
		if(a==1)
		{
			return student_list;
		}
		System.out.println("You are not registered in "+class_index);
		return student_list;
	}
	/**
	 * This methods add a new student into the data
	 * @Param name refers to the name of the student
	 * @Param Password refers to the password of the student
	 * @Param Gender refers to the gender of the student
	 * @Param m_num refers to the matriculation number of the student
	 * @Param nationality refers to the nationality of the student
	 * @Param email refers to the email of the student
	 * @return student list
	 */
	public static String[][] addStudent(String Name,String Password,String Gender,String m_num,String Nationality, String student_list[][], String email)
	{
		
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(student_list[x][0] == null)
			{
				student_list[x][0] = m_num;
				student_list[x][1] = Name;
				student_list[x][2] = Gender;
				student_list[x][3] = Nationality;
				student_list[x][4] = "NULL";
				student_list[x][5] = email+"@hotmail.com";
				System.out.println(Name+" has been added.");
				break;			
			}
			if(student_list[x][0].equals(m_num))
			{
				System.out.println("Error! Matriculation Number already existed");
				return student_list;
			}
			
		}
		return student_list;
	}
	/**
	 * This methods facilitates the swapping of index with another user
	 * @Param current_course_index refers to the current class index the user has
	 * @Param future_course_index refers to the class index that he wants to swap
	 * @Param m_num refers to the student's matriculation number
	 * @return student list
	 */
	public static String[][] swapIndex (String current_course_index, String future_course_index, String m_num, String [][] student_list)
	{
		
		int x,y,z=0;
		int a,b=0;
		for(x =0; x<100;x++)
		{
			if(student_list[x][0]==null) // No such m_num;
			{
				System.out.println("Error! Invalid Matriculation Number");
				return student_list;
			}
			if(student_list[x][0].equals(m_num))
			{
				String [] temp = student_list[x][4].split("-");
				y=temp.length;			
				for(a=0;a<y;a++)
				{
					if(temp[a].equals(current_course_index))
					{
						
						b=1;
						break;
					}
				
				}
				if(b!=1)
				{
					System.out.println("You are not registered to "+current_course_index);
					return student_list;
				}
				student_list[x][4] = "";
				for(z=0;z<y;z++)
				{
					if(temp[z].equals(current_course_index))
					{
						temp[z] = future_course_index;
					}
					if(z==(y-1))
					{
						student_list[x][4]= student_list[x][4]+"-"+temp[z];
						return student_list;
					}
					if(z==0)
					{
						student_list[x][4] = temp[0];
						continue;
					}
					student_list[x][4] = student_list[x][4]+"-"+temp[z];
				}
			}
		}
		return student_list;
	}
	/**
	 * Adds a student into the class
	 * @Param class_index refers to the class indexes the student wants to register
	 * @Param student_num refers to the matriculation number of the student
	 * @return student list
	 */
	public static String[][] addClass (String class_index, String student_num, String student_list[][], String course_data[][], String index_data[][] )
	{
		
		boolean a = false;
		boolean b = false;
		boolean c = false;
		int a2 = 0;
		int a3 = 0;
		String a1 ="";
		String [] new_class_index_timings = new String[3];
		int e,f=0,g=0;
		int x,y,z=0;
		// get timing of the new class_index
		for(x=0;x<100;x++)
		{
			if(index_data[x][1] == null)
				break;
			if(index_data[x][1].equals(index_data))
			{
				a2=1;
			}
		}
		for(x=0;x<100;x++)
		{
			if(a2==0)
			{
				//System.out.println("Error! Invalid Course");
				break;
			}
			if(index_data[x][1]==null)
				break;
			if(index_data[x][1].equals(class_index))
			{
				String [] temp = index_data[x][2].split("-");
				y=temp.length;
				for(z=0;z<y;z++)
				{	
					if(temp[z].equals(student_num))
					{
						f=1;
						break;
					}
				}
			}
		}
		/*if(f==0)
		{
			return student_list;
		}
		*/
		for(x=0;x<100;x++)
		{
			if(course_data[x][2] == null)
			{
				System.out.println("There are no such index : "+class_index);
				return student_list;
			}
				
			if(course_data[x][2].equals(class_index)) // Get the class_index timings that we want to add 
			{
				new_class_index_timings[0] = course_data[x][4]; 
				new_class_index_timings[1] = course_data[x][5]; 
				new_class_index_timings[2] = course_data[x][6]; 
				break;
			}
		}
		
		for(x=0;x<100;x++)
		{

			if(student_list[x][0]==null)
			{
				break;
			}
			if(student_list[x][0].equals(student_num))
			{
				a1= class_index.substring(0,1); // return first character of the class
				String [] temp = student_list[x][4].split("-");
				y = temp.length;
				for(z=0;z<y;z++) // Check if similar index already exist
				{
					if(temp[0].contains(a1))
					{
						//System.out.println("You are already taking this course with another index");
						return student_list;
					}
				}
				// Check clashes
				for(z=0;z<y;z++)
				{
					for(e=0;e<100;e++)
					{
						if(course_data[e][0] == null)
						{
							break;
						}
						if(course_data[e][2].equals(temp[z]))
						{
							a= CourseData.checkClash(course_data[e][4],new_class_index_timings[0]); // 4 & 5 & 6
							b= CourseData.checkClash(course_data[e][5],new_class_index_timings[1]); // 4 & 5 & 6
							c= CourseData.checkClash(course_data[e][6],new_class_index_timings[2]); // 4 & 5 & 6
							if(a==true || b==true || c==true)
							{
								System.out.println("You can't take this index as it clashes with "+course_data[e][2]);
								return student_list;
							}
						}
					}
				}
				
			
				for(x=0;x<100;x++)
				{
					if(index_data[x][0] == null)
						break;
					if(index_data[x][1].equals(class_index))
					{
						String [] list = index_data[x][2].split("-");
						y = list.length;
						for(z=0;z<y;z++)
						{
							if(list[z].equals(student_num))
							{
								a3 = 1;
								break;
							}
						}
						break;
					}
				}
				
				for(x=0;x<100;x++)
				{
					if(student_list[x][0]==null)
						break;
					if(student_list[x][0].equals(student_num))
					{
						if(a3==1)
					{
						System.out.println(student_list[x][0]);
						if(student_list[x][4].equals("NULL"))
						{
							student_list[x][4] = class_index;
							return student_list;
						}
						else
						{
							student_list[x][4] = student_list[x][4]+"-"+class_index;
							return student_list;
						}
					}
					break;
					}
				}
			}
		}
		return student_list;
	}
	
	//index_data = IndexData.swapIndexWithStudents(userName, b , a, c, index_data, course_data, student_data);
	//student_data = StudentData.swapIndexWithStudents(m_num1, m_num2, index_1, index_2, index_data, student_data)
	
	/**
	 * This methods facilitates the swapping of class index between 2 students
	 * @Param m_num1 = user 1
	 * @Param m_num2 = user 2
	 * @Param index_1 belongs to user1
	 * @Param index_2 belongs to user2
	 * @return student data
	 */		
	public static String [][] swapIndexWithStudents ( String m_num1, String m_num2, String index_1, String index_2, String [][] index_data, String [][] student_data)
	{	
		
		int x,y,z=0;
		int a = 0;
		for(x=0;x<100;x++)
		{
			if(index_data[x][1].equals(index_1))
			{
				String [] temp1 = index_data[x][2].split("-");
				y = temp1.length;
				for(z=0;z<y;z++)
				{
					if(temp1[z].equals(m_num2))
					{
						a=1;
						break;
					}
				}
				break;
			}
		}
		
		if(a==0)
		{
			return student_data;
		}
		
		for(x=0;x<100;x++)
		{
			if(student_data[x][0].equals(m_num1))
			{
				String [] temp2 = student_data[x][4].split("-");
				y= temp2.length;
				student_data[x][4] = "";
				for(z=0;z<y;z++)
				{
					if(temp2[z].equals(index_1))
					{
						temp2[z] = index_2;
						break;
					}
				}
				for(z=0;z<y;z++)
				{
					if(z==(y-1))
					{
						student_data[x][4] = student_data[x][4] + temp2[z];
						break;
					}
					student_data[x][4] = student_data[x][4] + temp2[z] + "-";
				}
				break;
			}
		}
		
		for(x=0;x<100;x++)
		{
			if(student_data[x][0].equals(m_num2))
			{
				String [] temp3 = student_data[x][4].split("-");
				y= temp3.length;
				student_data[x][4] = "";
				for(z=0;z<y;z++)
				{
					if(temp3[z].equals(index_2))
					{
						temp3[z] = index_1;
						break;
					}
				}
				for(z=0;z<y;z++)
				{
					if(z==(y-1))
					{
						student_data[x][4] = student_data[x][4] + temp3[z];
						break;
					}
					student_data[x][4] = student_data[x][4] + temp3[z] + "-";
				}
				break;
			}
		}
		
		return student_data;
	}
	/**
	 * This method retrieve the Class indexes detail from a txt file.
	 */
	public static String[][] getStudentData() throws FileNotFoundException
	{
		
		String[][] studentDataArray = new String[100][6];
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
				f=x.next().trim();
				studentDataArray[y][0] = a;
				studentDataArray[y][1] = b;
				studentDataArray[y][2] = c;
				studentDataArray[y][3] = d;
				studentDataArray[y][4] = e;
				studentDataArray[y][5] = f; // email;
				y++;
		}
		return studentDataArray;
	}
	
	/**
	 * This method returns student's details
	 * @param m_num
	 * @param student_data
	 */
	public static void getStudentInfo( String m_num, String [][] student_data)
	{
		
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(student_data[x][0]==null)
			{
				System.out.println("Error! There is not such student with matriculation number : " + m_num);
				return;
			}
			if(student_data[x][0].equals(m_num))
			{
				System.out.println("Student : " + student_data[x][0]);
				System.out.println("Student's Name : " + student_data[x][1]);
				System.out.println("Student's Gender : " + student_data[x][2]);
				System.out.println("Student's Nationality : " + student_data[x][3]);
				System.out.println("List of classes the student is taking  : " + student_data[x][4]);
				System.out.println("Student's Email : " + student_data[x][5]);
				return;
			}

		}
	}
}






































