package com.amar.soccer.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Shell
{

	public String cmd(String command)
	{
		StringBuffer sb = new StringBuffer(); 
		try 
		{  
			Process ps = Runtime.getRuntime().exec(command);  
			ps.waitFor();  
  
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
			
			String line;  
			while ((line = br.readLine()) != null) 
			{  
				sb.append(line).append("\n");  
			}  
		}   
		catch (Exception e) 			
		{  
			e.printStackTrace();  
		}  

		String result = sb.toString();  
		
		return result;
	}

	public String cmdForLong(String command)
	{
		StringBuffer sb = new StringBuffer(); 
		try 
		{  
			Process ps = Runtime.getRuntime().exec(command);  
  
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
			
			String line;  
			while ((line = br.readLine()) != null) 
			{  
				System.out.println(line);
			}  
		}   
		catch (Exception e) 			
		{  
			e.printStackTrace();  
		}  

		String result = sb.toString();  
		
		return result;
	}
}
