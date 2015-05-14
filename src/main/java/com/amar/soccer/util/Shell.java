package com.amar.soccer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Shell
{
	private OutputStream output;

	private Process process;

	private InputStream input;

	public Shell()
	{

	}

	public String cmd( String command )
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			Process ps = Runtime.getRuntime().exec( command );
			ps.waitFor();

			BufferedReader br = new BufferedReader( new InputStreamReader( ps.getInputStream() ) );

			String line;
			while ( ( line = br.readLine() ) != null )
			{
				sb.append( line ).append( "\n" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		String result = sb.toString();

		return result;
	}

	public void originShell( String command )
	{
		System.out.println(command);
		try
		{
			process = Runtime.getRuntime().exec( command );
			input = process.getInputStream();
			output = process.getOutputStream();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public String cmdNoWait( String command )
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			Process ps = Runtime.getRuntime().exec( command );

			BufferedReader br = new BufferedReader( new InputStreamReader( ps.getInputStream() ) );

			String line;
			while ( ( line = br.readLine() ) != null )
			{
				sb.append( line ).append( "\n" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		String result = sb.toString();

		return result;
	}

	public Process getProcess()
	{
		return process;
	}

	public void setProcess( Process process )
	{
		this.process = process;
	}

	public OutputStream getOutput()
	{
		return output;
	}

	public void setOutput( OutputStream output )
	{
		this.output = output;
	}

	public InputStream getInput()
	{
		return input;
	}

	public void setInput( InputStream input )
	{
		this.input = input;
	}

}
