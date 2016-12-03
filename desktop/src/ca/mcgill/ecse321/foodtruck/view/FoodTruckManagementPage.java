package ca.mcgill.ecse321.foodtruck.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.Employee;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.FoodItem;
import ca.mcgill.ecse321.foodtruck.model.Shift;
import ca.mcgill.ecse321.foodtruck.model.Supply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

/**
 * Java Swing view for grubbr.
 * @author erick
 *
 */

public class FoodTruckManagementPage {

	private JFrame frame;
	private JTextField txtItemName;
	private JTextField txtItemPrice;
	private JLabel lblMenu;
	private JTextField txtSupplyName;
	private JTextField txtSupplyQty;
	private JLabel lblSupplies;
	private JTextField txtEquipmentName;
	private JTextField txtEquipmentQty;
	private JLabel lblEquipment;
	private JLabel lblShifts;
	private JLabel lblTopMenuList;

	//combo boxes
	private JComboBox<String> foodList;
	private JComboBox<String> supplyList;
	private JComboBox<String> equipmentList;
	private JComboBox<String> dayList;
	private JComboBox<String> employeeList;
	private JComboBox<String> shiftList;
	
	private JSpinner startTimeSpinner;
	private JSpinner endTimeSpinner;
	
	//data elements
	private String error = null;
	
	//index of selected items
	private Integer selectedFoodItem = -1;
	private Integer selectedSupply = -1;
	private Integer selectedEquipment = -1;
	private Integer selectedDay = -1;
	private Integer selectedEmployee = -1;
	private Integer selectedShift = -1;
	
	//hash maps containing objects
	private HashMap<Integer, FoodItem> foodItems;
	private HashMap<Integer, Supply> supplies;
	private HashMap<Integer, Equipment> equipment;
	private HashMap<Integer, String> days;
	private HashMap<Integer, Employee> employees;
	private HashMap<Integer, Shift> shifts;
	
	private JTextField txtEmployeeName;
	private JTextField txtEmployeeRole;
	private JTextField txtEmployeeSalary;
	private JTextField txtOrderAmount;
	
