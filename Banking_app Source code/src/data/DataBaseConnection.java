package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import logic.Account;
import logic.AccountType;
import logic.Client;
import logic.Employee;
import logic.MainLogic;

public class DataBaseConnection {
	public Connection con;
	public Statement stmt;

	@SuppressWarnings("static-access")
	public DataBaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hue");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3301/bank_db", "gohonel", "Ferrari262332!");
			stmt = con.createStatement();
		} catch (Exception e) {
			MainLogic.UI.errorMsg("Could not connect to DataBase");
			System.exit(0);
		}
	}

	public void createEmployee(Employee newEmployee) {
		try {
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO desk_employee (name, password) values (?, ?)");
			statement.setString(1, newEmployee.getUsername());
			statement.setString(2, newEmployee.getPassword());
			statement.executeUpdate();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

	public Employee readEmployee(String username) {
		try {
			ResultSet rs = stmt.executeQuery("select * from desk_employee where name='" + username + "'");
			rs.next();
			return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}
	}

	public void updateEmployee(Employee newEmployee) {
		try {
			if (readEmployee(newEmployee.getUsername()) == null) {
				createEmployee(newEmployee);
				return;
			}

			newEmployee.setId(readEmployee(newEmployee.getUsername()).getId());

			PreparedStatement statement = con
					.prepareStatement("UPDATE desk_employee SET name=?, password=? WHERE id=?");
			statement.setString(1, newEmployee.getUsername());
			statement.setString(2, newEmployee.getPassword());
			statement.setInt(3, newEmployee.getId());
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(String username) {
		try {
			PreparedStatement statement = con.prepareStatement("DELETE FROM desk_employee WHERE name=?");
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int readEmploye(String name) {
		try {
			ResultSet rs = stmt.executeQuery("select ID from desk_employee where name='" + name + "'");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void reports(String username, String start, String end) {
		try {
			int id = readEmploye(username);
			if (id == 0){
				return;
			}
			ResultSet rs = stmt.executeQuery("select * from employee_activity where (date between '" + start + "' and '"
					+ end + "') and employeeID=" + id);

			ArrayList<String> reports = new ArrayList<String>();

			while (rs.next()) {
				reports.add("ID:" + rs.getInt(1) + ", date:" + rs.getString(2) + " , activity: " + rs.getString(3));
			}

			MainLogic.displayReports(reports);

		} catch (Exception e) {

		}
	}

	public void writeToReport(String report) {
		PreparedStatement statement;
		try {
			statement = con
					.prepareStatement("INSERT INTO employee_activity (employeeID, date, activity) values (?, ?, ?)");
			statement.setInt(1, MainLogic.getUserID());
			LocalDate localDate = LocalDate.now();
			statement.setDate(2, Date.valueOf(localDate));
			statement.setString(3, report);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean testUsername(String username, int loginType) {
		try {
			String type = null;
			if (loginType == 0) {
				type = "administrator";
			} else if (loginType == 1) {
				type = "desk_employee";
			}
			ResultSet rs = stmt.executeQuery("select ID from " + type + " where name='" + username + "'");
			rs.next();
			if (rs.getInt(1) >= 0)
				return true;
		} catch (SQLException e) {

		}
		return false;
	}

	public boolean testPassword(String password, String username, int loginType) {
		try {
			String type = null;
			if (loginType == 0) {
				type = "administrator";
			} else if (loginType == 1) {
				type = "desk_employee";
			}
			ResultSet rs = stmt.executeQuery("select password, ID from " + type + " where name='" + username + "'");
			rs.next();
			if (rs.getString(1).equals(password)) {
				MainLogic.setUserID(rs.getInt(2));
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}

	public Client readClient(String name) {
		try {
			ResultSet rs = stmt.executeQuery("select * from clients where name='" + name + "'");
			rs.next();
			writeToReport("Read Information about client " + rs.getString(2));
			return new Client(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getString(5));
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteClient(String name) {
		try {
			int id = readClient(name).getId();
			
			PreparedStatement statement = con.prepareStatement("DELETE FROM clients WHERE name=?");
			writeToReport("Deleted client " + name);
			statement.setString(1, name);
			statement.executeUpdate();
			
			statement = con.prepareStatement("DELETE FROM accounts WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateClient(Client client) {
		try {
			if (readClient(client.getName()) == null) {
				createClient(client);
				return;
			}

			client.setId(readClient(client.getName()).getId());

			PreparedStatement statement = con
					.prepareStatement("UPDATE clients SET name=?, id_card_nr=?, numerical_code=?, adress=? WHERE id=?");
			statement.setString(1, client.getName());
			statement.setLong(2, client.getIcn());
			statement.setLong(3, client.getCnp());
			statement.setString(4, client.getAddress());
			statement.setInt(5, client.getId());
			statement.executeUpdate();
			writeToReport("Updated client " + client.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createClient(Client client) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO clients (name, id_card_nr, numerical_code, adress) values (?, ?, ?, ?)");
			statement.setString(1, client.getName());
			statement.setLong(2, client.getIcn());
			statement.setLong(3, client.getCnp());
			statement.setString(4, client.getAddress());
			statement.executeUpdate();
			writeToReport("Created client " + client.toString());
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

	public String readClientName(int cid) {
		try {
			ResultSet rs = stmt.executeQuery("select name from clients where ID=" + cid);
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getClientAccounts(String name) {
		ArrayList<String> result = new ArrayList<String>();

		try {
			ResultSet rs = stmt.executeQuery(
					"select ID, type from accounts where clientID=(select ID from clients where name='" + name + "')");
			while (rs.next()) {
				result.add(Integer.toString(rs.getInt(1)));
			}
		} catch (Exception e) {
			return null;
		}

		return result;
	}

	public Account readAccount(int iD) {
		try {
			ResultSet rs = stmt.executeQuery("select * from accounts where ID=" + iD);
			rs.next();
			return new Account(rs.getInt(1), rs.getInt(2), AccountType.getTypeFromString(rs.getString(3)), rs.getInt(4),
					rs.getDate(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateAccount(String name, int id, Account account) {
		try {
			if (id > 0) {

				PreparedStatement statement = con
						.prepareStatement("UPDATE accounts SET clientID=?, type=?, money=?, date=? WHERE ID=?");
				statement.setInt(1, account.getCid());
				statement.setString(2, AccountType.getStringFromType(account.getType()));
				statement.setInt(3, account.getMoney());
				statement.setDate(4, account.getDate());
				statement.setInt(5, id);
				statement.executeUpdate();
				writeToReport("Updated account of " + name + " to " + account.toString());
			} else {
				
				createAccount(name, account);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createAccount(String name, Account account) {
		try {
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO accounts SET clientID=?, type=?, money=?, date=?");

			LocalDate localDate = LocalDate.now();
			statement.setInt(1, account.getCid());
			statement.setString(2, AccountType.getStringFromType(account.getType()));
			statement.setInt(3, account.getMoney());
			statement.setDate(4, Date.valueOf(localDate));
			statement.executeUpdate();
			writeToReport("Created account of " + name + " to " + account.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
