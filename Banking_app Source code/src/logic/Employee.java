package logic;

public class Employee {
	
	private int id;
	private String username;
	private String password;
	private String[] report;
	
	public Employee(int id, String username, String password, String[] report) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.report = report;
	}
	
	public Employee(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public Employee(String username, String password, String[] report) {
		super();
		this.username = username;
		this.password = password;
		this.report = report;
	}
	
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getReport() {
		return report;
	}

	public void setReport(String[] report) {
		this.report = report;
	}
	
	
	
	
	
	
	
	

}
