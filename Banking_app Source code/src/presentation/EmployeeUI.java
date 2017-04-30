
package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.MainLogic;

public class EmployeeUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5377370534326858141L;
	private JPanel content;
	private JButton editC;
	private JButton addC;
	private JButton editA;
	private JButton addA;
	private JButton bill;
	private JButton transfer;

	public EmployeeUI() {
		MainLogic.writeToReport("Logged in");

		content = new JPanel();
		content.setVisible(true);

		editC = new JButton("Edit Client");
		addC = new JButton("Add Client");
		editA = new JButton("Edit Account");
		addA = new JButton("Add Account");
		transfer = new JButton("Transfer Money");
		bill = new JButton("Process bill");

		content.setLayout(new GridLayout(3, 2));

		content.add(editC);
		editC.addActionListener(this);
		content.add(addC);
		addC.addActionListener(this);
		content.add(editA);
		editA.addActionListener(this);
		content.add(addA);
		addA.addActionListener(this);
		content.add(bill);
		bill.addActionListener(this);
		content.add(transfer);
		transfer.addActionListener(this);

		this.setVisible(true);
		this.setSize(350, 250);
		this.setLocation(400, 400);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);
	}

	public void editC() {
		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Client Name:", "Edit Client",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		try {
			if (!name.isEmpty())
				MainLogic.openClient(name);
			else
				MainUI.errorMsg("Invalid Username");
		} catch (Exception e) {
			MainUI.errorMsg("Invalid Username");
		}
	}

	public void addC() {
		new ClientEdit();
	}

	public void bill() {
		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Client Name:", "Pay Bill",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		try {
			if (name.isEmpty()) {
				MainUI.errorMsg("Username can't be empty");
				return;
			}
		} catch (Exception e) {
			MainUI.errorMsg("Username can't be empty");
			return;
		}

		ArrayList<String> optionList = MainLogic.getClientAccounts(name);
		int len = optionList.size();
		String[] options = new String[len];

		for (int i = 0; i < len; i++)
			options[i] = optionList.get(i);

		String s = (String) JOptionPane.showInputDialog(MainLogic.UI, "Choose account", "Pay Bill",
				JOptionPane.PLAIN_MESSAGE, null, options, "Please choose one");
		int acc = 0;
		try {
			if (!s.isEmpty())
				acc = Integer.parseInt(s);
			else {
				MainUI.errorMsg("Username can't be empty");
				return;
			}
		} catch (Exception e) {
			acc = 0;
			MainUI.errorMsg("Username can't be empty");
			return;
		}

		String[] options1 = { "Gas", "Electricity", "Water", "Garbage", "Internet" };

		String biller = (String) JOptionPane.showInputDialog(MainLogic.UI, "Choose account", "Pay Bill",
				JOptionPane.PLAIN_MESSAGE, null, options1, "Please choose one");

		String sumString = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter sum:", "Pay Bill",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		int sum;
		try {
			sum = Integer.parseInt(sumString);
		} catch (Exception e) {
			MainUI.errorMsg("Not a valid number");
			sum = 0;
			return;
		}

		MainLogic.payBill(sum, biller, acc, name);
	}

	public void editeA() {

		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Client Name:", "Edit Account",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		ArrayList<String> optionList = MainLogic.getClientAccounts(name);
		int len = optionList.size();
		String[] options = new String[len];

		for (int i = 0; i < len; i++)
			options[i] = optionList.get(i);

		String s = (String) JOptionPane.showInputDialog(MainLogic.UI, "Choose account", "Edit Account",
				JOptionPane.PLAIN_MESSAGE, null, options, "Please choose one");

		try {
			if (!s.isEmpty())
				MainLogic.openAccount(Integer.parseInt(s), name);
			else
				MainUI.errorMsg("Username can't be empty");
		} catch (Exception e) {
		}
	}

	public void addA() {
		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Client Name:", "Edit Account",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		MainLogic.addAccount(name);
	}

	public void transfer() {
		try {
			String from = (String) JOptionPane.showInputDialog(MainLogic.UI, "From Client:", "Transfer Money",
					JOptionPane.PLAIN_MESSAGE, null, null, null);

			ArrayList<String> optionList = MainLogic.getClientAccounts(from);
			int len = optionList.size();
			String[] options = new String[len];

			for (int i = 0; i < len; i++)
				options[i] = optionList.get(i);

			int fromAccount = Integer.parseInt((String) JOptionPane.showInputDialog(MainLogic.UI, "Choose account",
					"Transfer Money", JOptionPane.PLAIN_MESSAGE, null, options, "Please choose one"));

			String to = (String) JOptionPane.showInputDialog(MainLogic.UI, "To Client :", "Transfer Money",
					JOptionPane.PLAIN_MESSAGE, null, null, null);

			optionList = MainLogic.getClientAccounts(to);
			len = optionList.size();
			options = new String[len];

			for (int i = 0; i < len; i++)
				options[i] = optionList.get(i);

			int toAccount = Integer.parseInt((String) JOptionPane.showInputDialog(MainLogic.UI, "Choose account",
					"Transfer Money", JOptionPane.PLAIN_MESSAGE, null, options, "Please choose one"));

			int sum = Integer.parseInt((String) JOptionPane.showInputDialog(MainLogic.UI, "Sum:",
					"Transfer Money", JOptionPane.PLAIN_MESSAGE, null, null, null));

			MainLogic.transfer(from, fromAccount, to, toAccount, sum);
		} catch (Exception e) {

		}
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == editC) {
			editC();
		} else if (click.getSource() == addC) {
			addC();
		} else if (click.getSource() == editA) {
			editeA();
		} else if (click.getSource() == addA) {
			addA();
		} else if (click.getSource() == transfer) {
			transfer();
		} else if (click.getSource() == bill) {
			bill();
		}

	}
}
