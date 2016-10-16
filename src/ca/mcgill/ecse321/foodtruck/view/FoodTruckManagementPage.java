package ca.mcgill.ecse321.foodtruck.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodTruckManagementPage {

	private JFrame frame;
	private JTextField txtItemName;
	private JTextField txtItemPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodTruckManagementPage window = new FoodTruckManagementPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodTruckManagementPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 216, 173);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JLabel lblItemName = new JLabel("Item Name");
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Item Price");
		
		txtItemPrice = new JTextField();
		txtItemPrice.setColumns(10);
		
		JButton btnAddToMenu = new JButton("Add to menu");
		btnAddToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuItemButtonActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblItemName)
							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
							.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblItemPrice)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddToMenu))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemName)
						.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemPrice)
						.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAddToMenu)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	/**
	 * Refresh data to update the view
	 */
	
	public void refreshData() {
		txtItemName.setText("");
		txtItemPrice.setText("");
	}
	
	public void addMenuItemButtonActionPerformed(ActionEvent e) {
		FoodTruckController ftc = new FoodTruckController();
		ftc.createMenuItem(txtItemName.getText(),Double.parseDouble(txtItemPrice.getText()));
		
		refreshData();
	}
}
