/*
 * SettingForm.java
 * Created on __DATE__, __TIME__
 */

package com.amar.soccer.setting;

/**
 *
 * @author  __USER__
 */
public class SettingForm extends javax.swing.JPanel
{

	/** Creates new form SettingForm */
	public SettingForm()
	{
		initComponents();
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

		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jTextField2 = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();

		jTextField1.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jTextField1ActionPerformed( evt );
			}
		} );

		jLabel1.setText( "IP\uff1a" );

		jLabel2.setText( "\u7aef\u53e3\uff1a" );

		jButton1.setText( "\u4fdd\u5b58" );
		jButton1.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jButton1ActionPerformed( evt );
			}
		} );

		jTextField2.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jTextField2ActionPerformed( evt );
			}
		} );

		jButton2.setText( "\u6062\u590d" );
		jButton2.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jButton2ActionPerformed( evt );
			}
		} );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout
				.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				.addGroup(
						layout.createSequentialGroup().addContainerGap( 84 , Short.MAX_VALUE ).addComponent( jLabel1 ).addGap( 18 , 18 , 18 )
								.addComponent( jTextField2 , javax.swing.GroupLayout.PREFERRED_SIZE , 187 , javax.swing.GroupLayout.PREFERRED_SIZE ).addGap( 40 , 40 , 40 ).addComponent( jLabel2 )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent( jTextField1 , javax.swing.GroupLayout.PREFERRED_SIZE , 62 , javax.swing.GroupLayout.PREFERRED_SIZE ).addGap( 124 , 124 , 124 ) )
				.addGroup( layout.createSequentialGroup().addGap( 146 , 146 , 146 ).addComponent( jButton2 ).addGap( 59 , 59 , 59 ).addComponent( jButton1 ).addContainerGap( 267 , Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel1 )
										.addComponent( jTextField2 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jLabel2 )
										.addComponent( jTextField1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , 184 , Short.MAX_VALUE )
						.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButton2 ).addComponent( jButton1 ) ).addGap( 54 , 54 , 54 ) ) );
	}// </editor-fold>
	//GEN-END:initComponents

	private void jTextField1ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	private void jTextField2ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JTextField jTextField1;

	private javax.swing.JTextField jTextField2;
	// End of variables declaration//GEN-END:variables

}