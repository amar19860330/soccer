package com.amar.soccer.test.android;

import java.util.List;
import java.util.Map;

import com.amar.soccer.util.MathUtil;
import com.android.ddmlib.IDevice;
import com.android.hierarchyviewerlib.device.DeviceBridge;
import com.android.hierarchyviewerlib.device.ViewServerDevice;
import com.android.hierarchyviewerlib.models.ViewNode;
import com.android.hierarchyviewerlib.models.ViewNode.Property;
import com.android.hierarchyviewerlib.models.Window;

public class AccuratelyAnalyze
{
	public static void main( String [] args )
	{
		// AccuratelyAnalyze accuratelyAnalyze = new AccuratelyAnalyze( "E:/android/sdk/platform-tools/adb.exe" , "192.168.112.101:5555" , 1000 );
		AccuratelyAnalyze accuratelyAnalyze = new AccuratelyAnalyze( "D:/data/Android/sdk/platform-tools/adb.exe" , "192.168.56.101:5555" , 1000 );

		ViewNode rootViewNode = accuratelyAnalyze.getRootViewNode();
		ViewNode focusViewNode = accuratelyAnalyze.focusViewIsClick( 420 , 390 , rootViewNode );

		if ( focusViewNode != null )
		{
			System.out.println( focusViewNode.id + ":" + focusViewNode.namedProperties.get( "text:mText" ).value );
		}
		else
		{
			System.out.println( "not in" );
		}

		focusViewNode = accuratelyAnalyze.focusViewIsClick( 120 , 390 , rootViewNode );
		if ( focusViewNode != null )
		{
			System.out.println( focusViewNode.id );
		}
		else
		{
			System.out.println( "not in" );
		}

		//accuratelyAnalyze.destory();
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

	public ViewNode focusViewIsClick( int x , int y , ViewNode rootViewNode )
	{
		ViewNode focusViewNode = null;
		if ( rootViewNode != null )
		{
			ViewNode topFocusViewNode = findFocusedViewWhoIsTop( rootViewNode );
			if ( topFocusViewNode != null )
			{
				System.out.println( topFocusViewNode.id );
				int size[] = getSize( topFocusViewNode );
				if ( size != null )
				{
					boolean viewInThisArea = MathUtil.inThisArea( x , y - actionBarHeight , size[ 0 ] , size[ 1 ] , size[ 2 ] , size[ 3 ] );
					if ( viewInThisArea )
					{
						focusViewNode = topFocusViewNode;
					}
				}
			}
		}

		return focusViewNode;
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
		Window window = getWindow();

		String currentActivity = "";

		if ( window != null )
			currentActivity = window.getTitle();

		destory();
		return currentActivity;
	}

	public void destory()
	{
		actionBarHeight = 0;
		DeviceBridge.terminate();
	}

	int actionBarHeight = 0;

	private void detectActionBarHeight( ViewNode rootViewNode )
	{
		int rootHeight = rootViewNode.height;

		if ( rootViewNode.children != null && rootViewNode.children.size() > 0 )
		{
			ViewNode secondViewNode = rootViewNode.children.get( 0 );
			if ( secondViewNode.children != null && secondViewNode.children.size() == 3 && "id/content".equals( secondViewNode.children.get( 2 ).id ) )
			{
				int contentHight = secondViewNode.children.get( 2 ).height;
				if ( contentHight < rootHeight )
				{
					actionBarHeight = rootHeight - contentHight;
				}
			}
		}
	}

	public ViewNode getRootViewNode()
	{
		ViewNode rootViewNode = null;
		DeviceBridge.initDebugBridge( adbPath );
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
				rootViewNode = viewServerDevice.loadWindowData( window );
				break;
			}
		}
		detectActionBarHeight( rootViewNode );
		return rootViewNode;
	}

	public Window getWindow()
	{
		Window currentWindow = null;
		DeviceBridge.initDebugBridge( adbPath );
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
				currentWindow = window;
				break;
			}
		}
		return currentWindow;
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

	public int [] getSize( ViewNode viewNode )
	{
		int [] size = null;

		if ( viewNode.namedProperties != null )
		{
			Map<String,Property> propertyMap = viewNode.namedProperties;
			boolean existScreenX = propertyMap.containsKey( "layout:getLocationOnScreen_x()" );
			boolean existScreenY = propertyMap.containsKey( "layout:getLocationOnScreen_y()" );
			boolean existX = propertyMap.containsKey( "drawing:getX()" );
			boolean existY = propertyMap.containsKey( "drawing:getY()" );
			boolean existWidth = propertyMap.containsKey( "measurement:mMeasuredWidth" );
			boolean existHeight = propertyMap.containsKey( "measurement:mMeasuredHeight" );

			if ( ( existScreenX || existX ) && ( existScreenY || existY ) && existWidth && existHeight )
			{
				size = new int [ 4 ];
				try
				{
					String x = existScreenX ? propertyMap.get( "layout:getLocationOnScreen_x()" ).value : propertyMap.get( "drawing:getX()" ).value;
					String y = existScreenY ? propertyMap.get( "layout:getLocationOnScreen_y()" ).value : propertyMap.get( "drawing:getY()" ).value;
					String width = propertyMap.get( "measurement:mMeasuredWidth" ).value;
					String height = propertyMap.get( "measurement:mMeasuredHeight" ).value;
					size[ 0 ] = ( int ) Float.parseFloat( x );
					size[ 1 ] = ( int ) Float.parseFloat( y );
					size[ 2 ] = ( int ) Float.parseFloat( width );
					size[ 3 ] = ( int ) Float.parseFloat( height );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					size = null;
				}
			}
		}
		return size;
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
