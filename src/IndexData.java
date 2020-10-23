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
		test = dropStudent("E01","U01",test);
		
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
	
	public static String[][] addStudent(String IndexCode,String Student_num, String student_List[][])
	{
		String temp;
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(student_List[x][1]==null)
				break;
			if(student_List[x][1].equals(IndexCode))
			{
				if(!student_List[x][3].equals("0") && student_List[x][4].equals("NULL"))
				{
					if(student_List[x][2].equals("NULL"))
					{
						student_List[x][2] = ""+Student_num;
						y = Integer.parseInt(student_List[x][3]);
						y = y-1;
						student_List[x][3] = Integer.toString(y);
						break;
					}
					temp = student_List[x][2];
					temp = temp+"-"+Student_num;
					student_List[x][2] = temp;
					y = Integer.parseInt(student_List[x][3]);
					y = y-1;
					student_List[x][3] = Integer.toString(y);
					break;
				}
				else
				{
					if(student_List[x][4].equals("NULL"));
					{

						student_List[x][4] = Student_num;
						
					}
					student_List[x][4] =student_List[x][4]+"-"+Student_num;
				}
			}
	}
		return student_List;
	}
	
	
	public static String[][] dropStudent(String IndexCode,String Student_num, String student_List[][])
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
						System.out.println(x);
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
					y = temp2.length;
					temp ="";
					if(y==1)
					{
						student_List[x][4] = "NULL";
						break;
					}
					for(a=1;a<y;a++)
					{
						temp = temp+temp2[y];
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
	
	public static String [][] swapIndex (String current_course_index, String future_course_index, String m_num, String student_list[][])
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
		}
		return student_list;
	}
	


}
