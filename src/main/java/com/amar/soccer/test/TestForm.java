/*
 * TestForm.java
 * Created on __DATE__, __TIME__
 */

package com.amar.soccer.test;

import java.util.List;

import com.amar.soccer.test.android.AndroidShell;

/**
 *
 * @author  __USER__
 */
public class TestForm extends javax.swing.JPanel
{

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

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		testButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		infoTextArea = new javax.swing.JTextArea();
		startButton = new javax.swing.JButton();
		deviceComboBox = new javax.swing.JComboBox();
		findButton = new javax.swing.JButton();

		testButton.setText( "\u5f00\u59cb\u5f55\u5236" );
		testButton.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				testButtonMouseReleased( evt );
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addComponent( testButton )
										.addGroup(
												layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING , false )
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING ,
																layout.createSequentialGroup().addComponent( findButton ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																		.addComponent( deviceComboBox , 0 , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ).addGap( 18 , 18 , 18 )
																		.addComponent( startButton ) )
														.addComponent( jScrollPane1 , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.PREFERRED_SIZE , 358 ,
																javax.swing.GroupLayout.PREFERRED_SIZE ) ) ).addContainerGap( 30 , Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addGap( 33 , 33 , 33 )
						.addComponent( testButton )
						.addGap( 9 , 9 , 9 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( findButton ).addComponent( startButton )
										.addComponent( deviceComboBox , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 150 , javax.swing.GroupLayout.PREFERRED_SIZE ).addContainerGap( 50 , Short.MAX_VALUE ) ) );
	}// </editor-fold>
	//GEN-END:initComponents

	private void startButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
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

	Thread recordThread;

	MonitorMobile monitorMobile;

	private void testButtonMouseReleased( java.awt.event.MouseEvent evt )
	{
		if ( isRecord )
		{
			try
			{
				monitorMobile.stop();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
			testButton.setText( "开始录制" );
			isRecord = false;
		}
		else
		{
			String device = deviceComboBox.getSelectedItem().toString();

			if ( device.equals( chooseDevicePlease ) || device.equals( noDevice ) )
			{
				infoTextArea.setText( infoTextArea.getText() + "\n" + chooseDevicePlease );
				return;
			}

			testButton.setText( "停止录制" );
			isRecord = true;

			monitorMobile = new MonitorMobile();
			monitorMobile.setDevice( device );

			monitorMobile.setCallBack( new MonitorMobile.CallBack()
			{
				@Override
				public void event( int type , int x , int y )
				{
					infoTextArea.setText( infoTextArea.getText() + "\n" + "touch:" + x + "," + y );
				}
			} );

			recordThread = new Thread( monitorMobile );
			recordThread.start();

		}

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JComboBox deviceComboBox;

	private javax.swing.JButton findButton;

	private javax.swing.JTextArea infoTextArea;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JButton startButton;

	private javax.swing.JButton testButton;
	// End of variables declaration//GEN-END:variables

}
