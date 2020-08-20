package org.unibl.etf.bp.store.entity;

public class KorisnickiNalog {
	
	private int id;
	private String username;
	private String password;
	private Status status;
	
	public KorisnickiNalog(String username, String password, Status status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public KorisnickiNalog(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.status = new Status();
	}
	
	public KorisnickiNalog() {
		super();
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
