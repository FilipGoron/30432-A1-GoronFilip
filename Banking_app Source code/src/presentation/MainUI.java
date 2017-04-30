package presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logic.MainLogic;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1169178160978855339L;

	private Login LoginProcedure;
	@SuppressWarnings("unused")
	private JFrame window;

	public MainUI() {
		LoginProcedure = new Login();
		int loginType = LoginProcedure.LoginProcess();
		System.out.println(loginType);
		if (loginType < 0) {
			errorMsg("Login Failed");
			System.exit(0);
		} else if (loginType == 0) { // Admin
			window = new AdminUI();
		} else if (loginType == 1) { // Normal
			window = new EmployeeUI();
		}
	}

	public static void errorMsg(String msg) {
		JOptionPane.showMessageDialog(MainLogic.UI, msg, "Login Error", JOptionPane.ERROR_MESSAGE);
	}

}
