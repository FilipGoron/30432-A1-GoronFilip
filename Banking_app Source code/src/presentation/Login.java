package presentation;

import javax.swing.JOptionPane;

import logic.MainLogic;

public class Login {

	public int LoginProcess() {
		int loginType = loginType();
		try {
			String username = getUsername();
			boolean validUsername = false;
			
			while (validUsername == false) {
				if (username.isEmpty()) {
					return -1;
				} else if (MainLogic.askForUsername(username, loginType)) {
					validUsername = true;
					break;
				} else {
					MainUI.errorMsg("Invalid Username");
					username = getUsername();
				}
			}

			String password = getPassword();
			boolean validPassword = false;

			while (validPassword == false) {
				if (password == null) {
					return -2;
				} else if (MainLogic.askForPassword(password, username, loginType)) {
					validPassword = true;
					break;
				} else {
					MainUI.errorMsg("Invalid Password");
					password = getPassword();
				}
			}
			return loginType;
		} catch (Exception e) {
		}
		return -3;
	}

	private int loginType() {
		Object[] options = { "Admin", "Normal" };
		return JOptionPane.showOptionDialog(MainLogic.UI, "Login as:", "Login", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, null);

	}

	private String getUsername() {
		return (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Username:", "Login", JOptionPane.PLAIN_MESSAGE,
				null, null, null);
	}

	private String getPassword() {
		return (String) JOptionPane.showInputDialog(MainLogic.UI, "Enter Password:", "Login", JOptionPane.PLAIN_MESSAGE,
				null, null, null);
	}
}
