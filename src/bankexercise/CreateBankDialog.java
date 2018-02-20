package bankexercise;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CreateBankDialog extends JFrame {

	
	private final static int TABLE_SIZE = 29;
	Random rand = new Random();
	
	ArrayList<BankAccount> accountList;

	HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	
	
	
	
	public void put(int key, BankAccount value){
		int hash = (key%TABLE_SIZE);

		while(table.containsKey(key)){
			hash = hash+1;
		}
		table.put(hash, value);
	}
	
	
	
	
	// Constructor code based on that for the Create and Edit dialog classes in the Shapes exercise.

	JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	
	
	JComboBox comboBox;
	JTextField accountNumberTextField;
	final JTextField firstNameTextField, surnameTextField, accountTypeTextField, balanceTextField, overdraftTextField;
	
	CreateBankDialog(HashMap accounts) {
		
		super("Add Bank Details");
		
		table = accounts;
		
		setLayout(new BorderLayout());
		
		JPanel dataPanel = new JPanel(new MigLayout());
		
		
		
		
		
		
		
		String[] comboTypes = {"Current", "Deposit"};
		
		final JComboBox comboBox = new JComboBox(comboTypes);
		
		
		accountNumberLabel = new JLabel("Photograph file name: ");
		accountNumberTextField = new JTextField(15);
		
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(true);
		
		dataPanel.add(accountNumberLabel, "growx, pushx");
		dataPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(true);
		
		dataPanel.add(surnameLabel, "growx, pushx");
		dataPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(true);
		
		dataPanel.add(firstNameLabel, "growx, pushx");
		dataPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(true);
		
		dataPanel.add(accountTypeLabel, "growx, pushx");	
		dataPanel.add(comboBox, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setText("0.0");
		balanceTextField.setEditable(false);
		
		dataPanel.add(balanceLabel, "growx, pushx");
		dataPanel.add(balanceTextField, "growx, pushx, wrap");
		
		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setText("0.0");
		overdraftTextField.setEditable(false);
		
		dataPanel.add(overdraftLabel, "growx, pushx");
		dataPanel.add(overdraftTextField, "growx, pushx, wrap");
		
		add(dataPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String accountNumber = accountNumberTextField.getText();
				
				
							
				
				String surname = surnameTextField.getText();
				String firstName = firstNameTextField.getText();
			
				String accountType = comboBox.getSelectedItem().toString();
				
				String balanceStr = balanceTextField.getText();
				String overdraftStr = overdraftTextField.getText();
				
				

				double balance;
				double overdraft;
				
		
				if (accountNumber != null && accountNumber.length()==8 && surname != null && firstName != null && accountType != null) {
					try {
						
						boolean idTaken = false;
						boolean accNumTaken=false;
							
							int randNumber = rand.nextInt(24) + 1;
						
						 for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
							 
							 while(randNumber == entry.getValue().getAccountID()){
								 idTaken = true;
								 randNumber = rand.nextInt(24)+1;
							 }		 
						 }
					 
							for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {					
								 if(entry.getValue().getAccountNumber().trim().equals(accountNumberTextField.getText())){
									 accNumTaken=true;	
									 
								 }
							 }
						
						if(!accNumTaken){
						
						
							BankAccount account = new BankAccount(randNumber, accountNumber, surname, firstName, accountType, 0.0, 0.0);
						
							
							int key = Integer.parseInt(account.getAccountNumber());
							
							int hash = (key%TABLE_SIZE);
							
							while(table.containsKey(hash)){
								hash = hash+1;
							}
							table.put(hash, account);
						}
						else{
							JOptionPane.showMessageDialog(null, "Account Number must be unique");
						}
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Number format exception");					
					}
				}
				else JOptionPane.showMessageDialog(null, "Please make sure all fields have values, and Account Number is a unique 8 digit number");
				dispose();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setSize(400,800);
		pack();
		setVisible(true);

	}
	
	
	
}