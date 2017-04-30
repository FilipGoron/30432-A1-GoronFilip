package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Client;
import logic.MainLogic;

public class ClientEdit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227890199039900874L;
	private JPanel content;
	private JTextField name;
	private JTextField icn;
	private JTextField cnp;
	private JTextField address;
	private JButton save;
	private JButton delete;
	private Client client;

	public ClientEdit() {
		name = new JTextField("Name");
		icn = new JTextField("Card number");
		cnp = new JTextField("CNP");
		address = new JTextField("Adress");
		init();
	}

	public ClientEdit(Client newClient) {
		setClient(newClient);
		name = new JTextField(newClient.getName());
		icn = new JTextField(Long.toString(newClient.getIcn()));
		cnp = new JTextField(Long.toString(newClient.getCnp()));
		address = new JTextField(newClient.getAddress());
		init();
	}

	private void init() {
		content = new JPanel();
		content.setVisible(true);

		save = new JButton("Save");
		delete = new JButton("Delete");

		save.addActionListener(this);
		delete.addActionListener(this);

		content.setLayout(new GridLayout(3, 2));

		content.add(name);
		content.add(icn);
		content.add(cnp);
		content.add(address);

		content.add(save);
		content.add(delete);

		this.setVisible(true);
		this.setSize(350, 250);
		this.setLocation(400, 400);
		// this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == save) {
			try {

				String cnp = this.cnp.getText();

				if (cnp.length() != 13) {
					MainUI.errorMsg("Invalid CNP" + cnp);
					return;
				} else if (cnp.charAt(3) > '1') {
					MainUI.errorMsg("Invalid CNP");
					return;
				} else if (cnp.charAt(5) > '3') {
					MainUI.errorMsg("Invalid CNP");
					return;
				} else if (cnp.charAt(7) > '5') {
					MainUI.errorMsg("Invalid CNP");
					return;
				} else {
					float sum = 0;
					for (int i = 0; i < 12; i++) {
						sum += (float) cnp.indexOf(i);
					}
					sum %= 11;

					if (sum == 10)
						sum = 1;
					System.out.println(sum + " " + cnp.indexOf(12) + " " + (int) cnp.indexOf(12));
					if (sum != cnp.indexOf(12)) {
						MainUI.errorMsg("Invalid CNP5");
						return;
					}

				}

				if (icn.getText().length() != 6) {
					MainUI.errorMsg("Invalid Card nr");
					return;
				}

				MainLogic.editClient(new Client(name.getText(), Long.parseLong(icn.getText()), Long.parseLong(cnp),
						address.getText()));
				this.dispose();
			} catch (Exception e) {
				MainUI.errorMsg("Invalid Data");
			}
		} else if (click.getSource() == delete) {
			MainLogic.deleteClient(name.getText());
			this.dispose();
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
