package ca.mcgill.ecse321.foodtruck.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JSplitPane;

public class FoodTruckManagementPage {

	private JFrame frame;
	private JTextField txtItemName;
	private JTextField txtItemPrice;
	private JLabel lblMenu;
	
	//data elements
	private String error = null;
	private HashMap menu;

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
		refreshData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 173);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
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
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblItemName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblItemPrice)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddToMenu))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblItemName))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemPrice)
						.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAddToMenu)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		lblMenu = new JLabel("");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setRightComponent(lblMenu);
	}
	
	/**
	 * Refresh data to update the view
	 */
	
	public void refreshData() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		if (error == null || error.length()==0) {
			txtItemName.setText("");
			txtItemPrice.setText("");
		} else {
			JOptionPane.showMessageDialog(null,"ERROR: "+error);
		}
		
		lblMenu.setText("");
		String menuText = "<html>";
		Iterator<MenuItem> menuIterator = ftms.getMenuItems().iterator();
		
		while (menuIterator.hasNext()) {
			MenuItem item = menuIterator.next();
			menuText += item.getName()+" - "+item.getPrice()+"<br>";
		}
		
		lblMenu.setText(menuText);
		
	}
	
	public void addMenuItemButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		
		try {
		ftc.createMenuItem(txtItemName.getText(),txtItemPrice.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
}
