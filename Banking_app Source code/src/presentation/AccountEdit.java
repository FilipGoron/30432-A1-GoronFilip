package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


import logic.Account;
import logic.AccountType;
import logic.MainLogic;

public class AccountEdit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227890199039900874L;
	private JPanel content;
	private JTextField client;
	private JComboBox<String> type;
	private JTextField money;
	private JTextField date;
	private JButton save;
	private JButton delete;
	private Account acc;
	private String name;
	private int locid;
	private String types[];

	public AccountEdit(String name) {
		client = new JTextField(name);
		type = new JComboBox<String>();
		types = AccountType.getNames(AccountType.class);
		type = new JComboBox<String>(types);
		type.setSelectedIndex(0);
		money = new JTextField("Money");
		date = new JTextField();
		init();
	}

	public AccountEdit(Account newAccount, String name, int id) {
		client = new JTextField(name);
		this.name = name;
		types = AccountType.getNames(AccountType.class);
		type = new JComboBox<String>(types);
		type.setSelectedIndex(AccountType.getInderforType(newAccount.getType()));
		this.setAcc(newAccount);
		money = new JTextField(Integer.toString(newAccount.getMoney()));
		date = new JTextField(newAccount.getDate().toString());
		locid = id;
		init();
	}

	private void init() {
		date.setEditable(false);

		content = new JPanel();
		content.setVisible(true);

		save = new JButton("Save");
		delete = new JButton("Delete");

		save.addActionListener(this);
		delete.addActionListener(this);

		content.setLayout(new GridLayout(3, 2));

		content.add(client);
		content.add(type);
		content.add(money);
		content.add(date);

		content.add(save);
		content.add(delete);

		this.setVisible(true);
		this.setSize(400, 200);
		this.setLocation(400, 400);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == save) {
			try {
				Date date;
				if(this.date.getText().isEmpty()){
					LocalDate localDate = LocalDate.now();
					date = Date.valueOf(localDate);
					name = client.getText();
				}else{
					date = Date.valueOf(this.date.getText());
				}
				System.out.println("edit "+ name);
				MainLogic.editAccount(name, locid,
						new Account(name, AccountType.getTypeFromString(types[type.getSelectedIndex()]),
								Integer.parseInt(money.getText()), date));
				this.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (click.getSource() == delete) {
			MainLogic.deleteClient(client.getText());
			this.dispose();
		}
	}

	public Account getAcc() {
		return acc;
	}

	public void setAcc(Account acc) {
		this.acc = acc;
	}
}
