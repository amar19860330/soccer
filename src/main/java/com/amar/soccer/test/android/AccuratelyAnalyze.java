package com.amar.soccer.test.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window.IExceptionHandler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.android.ddmlib.IDevice;
import com.android.hierarchyviewerlib.device.DeviceBridge;
import com.android.hierarchyviewerlib.device.ViewServerDevice;
import com.android.hierarchyviewerlib.models.ViewNode;
import com.android.hierarchyviewerlib.models.Window;
import com.android.hierarchyviewerlib.ui.util.PsdFile;
import com.android.uiautomator.UiAutomatorHelper;
import com.android.uiautomator.UiAutomatorHelper.UiAutomatorException;
import com.android.uiautomator.UiAutomatorHelper.UiAutomatorResult;
import com.android.uiautomator.UiAutomatorModel;
import com.android.uiautomator.UiAutomatorViewer;

public class AccuratelyAnalyze
{
	public static void main( String [] args )
	{
		AccuratelyAnalyze accuratelyAnalyze = new AccuratelyAnalyze( "E:/android/sdk/platform-tools/adb.exe" , "192.168.112.101:5555" , 1000 );
		// AccuratelyAnalyze accuratelyAnalyze = new AccuratelyAnalyze( "D:/data/Android/sdk/platform-tools/adb.exe" , "192.168.56.101:5555" , 1000 );

		// test findFocusedViewWhoIsTop
		// ViewNode rootViewNode = accuratelyAnalyze.getRootViewNode();
		// ViewNode topFocusViewNode = accuratelyAnalyze.findFocusedViewWhoIsTop( rootViewNode );
		// ViewNodeInfo viewNodeInfo = new ViewNodeInfo( topFocusViewNode );
		// viewNodeInfo.setActionHeight( accuratelyAnalyze.getActionBarHeight() );
		//
		// System.out.println( viewNodeInfo );

		// test findViewById
		// ViewNode findView = accuratelyAnalyze.findViewById( accuratelyAnalyze.getRootViewNode() , "id/edt_login_password" );
		// ViewNodeInfo viewNodeInfo = new ViewNodeInfo( findView );
		// System.out.println( viewNodeInfo );

		// Window window = accuratelyAnalyze.getWindow();
		// PsdFile psdFile = window.getHvDevice().captureLayers( window );
		// if ( psdFile != null )
		// {
		// try
		// {
		// psdFile.write( new FileOutputStream( "e:/test/2.psd" ) );
		// }
		// catch ( FileNotFoundException e )
		// {
		// }
		// }

		try
		{
			UiAutomatorViewer uiAutomatorViewer = new UiAutomatorViewer();
			uiAutomatorViewer.setBlockOnOpen( false );
			uiAutomatorViewer.open();
			IDevice device = accuratelyAnalyze.getWindow().getDevice();
			
			Thread.sleep( 3000 );
			accuratelyAnalyze.openUI( uiAutomatorViewer , device );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public void openUI( final UiAutomatorViewer uiAutomatorViewer , final IDevice device )
	{
		try
		{
			ProgressMonitorDialog dialog = new ProgressMonitorDialog( uiAutomatorViewer.getShell() );
			dialog.run( true , false , new IRunnableWithProgress()
			{
				public void run( IProgressMonitor monitor ) throws InvocationTargetException , InterruptedException
				{
					UiAutomatorHelper.UiAutomatorResult result = null;
					try
					{
						result = UiAutomatorHelper.takeSnapshot( device , monitor );
					}
					catch ( UiAutomatorHelper.UiAutomatorException e )
					{
						monitor.done();
						e.printStackTrace();
						return;
					}

					uiAutomatorViewer.setModel( result.model , result.uiHierarchy , result.screenshot );
					monitor.done();
				}
			} );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
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

	public int actionBarHeight = 0;

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

	void p( String info )
	{
		System.out.println( info );
	}

	public int getActionBarHeight()
	{
		return actionBarHeight;
	}

	public void setActionBarHeight( int actionBarHeight )
	{
		this.actionBarHeight = actionBarHeight;
	}
}