	private int lastNumOfEmployees = 0;

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
		frame.setBounds(100, 100, 507, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JSplitPane menuPane = new JSplitPane();
		tabbedPane.addTab("Menu", null, menuPane, null);
		
		JPanel panel = new JPanel();
		menuPane.setLeftComponent(panel);
		
		JLabel lblItemName = new JLabel("Item Name");
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Item Price");
		
		txtItemPrice = new JTextField();
		txtItemPrice.setColumns(10);
		
		JButton btnAddToMenu = new JButton("Add to menu");
		btnAddToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFoodItemButtonActionPerformed(e);
			}
		});
		
		lblTopMenuList = new JLabel("<html>");
		lblTopMenuList.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblTopMenu = new JLabel("Top 5 Menu Items:");
		
		JLabel label = new JLabel("");
		
		foodList = new JComboBox<String>();
		foodList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedFoodItem = cb.getSelectedIndex();
			}
		});
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderFoodButtonActionPerformed(e);
			}
		});
		
		txtOrderAmount = new JTextField();
		txtOrderAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(foodList, 0, 210, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblItemPrice)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddToMenu, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblItemName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTopMenuList, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTopMenu)
							.addPreferredGap(ComponentPlacement.RELATED, 96, GroupLayout.PREFERRED_SIZE)))
					.addGap(2))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(137, Short.MAX_VALUE)
					.addComponent(btnOrder)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addComponent(lblAmount)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtOrderAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
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
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(foodList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOrderAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnOrder)
					.addGap(15)
					.addComponent(lblTopMenu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTopMenuList, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		menuPane.setRightComponent(scrollPane);
		
		lblMenu = new JLabel("");
		lblMenu.setVerticalAlignment(SwingConstants.TOP);
		scrollPane.setViewportView(lblMenu);
		
		JSplitPane supplyPane = new JSplitPane();
		tabbedPane.addTab("Supplies", null, supplyPane, null);
		
		JPanel panel_1 = new JPanel();
		supplyPane.setLeftComponent(panel_1);
		
		JLabel lblAddSupply = new JLabel("Add Supply");
		
		JLabel lblEditSupply = new JLabel("Edit Quantity");
		
		JButton btnAddSupply = new JButton("Add");
		btnAddSupply.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		JLabel lblSupplyName = new JLabel("Name");
		
		txtSupplyName = new JTextField();
		txtSupplyName.setColumns(10);
		
		JLabel lblSupplyQty = new JLabel("Quantity");
		
		txtSupplyQty = new JTextField();
		txtSupplyQty.setColumns(10);
		
		JButton btnEditSupply = new JButton("Edit");
		btnEditSupply.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				editSupplyButtonActionPerformed(evt);
			}
		});
		
		supplyList = new JComboBox<String>();
		supplyList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply = cb.getSelectedIndex();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAddSupply)
									.addComponent(lblEditSupply))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblSupplyQty)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtSupplyQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(btnEditSupply, Alignment.TRAILING))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnAddSupply)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblSupplyName)
										.addGap(18)
										.addComponent(txtSupplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(supplyList, Alignment.TRAILING, 0, 196, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddSupply)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupplyName)
						.addComponent(txtSupplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddSupply)
					.addGap(18)
					.addComponent(lblEditSupply)
					.addGap(5)
					.addComponent(supplyList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupplyQty)
						.addComponent(txtSupplyQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditSupply)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		supplyPane.setRightComponent(scrollPane_2);
		
		lblSupplies = new JLabel("<html>");
		lblSupplies.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_2.setViewportView(lblSupplies);
		
		JSplitPane equipmentPane = new JSplitPane();
		tabbedPane.addTab("Equipment", null, equipmentPane, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		equipmentPane.setRightComponent(scrollPane_1);
		
		lblEquipment = new JLabel("<html>");
		lblEquipment.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_1.setRowHeaderView(lblEquipment);
		
		JPanel panel_2 = new JPanel();
		equipmentPane.setLeftComponent(panel_2);
		
		JLabel lblAddEquipment = new JLabel("Add Equipment");
		
		JLabel lblEquipmentName = new JLabel("Name");
		
		txtEquipmentName = new JTextField();
		txtEquipmentName.setColumns(10);
		
		JButton btnAddEquipment = new JButton("Add");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentButtonActionPerformed(e);
			}
		});
		
		JLabel lblEditEquipment = new JLabel("Edit Quantity");
		
		JLabel lblEquipmentQty = new JLabel("Quantity");
		
		txtEquipmentQty = new JTextField();
		txtEquipmentQty.setColumns(10);
		
		JButton btnEditEquipment = new JButton("Edit");
		btnEditEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editEquipmentButtonActionPerformed(e);
			}
		});
		
		equipmentList = new JComboBox<String>();
		equipmentList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEquipment = cb.getSelectedIndex();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAddEquipment)
									.addComponent(lblEditEquipment))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblEquipmentQty)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtEquipmentQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(btnEditEquipment, Alignment.TRAILING))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnAddEquipment)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblEquipmentName)
										.addGap(18)
										.addComponent(txtEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(equipmentList, Alignment.TRAILING, 0, 196, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddEquipment)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquipmentName)
						.addComponent(txtEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddEquipment)
					.addGap(18)
					.addComponent(lblEditEquipment)
					.addGap(5)
					.addComponent(equipmentList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquipmentQty)
						.addComponent(txtEquipmentQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditEquipment)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JSplitPane employeePane = new JSplitPane();
		tabbedPane.addTab("Employees", null, employeePane, null);
		
		JPanel panel_3 = new JPanel();
		employeePane.setLeftComponent(panel_3);
		
		JLabel lblName = new JLabel("Name");
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		
		txtEmployeeRole = new JTextField();
		txtEmployeeRole.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JLabel lblSalary = new JLabel("Salary");
		
		txtEmployeeSalary = new JTextField();
		txtEmployeeSalary.setColumns(10);
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployeeButtonActionPerformed(e);
			}
		});
		
		employeeList = new JComboBox<String>();
		employeeList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEmployee = cb.getSelectedIndex();
				System.out.println(selectedEmployee);
			}
		});
		
		JButton btnFireEmployee = new JButton("Fire Employee");
		btnFireEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEmployeeButtonActionPerformed(e);
			}
		});
		
		days = new HashMap<Integer,String>();
		dayList = new JComboBox<String>();
		dayList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedDay = cb.getSelectedIndex();
			}
		});
		setDaysOfWeek();
		
		JLabel lblName_1 = new JLabel("Name");
		
		JLabel lblDay = new JLabel("Day");
		
		JLabel lblStartTime = new JLabel("Start Time");
		
		JLabel lblEndTime = new JLabel("End Time");
		
		JButton btnAddShift = new JButton("Add Shift");
		btnAddShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addShiftButtonActionPerformed(e);
			}
		});
		
		JLabel lblShift = new JLabel("Shift");
		
		JButton btnRemoveShift = new JButton("Remove Shift");
		btnRemoveShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeShiftButtonActionPerformed(e);
			}
		});
		
		shiftList = new JComboBox<String>();
		shiftList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedShift = cb.getSelectedIndex();
			}
		});
		
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_startTimeSpinner = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(de_startTimeSpinner); // will only show the current time
		
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_endTimePoster = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(de_endTimePoster); // will only show the current time
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addGap(42)
											.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblName))
									.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
									.addComponent(txtEmployeeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRole)
										.addComponent(lblSalary))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtEmployeeSalary)
										.addComponent(txtEmployeeRole)))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblDay)
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnFireEmployee, Alignment.TRAILING)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addGap(6)
											.addComponent(dayList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblName_1)
									.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addGap(6)
											.addComponent(employeeList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(btnAddEmployee, Alignment.TRAILING)))
								.addComponent(btnAddShift)
								.addComponent(btnRemoveShift)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblShift)
							.addGap(43)
							.addComponent(shiftList, 0, 125, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(5)
									.addComponent(lblStartTime))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblEndTime)))
							.addGap(15)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(endTimeSpinner, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
								.addComponent(startTimeSpinner, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtEmployeeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmployeeRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRole))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmployeeSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalary))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddEmployee)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(employeeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFireEmployee)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(dayList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDay))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartTime)
						.addComponent(startTimeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndTime)
						.addComponent(endTimeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(btnAddShift)
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblShift)
						.addComponent(shiftList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(btnRemoveShift)
					.addGap(47))
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		employeePane.setRightComponent(scrollPane_4);
		
		lblShifts = new JLabel("<html>");
		lblShifts.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_4.setViewportView(lblShifts);
	}
	
	/**
	 * Refresh data to update the view
	 */
	
	public void refreshData() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		if (error == null || error.length()==0) {

			//handle menu
			txtItemName.setText("");
			txtItemPrice.setText("");
			txtOrderAmount.setText("");

			foodItems = new HashMap<Integer, FoodItem>();
			foodList.removeAllItems();
			Integer index = 0;
			
			String menuText = "<html>";
			Iterator<FoodItem> menuIterator = ftms.getFoodItems().iterator();
			
			while (menuIterator.hasNext()) {
				FoodItem item = menuIterator.next();
				foodItems.put(index,item);
				foodList.addItem(item.getName());
				menuText += item.getName()+" - "+item.getPrice()+"<br>";
				index++;
			}
			
			lblMenu.setText(menuText);
			
			FoodTruckController ftc = new FoodTruckController();
			ArrayList<FoodItem> topList = (ArrayList)ftc.getPopularItems();
			
			String topListText = "<html>";
			
			for (int i=0;i<topList.size();i++) {
				topListText+= (i+1)+") "+topList.get(i).getName()+" - "+topList.get(i).getAmountSold()+" sold<br>";
			}
			
			lblTopMenuList.setText(topListText);

			//handle inventory
			txtSupplyName.setText("");
			txtEquipmentName.setText("");
			txtSupplyQty.setText("");
			txtEquipmentQty.setText("");
			
			String supplyText ="<html>";
			
			supplies = new HashMap<Integer, Supply>();
			supplyList.removeAllItems();
			Iterator<Supply> sIt = ftms.getSupplies().iterator();
			index = 0;
			
			while (sIt.hasNext()){
				Supply supply = sIt.next();
				supplyText += supply.getName()+" - x"+supply.getCount()+"<br>";
				supplies.put(index, supply);
				supplyList.addItem(supply.getName());
				index++;
			}
			
			lblSupplies.setText(supplyText);
			
			String equipmentText ="<html>";
			
			equipment = new HashMap<Integer, Equipment>();
			equipmentList.removeAllItems();
			Iterator<Equipment> eIt = ftms.getEquipment().iterator();
			index = 0;
			while (eIt.hasNext()){
				Equipment equip = eIt.next();
				equipmentText += equip.getName()+" - x"+equip.getCount()+"<br>";
				equipment.put(index, equip);
				equipmentList.addItem(equip.getName());
				index++;
			}
			
			lblEquipment.setText(equipmentText);

			//handle employees only if number of employees hasn't changed
			//(to avoid conflicts with the shifts as they share the same combobox
			if (lastNumOfEmployees!=ftms.numberOfEmployees()) {
				txtEmployeeName.setText("");
				txtEmployeeRole.setText("");
				txtEmployeeSalary.setText("");
				
				employees = new HashMap<Integer, Employee>();
				employeeList.removeAllItems();
				Iterator<Employee> emIt = ftms.getEmployees().iterator();
				index = 0;
				
				while (emIt.hasNext()){
					Employee e = emIt.next();
					employees.put(index, e);
					employeeList.addItem(e.getName()+" - "+e.getRole());
					index++;
				}
				lastNumOfEmployees = ftms.numberOfEmployees();
			}
			
			//handle shifts for an employee
			if (selectedEmployee>-1) {
				Employee e = ftms.getEmployee(selectedEmployee);
				shifts = new HashMap<Integer, Shift>();
				shiftList.removeAllItems();
				Iterator<Shift> shIt = e.getShifts().iterator();
				index = 0;
				
				String shiftText = "<html>";
				
				while (shIt.hasNext()){
					Shift sh = shIt.next();
					shiftText += e.getName()+" - "+sh.getDayOfWeek()+" - "+sh.getStartTime().toString()+" to "+sh.getEndTime().toString()+"<br>";
					shifts.put(index, sh);
					shiftList.addItem(sh.getDayOfWeek()+"-"+sh.getStartTime().toString());
					index++;
				}
				lblShifts.setText(shiftText);
			} else {
				//remove all shifts from view if employee isn't selected
				shiftList.removeAllItems();
				lblShifts.setText("");
			}

		} else {
			JOptionPane.showMessageDialog(null,"ERROR: "+error);
			error = "";
		}
		
	}
	
	public void addFoodItemButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		
		try {
		ftc.createFoodItem(txtItemName.getText(),txtItemPrice.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void orderFoodButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
		ftc.orderFood(foodItems.get(selectedFoodItem),txtOrderAmount.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void addSupplyButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.createSupply(txtSupplyName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void editSupplyButtonActionPerformed(ActionEvent event) {
		
		if (selectedSupply < 0) {
			error = "You need to select a supply!";
		} else {
			FoodTruckController ftc = new FoodTruckController();
			try {
				ftc.editSupplyQuantity(supplies.get(selectedSupply), txtSupplyQty.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		refreshData();
	}
	
	public void addEquipmentButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.createEquipment(txtEquipmentName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void editEquipmentButtonActionPerformed(ActionEvent event) {
		
		if (selectedEquipment < 0) {
			error = "You need to select a piece of equipment!";
		} else {
			FoodTruckController ftc = new FoodTruckController();
			try {
				ftc.editEquipmentQuantity(equipment.get(selectedEquipment), txtEquipmentQty.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		refreshData();	
	}
	
	public void addEmployeeButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.createEmployee(txtEmployeeName.getText(), txtEmployeeRole.getText(), txtEmployeeSalary.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
	}
	
	public void removeEmployeeButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.removeEmployee(employees.get(selectedEmployee));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
	}
	
	public void addShiftButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		
		if (selectedEmployee<0) {
			error = "You need to select an employee!";
		} else {
			Employee employee = employees.get(selectedEmployee);
			
			//Jspinner returns date&time
			//force same date start/end to ensure only time differs;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) startTimeSpinner.getValue());
			calendar.set(2000, 1,1);
			Time startTime = new Time(calendar.getTime().getTime());
			calendar.setTime((Date) endTimeSpinner.getValue());
			calendar.set(2000, 1, 1);
			Time endTime = new Time(calendar.getTime().getTime());
			try {
				ftc.createShift(employee,days.get(selectedDay),startTime,endTime);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}
	
	public void removeShiftButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.cancelShift(employees.get(selectedEmployee),shifts.get(selectedShift));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
	}
	
	private void setDaysOfWeek() {
		days.put(0,"Sunday");
		dayList.addItem("Sunday");
		days.put(1,"Monday");
		dayList.addItem("Monday");
		days.put(2, "Tuesday");
		dayList.addItem("Tuesday");
		days.put(3, "Wednesday");
		dayList.addItem("Wednesday");
		days.put(4, "Thursday");
		dayList.addItem("Thursday");
		days.put(5, "Friday");
		dayList.addItem("Friday");
		days.put(6, "Saturday");
		dayList.addItem("Saturday");
		
	}
}
