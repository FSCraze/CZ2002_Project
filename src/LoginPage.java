
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class LoginPage {
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
	 * this method checks if the current date/time is within the access period
	 * @return true if within, false if not within
	 */
	
	public static boolean verifyAccess() throws FileNotFoundException {
		Scanner accessScan;
		int i = 0;
		int[] acc = new int[20];
		accessScan = new Scanner(new File("AccessPeriod.txt")); 
		/*first line = start of access period 
		 * 2nd line = end of access period  */
		accessScan.useDelimiter("[,\n]");
		while(accessScan.hasNext()) {
			acc[i]= Integer.parseInt(accessScan.next().trim());
			i++;
		}
		
		
		Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);      
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
		SimpleDateFormat dFormat = new SimpleDateFormat("YYYY,MM,d,HH,mm");
		Calendar dateNow,startAccess,endAccess;
		 dateNow = new GregorianCalendar(year,month,day,hour,minute);
		 endAccess = new GregorianCalendar(acc[5],acc[6],acc[7],acc[8],acc[9]); //change this to end of access period 
		 startAccess = new GregorianCalendar(acc[0],acc[1],acc[2],acc[3],acc[4]); //change this to start of access period
		
		if (dateNow.after(startAccess) && dateNow.before(endAccess)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method verifies whether the login id and password of a student is valid. 
	 * @Param username refers to the username the user entered
	 * @password refers to the password that the user entered
	 * @return a for admin, s for student, z for default
	 */
public static String verifyLogin(String username, String password) 
	{
	
		String tempUsername;
		String tempPassword;
		Scanner x;
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String text = password;
			md.update(text.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
			password = String.format("%064x", new BigInteger(1, digest));
			x = new Scanner(new File("Login.txt"));
			x.useDelimiter("[,\n]");			
			while(x.hasNext())
			{
				tempUsername = x.next().trim();
				tempPassword = x.next().trim();
				if(tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim()))
				{
					if(username.trim().contains("A"))
					return "a";
					else
						return "s";
				}
			}

		}
		catch(Exception e)
		{
			
		}
		return "z";	
	}

/** 
 * This methods hashes a password using SHA-256 which is required as the password is stored using SHA-256 in the text file
 * @param password is plaintext password
 * @return hashed password 
 */
public static String returnPassword (String password) throws NoSuchAlgorithmException
{
	
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	String text = password;
	md.update(text.getBytes(StandardCharsets.UTF_8));
	byte[] digest = md.digest();
	password = String.format("%064x", new BigInteger(1, digest));
	return password;
}

/**
 * This method adds the Login details of a new Student
 * @Param m_num refers to the matriculation number of a student which will be the username
 * @Param password refers to the password of the new user
 */
	public static void newStudent (String m_num, String password) throws NoSuchAlgorithmException
	{
		
		password = returnPassword(password);
	    try {
			FileWriter fwStudent = new FileWriter("login.txt",true);
			fwStudent.write("\n"+m_num+","+password);
			fwStudent.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return;
	    
	}
	
	/**
	 * This method adds the Login details of a new Student
	 * @Param m_num refers to the matriculation number of a student which will be the username
	 * @Param password refers to the password of the new user
	 * @return login data
	 */
public static String [][] newStudent (String login_data [][], String m_num, String password)
	{
	
		int x,y=0;
		for(x=0;x<100;x++)
		{
			if(login_data[x][0].equals(m_num))
			{
				System.out.println("Error! Student with that matriculation number already exist");
				break;
			}
			if(login_data[x][0]==null)
			{
				login_data[x][0] = m_num;
				login_data[x][1] = password;
			}
		}
		return login_data;
	}
}



















