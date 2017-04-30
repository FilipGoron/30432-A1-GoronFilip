package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


import logic.Employee;
import logic.MainLogic;

public class EmployeeEdit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227890199039900874L;
	private JTextField name;
	private JTextField password;
	private JPanel content;
	private JButton save;
	private JButton delete;
	private Employee emp;
	
	public EmployeeEdit() {
		name = new JTextField("Name");
		password = new JTextField("Password");
		init();
	}

	public EmployeeEdit(Employee newEmployee) {
		name = new JTextField(newEmployee.getUsername());
		password = new JTextField(newEmployee.getPassword());
		setEmp(newEmployee);
		init();
	}

	private void init() {
		content = new JPanel();
		content.setVisible(true);

		save = new JButton("Save");
		delete = new JButton("Delete");

		save.addActionListener(this);
		delete.addActionListener(this);

		content.setLayout(new GridLayout(2, 2));

		content.add(name);
		content.add(password);

		content.add(save);
		content.add(delete);

		this.setVisible(true);
		this.setSize(350, 100);
		this.setLocation(400, 400);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == save) {
			try{
				String name = this.name.getText();
				String password = this.password.getText();
				
				if(!name.isEmpty() && !password.isEmpty()){
					MainLogic.editEmployee(new Employee(name, password));
					this.dispose();
				}else{
					MainUI.errorMsg("Cant leave Empty fields");
				}
			}catch(Exception e){
				MainUI.errorMsg("Cant leave Empty fields");
			}
		} else if (click.getSource() == delete) {
			MainLogic.deleteEmployee(name.getText());
			this.dispose();
		}
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
