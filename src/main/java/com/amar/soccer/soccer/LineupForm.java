/*
 * LineupForm.java
 * Created on __DATE__, __TIME__
 */

package com.amar.soccer.soccer;

/**
 *
 * @author  __USER__
 */
public class LineupForm extends javax.swing.JPanel
{

	/** Creates new form LineupForm */
	public LineupForm()
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

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		jTextArea1.setColumns( 20 );
		jTextArea1.setRows( 5 );
		jScrollPane1.setViewportView( jTextArea1 );

		jTextField1.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jTextField1ActionPerformed( evt );
			}
		} );

		jButton1.setText( "\u53d1\u9001" );

		jPanel1.setBackground( new java.awt.Color( 51 , 255 , 0 ) );

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout( jPanel1 );
		jPanel1.setLayout( jPanel1Layout );
		jPanel1Layout.setHorizontalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGap( 0 , 310 , Short.MAX_VALUE ) );
		jPanel1Layout.setVerticalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGap( 0 , 429 , Short.MAX_VALUE ) );

		jButton2.setText( "\u6dfb\u52a0\u4eba\u5458" );
		jButton2.addActionListener( new java.awt.event.ActionListener()
		{
			public void actionPerformed( java.awt.event.ActionEvent evt )
			{
				jButton2ActionPerformed( evt );
			}
		} );

		jButton3.setText( "\u53d1\u9001" );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
												.addComponent( jScrollPane1 , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.DEFAULT_SIZE , 389 , Short.MAX_VALUE )
												.addComponent( jTextField1 , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.DEFAULT_SIZE , 389 , Short.MAX_VALUE )
												.addComponent( jButton3 ) )
								.addGap( 44 , 44 , 44 )
								.addGroup(
										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
												.addComponent( jPanel1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
												.addGroup( layout.createSequentialGroup().addComponent( jButton1 ).addGap( 18 , 18 , 18 ).addComponent( jButton2 ) ) ).addGap( 35 , 35 , 35 ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
										.addComponent( jPanel1 , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
												Short.MAX_VALUE )
										.addGroup(
												layout.createSequentialGroup().addComponent( jScrollPane1 , javax.swing.GroupLayout.DEFAULT_SIZE , 373 , Short.MAX_VALUE ).addGap( 18 , 18 , 18 )
														.addComponent( jTextField1 , javax.swing.GroupLayout.PREFERRED_SIZE , 38 , javax.swing.GroupLayout.PREFERRED_SIZE ) ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButton2 ).addComponent( jButton1 ).addComponent( jButton3 ) )
						.addContainerGap() ) );
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	private void jTextField1ActionPerformed( java.awt.event.ActionEvent evt )
	{
		// TODO add your handling code here:
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JButton jButton3;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextArea1;

	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}
