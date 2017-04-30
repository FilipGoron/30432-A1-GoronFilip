package logic;

import java.sql.Date;

public class Account {
	private int id;
	private int cid;
	private AccountType type;
	private int money;
	private Date date;

	public Account(int id, int cid, AccountType type, int money, Date date) {
		super();
		this.id = id;
		this.cid = cid;
		this.type = type;
		this.money = money;
		this.date = date;
	}

	public Account(String name, AccountType type, int money, Date date) {
		this.cid = MainLogic.getClientID(name);
		this.type = type;
		this.money = money;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClientName() {
		return MainLogic.getClientName(this.getCid());
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", cid=" + cid + ", type=" + type + ", money=" + money + ", date=" + date + "]";
	}

	public void subMoney(int sum) {
		money -= sum;
	}

	public void addMoney(int sum) {
		money += sum;
	}

}
