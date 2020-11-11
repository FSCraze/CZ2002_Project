import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class IndexData {
	public static void main (String args[]) throws FileNotFoundException
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

		/*
		getWaitList("A01",test);
		System.out.println("");
		getStudentList("A01",test);
		System.out.println("");
		getWaitStudent("A01",test);
		System.out.println("");
		System.out.println("");
		test = dropStudent("A01","U01",test);
		*/
		System.out.println("");
		
		
		for(y = 0;y<12;y++)
		{
			for(z=0;z<5;z++)
			{
				System.out.print(test[y][z] + " ");
			}
			System.out.println("");
			
		}
	}
	public static void getVancanciesLeft(String IndexCode,String student_list[][]) // Return the number of vancancies
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(student_list[x][1] == null)
			{
				System.out.println("Error, No such index number");
				break;
			}
			if(student_list[x][1].equals(IndexCode))
			{
				System.out.println("Index : "+IndexCode+" has "+student_list[x][3]+" vancancies left");
				break;
			}
		}
	}
	
	public static void getStudentList(String IndexCode, String student_List[][]) // Returns the list of students
	{		
		int x,y,z=0;
		for(x=0;x<100;x++)
		{
			if(student_List[x][1] == null)
			{
				System.out.println("Error, No such index number");
				break;
			}
			if(student_List[x][1].equals(IndexCode))
			{
				String [] temp = student_List[x][2].split("-");
				z=temp.length;
				for(y=0;y<z;y++)
				System.out.println("Inde x: "+student_List[x][1]);
				{
					System.out.println(temp[y]);
				}
				break;
						
			}
		}
	}
	
	public static void getWaitStudent(String IndexCode, String student_List[][]) // Returns the list of students in the waitlist
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(student_List[x][2] == null)
				break;
			if(student_List[x][2].equals(IndexCode))
			{
				System.out.println(student_List[x][4]);
			}
		}
	}
	
	public static String[][] addStudent(String IndexCode,String Student_num, String index_data[][], String student_data[][], String course_data[][])
	{
		String temp;
		int a =0;
		boolean a2,b,c;
		int e = 0;
		int x,y=0,z=0;;
		for(x=0;x<100;x++)
		{
			if(index_data[x][1] == null)
				break;			
			if(index_data[x][1].equals(IndexCode))
			{
				String [] temp1 = index_data[x][2].split("-");
				y = temp1.length;
				for(z=0;z<y;z++)
				{
					if(temp1[z].equals(Student_num))
					{
						System.out.println("You're already registered in the course");
						return index_data;
					}
				}
			}
		}
		
		for(x=0;x<100;x++)
		{
			if(index_data[x][1] == null)
				break;
			if(index_data[x][1].equals(IndexCode))
			{
				a=1;
			}
		}
		if(a==0)
		{
			System.out.println("Error! Invalid Course");
			return index_data;
		}
		
		String [] new_class_index_timings = new String[3];
		for(x=0;x<100;x++)
		{	if(course_data[x][1]== null)
				break;
			if(course_data[x][2].equals(IndexCode))
			{
				new_class_index_timings[0] = course_data[x][4];
				new_class_index_timings[1] = course_data[x][5];
				new_class_index_timings[2] = course_data[x][6];	
				break;
			}
		}
		String a1 = "";
		for(x=0;x<100;x++)
		{
			if(student_data[x][0]==null)
			{
				break;
			}
			if(student_data[x][0].equals(Student_num))
			{
				a1= IndexCode.substring(0,1);
				String [] temp2 = student_data[x][4].split("-");
				y=temp2.length;
				for(z=0;z<y;z++)
				{
					if(temp2[0].contains(a1)) 
					{
						return index_data;
					}
				}
				
				for(z=0;z<y;z++)
				{
					for(e=0;e<100;e++)
					{
						if(course_data[e][0] == null)
						{
							break;
						}
						if(course_data[e][2].equals(temp2[z]))
						{
							//System.out.println(new_class_index_timings[0]);
							//System.out.println("");
							//System.out.println(course_data[e][4]);
							a2= CourseData.checkClash(course_data[e][4],new_class_index_timings[0]); // 4 & 5 & 6
							b= CourseData.checkClash(course_data[e][5],new_class_index_timings[1]); // 4 & 5 & 6
							c= CourseData.checkClash(course_data[e][6],new_class_index_timings[2]); // 4 & 5 & 6
							if(a2==true || b==true || c==true)
							{
								//System.out.println("You can't take this index as it clashes with "+course_data[e][2]);
								return index_data;
							}
						}
					}

				}
			}
		}
		
		for(x=0;x<100;x++)
		{
			if(index_data[x][1]==null)
				break;
			if(index_data[x][1].equals(IndexCode))
			{
				if(!index_data[x][3].equals("0") && index_data[x][4].equals("NULL"))
				{
					if(index_data[x][2].equals("NULL"))
					{
						index_data[x][2] = ""+Student_num;
						y = Integer.parseInt(index_data[x][3]);
						y = y-1;
						index_data[x][3] = Integer.toString(y);
						break;
					}
					temp = index_data[x][2];
					temp = temp+"-"+Student_num;
					index_data[x][2] = temp;
					y = Integer.parseInt(index_data[x][3]);
					y = y-1;
					index_data[x][3] = Integer.toString(y);
					break;
				}
				else
				{
					System.out.println("Currently the class has no vacancies, you will be put in the waitlist");
					if(index_data[x][4].equals("NULL"));
					{
						if(index_data[x][4].equals("NULL"))
							index_data[x][4] = Student_num;
						else
							index_data[x][4] = index_data[x][4]+ "-" + Student_num;
					}

				}
			}
		}
		return index_data;
	}
	
	
	public static String[][] dropStudent(String IndexCode,String Student_num, String student_List[][] , String [][]student_data) throws FileNotFoundException
	{
		int x,y,z=0;
		int a;
		int count = 0;
		String temp = "";
		for(x=0;x<100;x++)
		{
			if(student_List[x][1]== null)
				break;
			if(student_List[x][1].equals(IndexCode))
			{
				String [] temp1 = student_List[x][2].split("-");
				y=temp1.length;
				for(z=0;z<y;z++)
				{
					if(y==1)
					{
						student_List[x][2]="NULL";
						count = 1;
						break;
					}
					if(temp1[z].equals(Student_num))
					{
					}
					else
					{
						if(z==(y-1))
						{
							temp=temp+temp1[z];
						}
						else
						{
							temp = temp+temp1[z]+"-";
						}
					}
				}	
				
				/*student_List[x][2] = temp;
				student_List[x][2] = temp;
				y = Integer.parseInt(student_List[x][3]);
				y = y+1;
				student_List[x][3] = Integer.toString(y);*/

				if(!student_List[x][4].equals("NULL")) 
				{
					String []temp2 = student_List[x][4].split("-");
					student_List[x][2] = student_List[x][2]+"-"+temp2[0];
					SendMailTLS.SendEmail(student_data, temp2[0]);
					y = temp2.length;
					temp ="";
					if(y==1)
					{
						student_List[x][4] = "NULL";
						break;
					}
					for(a=1;a<y;a++)
					{
						temp = temp+temp2[a];
						if(a!=(y-1))
						{
							temp = temp+"-";
						}
					}
				}
				if(count == 1)
				{
						student_List[x][2]="NULL";
						y = Integer.parseInt(student_List[x][3]);
						y = y+1;
						student_List[x][3] = Integer.toString(y);
						return student_List;
					}
				}
				else
				{

					y = Integer.parseInt(student_List[x][3]);
					y = y+1;
					student_List[x][3] = Integer.toString(y);
				}
			}
		
		return student_List;
	}
	
	public static void CourseStudentList (String coursecode, String student_list[][])
	{
		int x,y,z=0;
		int a =0;
		for(x=0;x<100;x++)
		{
			if(student_list[x][0] == null)
			{
				if(a==0)
				{
					System.out.println("Error, No such course");
				}
				break;
			}
			if(student_list[x][0].equals(coursecode))
			{
				a = 1;
				String [] temp = student_list[x][2].split("-");
				y = temp.length;
				System.out.println("Index : "+student_list[x][1]);
				for(z=0;z<y;z++)
				{
					System.out.println(temp[z]);
				}
			}
		}
	}
	
	public static String [][] swapIndex (String current_course_index, String future_course_index, String m_num, String student_list[][], String [][] student_data) throws FileNotFoundException
	{
		int a,b,c,x,y,z=0;
		int temp1 = -1,temp2 =-1;
		for(x=0; x<100; x++)
		{
			if(student_list[x][1] == null)
			{
				System.out.println("There is no such index of " + future_course_index);
				break;
			}
			if(student_list[x][1].equals(current_course_index))
			{
				temp1 = x;
			}
			if(student_list[x][1].equals(future_course_index))
			{
				temp2 = x;
			}
			if(temp1!=-1 && temp2 !=-1)
				break;
			
		}
		if(temp1 == -1)
		{
			System.out.println("You are not registered to "+current_course_index+"in the first place");
			return student_list;
		}
		if(temp2 == -1)
		{
			System.out.println(future_course_index+"Does not exist");
			return student_list;
		}
		if(temp1 != -1 && temp2 != -1)
		{
			if(student_list[temp2][3].equals("0"))
			{
				System.out.println("The index you wanted is full");
				return student_list;
			}
			String [] temp3 = student_list[temp1][2].split("-");
			y = temp3.length;
			for(z=0;z<y;z++)
			{
				if(temp3[z].equals(m_num))
				{
					student_list[temp2][2] = student_list[temp2][2] + "-" + m_num;
					student_list[temp1][2] = "";
					int inum = Integer.parseInt(student_list[temp1][3]);
					inum= inum+1;
					student_list[temp1][3] = Integer.toString(inum);
					student_list[temp1][2] = "NULL";
					for(a=0;a<y;a++)
					{	
						if(temp3[a].equals(m_num))
						{
							continue;
						}
						if(a==(y-1))
						{
							student_list[temp1][2] = student_list[temp1][2]+temp3[a];
							break;
						}
						student_list[temp1][2] = student_list[temp1][2]+"-"+temp3[a];
					}
				}
			}
			int abc =0;
			for(x=0;x<100;x++)
			{
				if(student_list[x][1].equals(current_course_index))
				{
					if(student_list[x][4].equals("NULL"))
					{
						break;
					}
					else
					{
						String [] temp4 = student_list[x][4].split("-");
						student_list[x][4] = "";
						y=temp4.length;
						System.out.println(y);
						if(student_list[x][2].equals("NULL"))
						{
							abc = 1;
							student_list[x][2] = "";
						}
						for(z=1;z<y+1;z++)
						{
							if(y==1)
							{
								System.out.println(student_list[x][4]);
								student_list[x][4] = "NULL";
								break;
							}
							if(z==(y-1)) 
							{
								student_list[x][4] = student_list[x][4] + temp4[z];
								break;
							}
							student_list[x][4] = student_list[x][4] + temp4[z] + "-";
						}
						
						if(abc==1)
						{
							student_list[x][2] = temp4[0];
						}
						else
						{
							student_list[x][2] = student_list[x][2] + "-" + temp4[0];
						}
						int inum2 = Integer.parseInt(student_list[x][3]); 
						inum2 = inum2-1;
						student_list[x][3] = Integer.toString(inum2);
						// send email to temp4[0];
						SendMailTLS.SendEmail(student_data, temp4[0]);
						
						for(x=0;x<100;x++)
						{
							if(student_data[x][0].equals(temp4[0]))
							{
								if(student_data[x][4].equals("NULL"))
								{
									student_data[x][4] = current_course_index;
								}
								else
								{
								student_data[x][4] = student_data[x][4] + "-" + current_course_index;
								}
							}
						}
						break;
					}
				}
			}
		}
		return student_list;
	}
		
	public static String[][] newIndex (String course_code,String index_code,String vacancy,String[][] index_data)
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(index_data[x][0] == null)
				break;
		}
		index_data[x][0] = course_code; 
		index_data[x][1] = index_code; 
		index_data[x][2] = "NULL"; 
		index_data[x][3] = vacancy; 
		index_data[x][4] = "NULL"; 
		return index_data;
	}
	
	public static String[][] updateVacancies (String index_code, String vacancies, String[][] index_data) 
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(index_data[x][0] == null)
			{
				System.out.println("Error! No such index number");
			}
			if(index_data[x][1].equals(index_code))
			{
				String [] temp = index_data[x][2].split("-");
				y=temp.length;
				int inum = Integer.parseInt(vacancies);
				inum = inum -y;
				index_data[x][3] = Integer.toString(inum);
				break;

			}
		}
		return index_data;
	}

	public static String [][] swapIndexWithStudents(String m_num1,String m_num2, String index_1 , String index_2, String index_data[][], String [][] course_data, String [][] student_data)
	{
		// m_num1 = user 1
		// m_num2 = user 2
		//index_1 belongs to user1
		//index_2 belongs to user2
		// check if m_num 1 & m_num 2 exist or not
		int s1 = 0;
		int s2 = 0;
		int x,y,z=0;
		int a=0;
		int b=0;
		for(x=0;x<100;x++) // check if m_num1 exist and if m_num1 got index_1
		{
			if(student_data[x][0] == null)
				break;
			if(student_data[x][0].equals(m_num1))
			{
				s1 = x;
				String [] temp1 = student_data[x][4].split("-");
				y = temp1.length;
				for(z=0;z<y;z++)
				{
					if(temp1[z].equals(index_1))
					{
						a = 1; // M_num1 has index1
						break;
					}
				}
				break;
			}
		}		
		for(x=0;x<100;x++) // check if m_num2 exist and if m_num2 got index_2
		{
			if(student_data[x][0] == null)
				break;
			if(student_data[x][0].equals(m_num2))
			{
				s2 = x;
				String [] temp2 = student_data[x][4].split("-");
				y = temp2.length;
				for(z=0;z<y;z++)
				{
					if(temp2[z].equals(index_2))
					{
						b = 1; // M_num1 has index1
						break;
					}
				}
				break;
			}
		}
		if(a == 0 || b == 0) // Either M_num1 does not have index1 or M_num2 does not have index2
		{
			return index_data;
		}
		
		// check if the new index clash anot.
		// temp1 = student 1 classes
		// temp2 = student 2 classes
		
		String [] s1_classes = student_data[s1][4].split("-"); // get student 1 classes
		String [] s2_classes = student_data[s2][4].split("-"); // get student 2 classes
		a = s1_classes.length;
		b = s2_classes.length;
		String [] index_1_timings = new String[3]; // index_1 timings
		for(x=0;x<100;x++)
		{
			if(course_data[x][2].equals(index_1))
			{
				if(course_data[x][2]==null)
				{
					return index_data;
				}
				index_1_timings[0] = course_data[x][4];
				index_1_timings[1] = course_data[x][5];
				index_1_timings[2] = course_data[x][6];
				break;
			}
		}
		
		String [] index_2_timings = new String[3]; // index_1 timings
		for(x=0;x<100;x++)
		{
			if(course_data[x][2]==null)
			{
				return index_data;
			}
			if(course_data[x][2].equals(index_1))
			{
				index_2_timings[0] = course_data[x][4];
				index_2_timings[1] = course_data[x][5];
				index_2_timings[2] = course_data[x][6];
				break;
			}
		}
		
		String [][] classes_timing = new String [10][3];
		for(x=0;x<a;x++) // check if index_2 clashes with any of M_num1 classes
		{
			if(a==1)
				break;
			if(s1_classes[x].equals(index_1))
				continue;
			
			for(y=0;y<100;y++)
			{
				if(course_data[y][2]==null)
				{
					return index_data;
				}
				if(course_data[y][2].equals(s1_classes[x]))
				{
					classes_timing[x][0] = course_data[y][4]; // lecture timing
					classes_timing[x][1] = course_data[y][5]; // Lab timing
					classes_timing[x][2] = course_data[y][6]; // Tutorial Timing
					if(CourseData.checkClash(index_2_timings[0], classes_timing[x][0])) // If lecture clashes
					{
						return index_data;
					}
					if(CourseData.checkClash(index_2_timings[1], classes_timing[x][1])) // If lab clashes
					{
						return index_data;
					}
					if(CourseData.checkClash(index_2_timings[2], classes_timing[x][2])) // If tutorial clashes
					{
						return index_data;
					}
				}
			}
		}
		

		for(x=0;x<b;x++) // check if index_1 clashes with any of M_num2 classes
		{
			if(b==1)
				break;
			
			if(s2_classes[x].equals(index_2))
				continue;
			
			for(y=0;y<100;y++)
			{
				if(course_data[y][2]==null)
				{
					return index_data;
				}
				if(course_data[y][2].equals(s2_classes[x]))
				{
					classes_timing[x][0] = course_data[y][4]; // lecture timing
					classes_timing[x][1] = course_data[y][5]; // Lab timing
					classes_timing[x][2] = course_data[y][6]; // Tutorial Timing
					if(CourseData.checkClash(index_1_timings[0], classes_timing[x][0])) // If lecture clashes
					{
						return index_data;
					}
					if(CourseData.checkClash(index_1_timings[1], classes_timing[x][1])) // If lab clashes
					{
						return index_data;
					}
					if(CourseData.checkClash(index_1_timings[2], classes_timing[x][2])) // If tutorial clashes
					{
						return index_data;
					}
				}
			}
		}
		
		// index_1 goes to m_num2 & index_2 goes to m_num1;
		for(x=0;x<100;x++)
		{
			if(index_data[x][0]==null)
				break;
			if(index_data[x][1].equals(index_1))
			{
				String [] temp6 = index_data[x][2].split("-");
				y = temp6.length;
				index_data[x][2] = "";
				for(z=0;z<y;z++)
				{
					if(temp6[z].equals(m_num1))
					{
						temp6[z] = m_num2;
					}
				}
				for(z=0;z<y;z++)
				{
					if(z==(y-1))
					{
						index_data[x][2]=index_data[x][2] + temp6[z];
						break;
					}
						index_data[x][2] = index_data[x][2] + temp6[z] + "-";
				}
				break;
			}
			
		}
		
		for(x=0;x<100;x++)
		{
			if(index_data[x][0]==null)
				break;
			if(index_data[x][1].equals(index_2))
			{
				String [] temp6 = index_data[x][2].split("-");
				y = temp6.length;
				index_data[x][2] = "";
				for(z=0;z<y;z++)
				{
					if(temp6[z].equals(m_num2))
					{
						temp6[z] = m_num1;
					}
				}
				for(z=0;z<y;z++)
				{
					if(z==(y-1))
					{
						index_data[x][2]=index_data[x][2] + temp6[z];
						break;
					}
						index_data[x][2] = index_data[x][2] + temp6[z] + "-";
				}
				break;
			}
			
		}
		
		
		
		return index_data;
		
	}
	
	public static String [][] newCourse (String [][] index_data , String course_code,  String []Vacancies , String [] noOfIndex, int count)
	{
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(index_data[x][0].equals(course_code))
			{
				return index_data;
			}
			if(index_data[x][0] == null)
			{
				for(y=0;y<count;y++)
				{
					index_data[x][0] = course_code;
					index_data[x][1] = noOfIndex[y];
					index_data[x][2] = "NULL";
					index_data[x][3] = Vacancies[y];
					index_data[x][4] = "NULL";
					x++;					
				}
				break;
			}
		}
		return index_data;
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
}


























