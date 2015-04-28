package com.amar.soccer.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil
{
	public static Properties readProperties(String filename)  
    {  
		
		String filePath = PropertiesUtil.class.getResource( filename ).toString();//获取绝对路径  
		filePath = filePath.substring(6); //截掉路径的”file:“前缀  
		
        Properties properties = new Properties();  
        InputStream inputStream =null;
        try  
        {
            inputStream = new FileInputStream(filePath);  
            properties.load(inputStream);  
            inputStream.close(); //关闭流 
            inputStream = null;
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally
        {
        	if(inputStream!=null)
        	{
        		try
				{
					inputStream.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
        	}
        }
       return properties;
    }  
	
	public static void writeProperties(Properties properties,String filename)  
    { 
		OutputStream outputStream = null;
        String filePath = PropertiesUtil.class.getResource( filename ).toString();//获取绝对路径  
		filePath = filePath.substring(6); //截掉路径的”file:“前缀
        try  
        {  
            outputStream = new FileOutputStream(filePath);  
            properties.store( outputStream , null );
            outputStream.close();  
            outputStream = null;
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally
        {
        	if(outputStream!=null)
        	{
        		try
				{
        			outputStream.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
        	}
        }
    }
	
	public static void writeProperties(String filename,String key,String value)  
    {  
        OutputStream outputStream = null;
        String filePath = PropertiesUtil.class.getResource( filename ).toString();//获取绝对路径  
		filePath = filePath.substring(6); //截掉路径的”file:“前缀 
		
        try  
        {  
            outputStream = new FileOutputStream(filePath);  
            Properties properties = new Properties();  
            properties.setProperty(key, value);  
            properties.store( outputStream , null );
            outputStream.close();  
            outputStream = null;
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally
        {
        	if(outputStream!=null)
        	{
        		try
				{
        			outputStream.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
        	}
        }
    }  
}
