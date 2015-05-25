/*
 * TestForm.java
 * Created on __DATE__, __TIME__
 */

package com.amar.soccer.test;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.amar.soccer.test.android.AndroidShell;
import com.amar.soccer.test.android.CallBack;
import com.amar.soccer.test.android.TestReaderFromEventFile;
import com.amar.soccer.test.android.TestRecordReceiver;
import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.util.Shell;

/**
 *
 * @author  __USER__
 */
public class TestForm extends javax.swing.JPanel
{

	private static final long serialVersionUID = - 6123222293657823872L;

	/** Creates new form TestForm */
	public TestForm()
	{
		initComponents();

		if ( deviceComboBox.getItemCount() > 0 )
		{
			deviceComboBox.removeAllItems();
		}
		deviceComboBox.addItem( noDevice );
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		recordButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		infoTextArea = new javax.swing.JTextArea();
		startButton = new javax.swing.JButton();
		deviceComboBox = new javax.swing.JComboBox();
		findButton = new javax.swing.JButton();
		useOldTestScriptButton = new javax.swing.JButton();
		clearInfoButton = new javax.swing.JButton();
		oldScriptTextField = new javax.swing.JTextField();
		openOldScriptButton = new javax.swing.JButton();

		recordButton.setText( "\u5f55\u5236\u65b0\u811a\u672c" );
		recordButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				recordButtonMouseReleased( evt );
			}
		} );

		infoTextArea.setColumns( 20 );
		infoTextArea.setRows( 5 );
		jScrollPane1.setViewportView( infoTextArea );

		startButton.setText( "\u5f00\u59cb\u6d4b\u8bd5" );
		startButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				startButtonMouseReleased( evt );
			}
		} );

		deviceComboBox.setModel( new javax.swing.DefaultComboBoxModel( new String [] { "Item 1", "Item 2", "Item 3", "Item 4" } ) );

		findButton.setText( "\u67e5\u627e\u8bbe\u5907" );
		findButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				findButtonMouseReleased( evt );
			}
		} );

		useOldTestScriptButton.setText( "\u4f7f\u7528\u65e7\u7684" );
		useOldTestScriptButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				useOldTestScriptButtonMouseReleased( evt );
			}
		} );

		clearInfoButton.setText( "\u6e05\u7a7a" );
		clearInfoButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				clearInfoButtonMouseReleased( evt );
			}
		} );

		openOldScriptButton.setText( "\u6253\u5f00" );
		openOldScriptButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				openOldScriptButtonMouseReleased( evt );
			}
		} );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addComponent( jScrollPane1 , javax.swing.GroupLayout.DEFAULT_SIZE , 578 , Short.MAX_VALUE )
										.addGroup(
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING , false )
																		.addComponent( useOldTestScriptButton , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																				Short.MAX_VALUE )
																		.addComponent( findButton , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) )
														.addGap( 7 , 7 , 7 )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																		.addGroup(
																				layout.createSequentialGroup()
																						.addComponent( oldScriptTextField , javax.swing.GroupLayout.DEFAULT_SIZE , 308 , Short.MAX_VALUE )
																						.addGap( 18 , 18 , 18 ).addComponent( openOldScriptButton ) )
																		.addComponent( deviceComboBox , 0 , 383 , Short.MAX_VALUE ) )
														.addGap( 14 , 14 , 14 )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
																		.addComponent( startButton , javax.swing.GroupLayout.DEFAULT_SIZE , 93 , Short.MAX_VALUE )
																		.addComponent( recordButton , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.DEFAULT_SIZE ,
																				javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) ) )
										.addComponent( clearInfoButton , javax.swing.GroupLayout.Alignment.TRAILING ) ).addContainerGap() ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( findButton ).addComponent( recordButton )
										.addComponent( deviceComboBox , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
										.addGroup(
												layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( useOldTestScriptButton )
														.addComponent( oldScriptTextField , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( openOldScriptButton ) ).addComponent( startButton ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jScrollPane1 , javax.swing.GroupLayout.DEFAULT_SIZE , 228 , Short.MAX_VALUE )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( clearInfoButton ).addContainerGap() ) );
	}// </editor-fold>
	//GEN-END:initComponents

	private void openOldScriptButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		// TODO add your handling code here:
		String filePath = oldScriptTextField.getText();
		File file = new File( filePath );
		if ( file.exists() )
		{
			new Shell().cmd( "cmd /c start " + filePath );
		}
		else
		{
			showInfo( "未找到文件" );
		}
	}

	List<AndroidEvent> eventList;

	private void useOldTestScriptButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		JFileChooser jFileChooser1 = new JFileChooser();
		File defaultFile = new File( TestRecordReceiver.DEFAULT_FILE_PATH );
		if ( ! defaultFile.exists() )
			defaultFile.mkdirs();
		jFileChooser1.setCurrentDirectory( defaultFile );
		jFileChooser1.setDialogTitle( "添加文件" );
		jFileChooser1.setFileSelectionMode( JFileChooser.FILES_ONLY );

		jFileChooser1.setSelectedFile( new File( TestRecordReceiver.DEFAULT_FILE_NAME ) );
		int ch = jFileChooser1.showDialog( this , "添加文件" );
		if ( ch == JFileChooser.APPROVE_OPTION )
		{
			TestReaderFromEventFile testReaderFromEventFile = new TestReaderFromEventFile();
			eventList = testReaderFromEventFile.xmlToList( jFileChooser1.getSelectedFile().getPath() );
			if ( eventList == null )
			{
				setInfo( "读取测试脚本失败" );
				oldScriptTextField.setText( "" );
			}
			else
			{
				setInfo( "读取测试脚本成功" );
				oldScriptTextField.setText( jFileChooser1.getSelectedFile().getPath() );
				for( AndroidEvent event : eventList )
				{
					setInfo( infoTextArea.getText() + "\n" + event.toString() );
				}
			}
		}
	}

	private void clearInfoButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		setInfo( "" );
	}

	boolean isTesting = false;
	TestReaderFromEventFile testReaderFromEventFile;
	private void startButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		if ( eventList == null )
		{
			showInfo( "没有测试数据" );
			return;
		}

		String device = deviceComboBox.getSelectedItem().toString();
		if ( device.equals( chooseDevicePlease ) || device.equals( noDevice ) )
		{
			setInfo( chooseDevicePlease );
			return;
		}

		if(isTesting)
		{
			isTesting = false;
			testReaderFromEventFile.stopTest();
			setInfo( "测试中止" );
			startButton.setText( "开始测试" );
		}
		else
		{
			setInfo( "开始测试" );
			startButton.setText( "中止测试" );
			testReaderFromEventFile = new TestReaderFromEventFile();
			testReaderFromEventFile.excuteScript( device , eventList , new CallBack<String>()
			{

				@Override
				public void callback( String info ,int status)
				{
					setInfo( infoTextArea.getText() + "\n" + info );
				}

			} );
			isTesting = true;
		}
		
	}

	public synchronized void setInfo( String info )
	{
		infoTextArea.setText( info );
		infoTextArea.setCaretPosition( infoTextArea.getDocument().getLength() );
	}

	public void showInfo( String info )
	{
		JOptionPane.showMessageDialog( this , info );
	}

	String chooseDevicePlease = "请选择设备";

	String noDevice = "没有设备";

	private void findButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		AndroidShell androidShell = new AndroidShell();
		List<String> devices = androidShell.getDevices();

		if ( deviceComboBox.getItemCount() > 0 )
		{
			deviceComboBox.removeAllItems();
		}

		deviceComboBox.addItem( chooseDevicePlease );
		if ( devices != null && devices.size() > 0 )
			for( String device : devices )
			{
				deviceComboBox.addItem( device );
			}
	}

	boolean isRecord = false;

	TestRecordReceiver testRecordReceiver;

	private void recordButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		if ( isRecord )
		{
			JFileChooser jFileChooser1 = new JFileChooser();
			File defaultFile = new File( TestRecordReceiver.DEFAULT_FILE_PATH );
			if ( ! defaultFile.exists() )
				defaultFile.mkdirs();
			jFileChooser1.setCurrentDirectory( defaultFile );
			jFileChooser1.setDialogTitle( "添加文件" );
			jFileChooser1.setFileSelectionMode( JFileChooser.FILES_ONLY );

			jFileChooser1.setSelectedFile( new File( TestRecordReceiver.DEFAULT_FILE_NAME ) );
			int ch = jFileChooser1.showDialog( this , "添加文件" );
			if ( ch == JFileChooser.APPROVE_OPTION )
			{
				testRecordReceiver.saveRecord( jFileChooser1.getSelectedFile().getPath() );
				eventList = testRecordReceiver.getEventList();
				testRecordReceiver.stop();
				testRecordReceiver = null;
				recordButton.setText( "开始录制" );
				isRecord = false;
				setInfo( "保存成功." );
				oldScriptTextField.setText( jFileChooser1.getSelectedFile().getPath() );
			}
		}
		else
		{
			String device = deviceComboBox.getSelectedItem().toString();
			if ( device.equals( chooseDevicePlease ) || device.equals( noDevice ) )
			{
				setInfo( chooseDevicePlease );
				return;
			}
			isRecord = true;
			recordButton.setText( "停止录制" );

			testRecordReceiver = new TestRecordReceiver( device );

			testRecordReceiver.setUiCallBack( new CallBack<AndroidEvent>()
			{

				@Override
				public void callback( AndroidEvent t ,int status)
				{
					setInfo( infoTextArea.getText() + "\n" + t.toString() );
				}

			} );

			testRecordReceiver.start();
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton clearInfoButton;

	private javax.swing.JComboBox deviceComboBox;

	private javax.swing.JButton findButton;

	private javax.swing.JTextArea infoTextArea;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextField oldScriptTextField;

	private javax.swing.JButton openOldScriptButton;

	private javax.swing.JButton recordButton;

	private javax.swing.JButton startButton;

	private javax.swing.JButton useOldTestScriptButton;
	// End of variables declaration//GEN-END:variables

}
