package logic;

import java.util.ArrayList;

import data.DataBaseConnection;
import presentation.AccountEdit;
import presentation.ClientEdit;
import presentation.EmployeeEdit;
import presentation.MainUI;
import presentation.ReportsWindow;

public class MainLogic {
	public static MainUI UI;
	public static DataBaseConnection DB;

	private static int userID;

	public static void main(String[] args) {
		DB = new DataBaseConnection();
		UI = new MainUI();
	}

	public static boolean askForUsername(String username, int loginType) {
		return DB.testUsername(username, loginType);
	}

	public static boolean askForPassword(String password, String username, int loginType) {
		return DB.testPassword(password, username, loginType);
	}

	public static void openEmployee(String username) {
		new EmployeeEdit(DB.readEmployee(username));
	}

	public static void editEmployee(Employee employee) {
		DB.updateEmployee(employee);
	}

	public static void deleteEmployee(String name) {
		DB.deleteEmployee(name);
	}

	public static void editClient(Client client) {
		DB.updateClient(client);
	}

	public static void deleteClient(String name) {
		DB.deleteClient(name);
	}

	public static void openClient(String name) {
		@SuppressWarnings("unused")
		ClientEdit ce = new ClientEdit(DB.readClient(name));
	}

	public static String getClientName(int cid) {
		return DB.readClientName(cid);
	}

	public static int getClientID(String name) {
		return DB.readClient(name).getId();
	}

	public static ArrayList<String> getClientAccounts(String name) {
		return DB.getClientAccounts(name);
	}

	public static void openAccount(int ID, String name) {
		new AccountEdit(DB.readAccount(ID), name, ID);
	}

	public static void addAccount(String name) {
		new AccountEdit(name);
	}

	public static void editAccount(String name, int locid, Account account) {
		DB.updateAccount(name, locid, account);
	}

	public static int getUserID() {
		return userID;
	}

	public static void setUserID(int userID) {
		MainLogic.userID = userID;
	}

	public static void displayReports(ArrayList<String> reports) {
		@SuppressWarnings("unused")
		ReportsWindow wind = new ReportsWindow(reports);
	}

	public static void generateReports(String name, String startDate, String endDate) {
		try {
			DB.reports(name, startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToReport(String string) {
		DB.writeToReport(string);
	}

	public static void transfer(String from, int fromID, String to, int toID, int sum) {
		Account fromAccount = DB.readAccount(fromID);

		if (fromAccount.getMoney() < sum) {
			MainUI.errorMsg("Insufficient Funds!");
			return;
		}
		writeToReport("Transfered money from " + from + " to " + to);
		Account toAccount = DB.readAccount(toID);
		fromAccount.subMoney(sum);
		toAccount.addMoney(sum);
		DB.updateAccount(from, fromID, fromAccount);
		DB.updateAccount(to, toID, toAccount);
	}

	public static void payBill(int sum, String biller, int acc, String name) {
		Account account = DB.readAccount(acc);
		if (account.getMoney() < sum) {
			MainUI.errorMsg("Insufficient Funds!");
			return;
		}
		writeToReport("Payed to " + biller + " a sum of " + sum);
		account.subMoney(sum);
		DB.updateAccount(name, acc, account);

	}

}
