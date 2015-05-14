/*
 * MainForm.java
 * Created on __DATE__, __TIME__
 */

package com.amar.soccer.main;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

/**
 *
 * @author  __USER__
 */
public class MainForm extends javax.swing.JFrame
{

	private Map<String,Integer> appMap = new HashMap<String,Integer>();

	/** Creates new form MainForm */
	public MainForm()
	{
		RegistComponet.init();

		initComponents();
	}

	int appIndex = 0;

	public void enableCompone( String title , JComponent component )
	{
		if ( appMap.containsKey( title ) )
		{
			contentsTab.setSelectedIndex( appMap.get( title ) );
		}
		else
		{
			contentsTab.addTab( title , component );
			appMap.put( title , appIndex );
			contentsTab.setSelectedIndex( appIndex );
			appIndex ++ ;
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		contentsTab = new javax.swing.JTabbedPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		loginMenu = new javax.swing.JMenuItem();
		logoutMenu = new javax.swing.JMenuItem();
		forgetPwMenu = new javax.swing.JMenuItem();
		modifyPwMenu = new javax.swing.JMenuItem();
		userMenu = new javax.swing.JMenuItem();
		registMenu = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		lineupMenu = new javax.swing.JMenuItem();
		jMenu3 = new javax.swing.JMenu();
		launchMenu = new javax.swing.JMenuItem();
		buyMenu = new javax.swing.JMenuItem();
		jMenu5 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu4 = new javax.swing.JMenu();
		settingMenu = new javax.swing.JMenuItem();

		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );

		jMenu1.setText( "\u7528\u6237" );

		loginMenu.setText( "\u767b\u5f55" );
		loginMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				loginMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( loginMenu );

		logoutMenu.setText( "\u767b\u51fa" );
		logoutMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				logoutMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( logoutMenu );

		forgetPwMenu.setText( "\u5fd8\u8bb0\u5bc6\u7801" );
		forgetPwMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				forgetPwMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( forgetPwMenu );

		modifyPwMenu.setText( "\u4fee\u6539\u5bc6\u7801" );
		modifyPwMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				modifyPwMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( modifyPwMenu );

		userMenu.setText( "\u57fa\u672c\u4fe1\u606f" );
		userMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				userMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( userMenu );

		registMenu.setText( "\u6ce8\u518c" );
		registMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				registMenuMouseReleased( evt );
			}
		} );
		jMenu1.add( registMenu );

		jMenuBar1.add( jMenu1 );

		jMenu2.setText( "\u8db3\u7403" );

		lineupMenu.setText( "\u9635\u5bb9\u5b89\u6392" );
		lineupMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				lineupMenuMouseReleased( evt );
			}
		} );
		jMenu2.add( lineupMenu );

		jMenuBar1.add( jMenu2 );

		jMenu3.setText( "\u56e2\u8d2d" );

		launchMenu.setText( "\u53d1\u8d77\u56e2\u8d2d" );
		launchMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				launchMenuMouseReleased( evt );
			}
		} );
		jMenu3.add( launchMenu );

		buyMenu.setText( "\u4e70" );
		buyMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				buyMenuMouseReleased( evt );
			}
		} );
		jMenu3.add( buyMenu );

		jMenuBar1.add( jMenu3 );

		jMenu5.setText( "\u6d4b\u8bd5" );

		jMenuItem1.setText( "\u5b89\u5353\u6d4b\u8bd5" );
		jMenuItem1.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jMenuItem1MouseReleased( evt );
			}
		} );
		jMenu5.add( jMenuItem1 );

		jMenuBar1.add( jMenu5 );

		jMenu4.setText( "\u8bbe\u7f6e" );

		settingMenu.setText( "\u53c2\u6570\u8bbe\u7f6e" );
		settingMenu.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				settingMenuMouseReleased( evt );
			}
		} );
		jMenu4.add( settingMenu );

		jMenuBar1.add( jMenu4 );

		setJMenuBar( jMenuBar1 );

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( org.jdesktop.layout.GroupLayout.LEADING ).add( contentsTab , org.jdesktop.layout.GroupLayout.DEFAULT_SIZE , 623 , Short.MAX_VALUE ) );
		layout.setVerticalGroup( layout.createParallelGroup( org.jdesktop.layout.GroupLayout.LEADING ).add( contentsTab , org.jdesktop.layout.GroupLayout.DEFAULT_SIZE , 366 , Short.MAX_VALUE ) );

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jMenuItem1MouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_TestForm , RegistComponet.getComponet( RegistComponet.Form_TestForm ) );
	}

	private void settingMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_SettingForm , RegistComponet.getComponet( RegistComponet.Form_SettingForm ) );
	}

	private void buyMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_BuyForm , RegistComponet.getComponet( RegistComponet.Form_BuyForm ) );
	}

	private void launchMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_LaunchForm , RegistComponet.getComponet( RegistComponet.Form_LaunchForm ) );
	}

	private void lineupMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_LineupForm , RegistComponet.getComponet( RegistComponet.Form_LineupForm ) );
	}

	private void registMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_RegistForm , RegistComponet.getComponet( RegistComponet.Form_RegistForm ) );
	}

	private void userMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_UserForm , RegistComponet.getComponet( RegistComponet.Form_UserForm ) );
	}

	private void modifyPwMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_ModifyPwForm , RegistComponet.getComponet( RegistComponet.Form_ModifyPwForm ) );
	}

	private void forgetPwMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_ForgetPwForm , RegistComponet.getComponet( RegistComponet.Form_ForgetPwForm ) );
	}

	private void logoutMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_LogoutForm , RegistComponet.getComponet( RegistComponet.Form_LogoutForm ) );
	}

	private void loginMenuMouseReleased( java.awt.event.MouseEvent evt )
	{
		enableCompone( RegistComponet.Form_LoginForm , RegistComponet.getComponet( RegistComponet.Form_LoginForm ) );
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main( String args[] )
	{
		java.awt.EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				new MainForm().setVisible( true );
			}
		} );
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem buyMenu;

	private javax.swing.JTabbedPane contentsTab;

	private javax.swing.JMenuItem forgetPwMenu;

	private javax.swing.JMenu jMenu1;

	private javax.swing.JMenu jMenu2;

	private javax.swing.JMenu jMenu3;

	private javax.swing.JMenu jMenu4;

	private javax.swing.JMenu jMenu5;

	private javax.swing.JMenuBar jMenuBar1;

	private javax.swing.JMenuItem jMenuItem1;

	private javax.swing.JMenuItem launchMenu;

	private javax.swing.JMenuItem lineupMenu;

	private javax.swing.JMenuItem loginMenu;

	private javax.swing.JMenuItem logoutMenu;

	private javax.swing.JMenuItem modifyPwMenu;

	private javax.swing.JMenuItem registMenu;

	private javax.swing.JMenuItem settingMenu;

	private javax.swing.JMenuItem userMenu;
	// End of variables declaration//GEN-END:variables

}
