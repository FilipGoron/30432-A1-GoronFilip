package logic;

public class Client {
	private int id;
	private String name;
	private long icn;
	private long cnp;
	private String address;

	public Client(int id, String name, long icn, long cnp, String address) {
		super();
		this.id = id;
		this.name = name;
		this.icn = icn;
		this.cnp = cnp;
		this.address = address;
	}

	public Client(String name, long icn, long cnp, String address) {
		super();
		this.name = name;
		this.icn = icn;
		this.cnp = cnp;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", icn=" + icn + ", cnp=" + cnp + ", address=" + address + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIcn() {
		return icn;
	}

	public void setIcn(long icn) {
		this.icn = icn;
	}

	public long getCnp() {
		return cnp;
	}

	public void setCnp(long cnp) {
		this.cnp = cnp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
