package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.MainLogic;

public class AdminUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5377370534326858141L;
	private JPanel content;
	private JButton edit;
	private JButton add;
	private JButton report;

	public AdminUI() {
		content = new JPanel();
		content.setVisible(true);

		edit = new JButton("Edit Employee");
		add = new JButton("Add new Employee");
		report = new JButton("Generate reports");

		report.addActionListener(this);
		edit.addActionListener(this);
		add.addActionListener(this);

		content.setLayout(new GridLayout(3, 1));
		content.add(edit);
		content.add(add);
		content.add(report);

		this.setName("Admin");
		this.setVisible(true);
		this.setSize(250, 250);
		this.setLocation(400, 400);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);
	}

	private void edit() {
		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Username:", "Edit Employee",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		try {
			if (!name.isEmpty())
				MainLogic.openEmployee(name);
			else
				MainUI.errorMsg("Username can't be empty");
		} catch (Exception e) {
		}
	}

	private void add() {
		new EmployeeEdit();
	}
	
	private void generateReports(){
		
		String name = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Name:", "Reports",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
	
		
		String startDate = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Start Date:", "Reports",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
	
		
		String endDate = (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter End Date:", "Reports",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
	
		try{
			MainLogic.generateReports(name, startDate, endDate);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == edit) {
			edit();
		} else if (click.getSource() == add) {
			add();
		} else if (click.getSource() == report) {
			generateReports();
		}
	}
}
