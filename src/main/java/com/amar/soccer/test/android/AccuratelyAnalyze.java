package com.amar.soccer.test.android;

import java.util.List;

import com.android.ddmlib.IDevice;
import com.android.hierarchyviewerlib.device.DeviceBridge;
import com.android.hierarchyviewerlib.device.ViewServerDevice;
import com.android.hierarchyviewerlib.models.ViewNode;
import com.android.hierarchyviewerlib.models.Window;

public class AccuratelyAnalyze
{
	public static void main( String [] args )
	{
		AccuratelyAnalyze accuratelyAnalyze = new AccuratelyAnalyze( "E:/android/sdk/platform-tools/adb.exe" , "192.168.112.101:5555" , 1000 );
		System.out.println( accuratelyAnalyze.getCurrentActivity() );
	}

	String adbPath;

	String deviceName;

	long connectTime;

	public AccuratelyAnalyze( String adbPath , String deviceName , long connectTime )
	{
		this.adbPath = adbPath;
		this.deviceName = deviceName;
		this.connectTime = connectTime;
	}

	public void demo1() throws Exception
	{
		p( "start" );
		IDevice [] devices = null;

		while ( null == devices || 0 == devices.length )
		{
			DeviceBridge.initDebugBridge( "E:/android/sdk/platform-tools/adb.exe" );
			// it must wait for some time, otherwise will throw exception
			try
			{
				Thread.sleep( 1000 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			devices = DeviceBridge.getDevices();
			break;
		}

		for( IDevice device : devices )
		{
			p( device.getSerialNumber() + " isOnline:" + device.isOnline() );

			ViewServerDevice viewServerDevice = new ViewServerDevice( device );
			viewServerDevice.initializeViewDebug();

			Window [] windows = viewServerDevice.getWindows();
			if ( windows == null )
			{
				return;
			}
			Window focusedWindow = null;
			for( Window window : windows )
			{
				if ( window.getHashCode() == viewServerDevice.getFocusedWindow() )
				{
					focusedWindow = window;
					p( viewServerDevice.getFocusedWindow() + ":" + window.getTitle() );
					break;
				}
			}

			if ( focusedWindow == null )
				return;

			ViewNode entryViewNode = viewServerDevice.loadWindowData( focusedWindow );
			ViewNode viewNode = findViewById( entryViewNode , "id/help_item_01" );
			
			printInfo( viewNode );
		}
		DeviceBridge.terminate();
	}
	
	public void demo2() throws Exception
	{
		p( "start" );
		IDevice [] devices = null;
		while ( null == devices || 0 == devices.length )
		{
			DeviceBridge.initDebugBridge( "E:/android/sdk/platform-tools/adb.exe" );

			try
			{
				Thread.sleep( 1000 ); // it must wait for some time, otherwise will throw exception
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			devices = DeviceBridge.getDevices();
			break;
		}

		for( IDevice device : devices )
		{
			p( device.getSerialNumber() + " isOnline:" + device.isOnline() );

			ViewServerDevice viewServerDevice = new ViewServerDevice( device );
			viewServerDevice.initializeViewDebug();

			Window [] windows = viewServerDevice.getWindows();
			if ( windows == null )
			{
				return;
			}
			Window focusedWindow = null;
			for( Window window : windows )
			{
				if ( window.getHashCode() == viewServerDevice.getFocusedWindow() )
				{
					focusedWindow = window;
					p( viewServerDevice.getFocusedWindow() + ":" + window.getTitle() );
					break;
				}
			}

			if ( focusedWindow == null )
				return;

			ViewNode entryViewNode = viewServerDevice.loadWindowData( focusedWindow );
			ViewNode viewNode = findFocusedViewWhoIsTop( entryViewNode );
			printInfo( viewNode );
		}
		DeviceBridge.terminate();
	}
	
	public String getCurrentActivity()
	{
		DeviceBridge.initDebugBridge( adbPath );
		String currentActivity = null;
		try
		{
			Thread.sleep( connectTime );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}

		IDevice [] devices = DeviceBridge.getDevices();

		IDevice device = null;

		for( IDevice iDevice : devices )
		{
			if ( deviceName.equals( iDevice.getSerialNumber() ) )
			{
				device = iDevice;
				break;
			}
		}

		if ( device == null )
		{
			return null;
		}

		ViewServerDevice viewServerDevice = new ViewServerDevice( device );
		viewServerDevice.initializeViewDebug();

		Window [] windows = viewServerDevice.getWindows();
		if ( windows == null )
		{
			return null;
		}
		for( Window window : windows )
		{
			if ( window.getHashCode() == viewServerDevice.getFocusedWindow() )
			{
				currentActivity = window.getTitle();
				break;
			}
		}
		DeviceBridge.terminate();

		return currentActivity;
	}
	
	public ViewNode findViewById( ViewNode entryViewNode , String id )
	{
		ViewNode existViewNode = null;

		if ( id.equals( entryViewNode.id ) )
		{
			existViewNode = entryViewNode;
		}
		else
		{
			List<ViewNode> nodeList = entryViewNode.children;

			if ( nodeList != null )
			{
				for( ViewNode viewNode : nodeList )
				{
					existViewNode = findViewById( viewNode , id );
					if ( existViewNode != null )
					{
						break;
					}
				}
			}
		}

		return existViewNode;
	}

	public ViewNode findFocusedViewWhoIsTop( ViewNode entryViewNode )
	{
		ViewNode existViewNode = null;

		if ( entryViewNode.hasFocus )
		{
			existViewNode = entryViewNode;
		}

		List<ViewNode> nodeList = entryViewNode.children;
		if ( nodeList != null )
		{
			for( ViewNode viewNode : nodeList )
			{
				ViewNode subExistViewNode = findFocusedViewWhoIsTop( viewNode );
				if ( subExistViewNode != null )
				{
					existViewNode = subExistViewNode;
				}
			}
		}

		return existViewNode;
	}
	
	public void printInfo( ViewNode viewNode )
	{
		if ( viewNode != null )
		{
			if ( viewNode.namedProperties != null )
			{
				String x = viewNode.namedProperties.get( "layout:getLocationOnScreen_x()" ).value;
				String y = viewNode.namedProperties.get( "layout:getLocationOnScreen_y()" ).value;
				String width = viewNode.namedProperties.get( "measurement:mMeasuredWidth" ).value;
				String height = viewNode.namedProperties.get( "measurement:mMeasuredHeight" ).value;
				String text = viewNode.namedProperties.get( "text:mText" ) == null ? "" : viewNode.namedProperties.get( "text:mText" ).value;
				p( "info ==> hasFocus:" + viewNode.hasFocus + " ,id:" + viewNode.id + " ,type:" + viewNode.name + " ,x:" + x + " ,y:" + y + " ,width:" + width + " ,height:" + height + " ,text:"
						+ text );
			}
			else
			{
				p( "no text" );
			}
		}
		else
		{
			p( "not exist" );
		}
	}
	
	void p( String info )
	{
		System.out.println( info );
	}
}
