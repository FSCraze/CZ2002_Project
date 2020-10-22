
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
public class LoginPage {

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
public static String returnPassword (String password) throws NoSuchAlgorithmException
{
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	String text = password;
	md.update(text.getBytes(StandardCharsets.UTF_8));
	byte[] digest = md.digest();
	password = String.format("%064x", new BigInteger(1, digest));
	return password;
}
}

