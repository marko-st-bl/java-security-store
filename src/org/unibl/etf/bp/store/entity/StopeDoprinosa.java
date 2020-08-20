package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class StopeDoprinosa {
	
	private int id;
	private double pio;
	private double zdr;
	private double dj;
	private double nezap;
	private Date vaziOd;
	private Date vaziDo;
	
	public StopeDoprinosa(double pio, double zdr, double dj, double nezap) {
		super();
		this.pio = pio;
		this.zdr = zdr;
		this.dj = dj;
		this.nezap = nezap;
		this.vaziOd = new Date();
	}
	
	public StopeDoprinosa() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPio() {
		return pio;
	}

	public void setPio(double pio) {
		this.pio = pio;
	}

	public double getZdr() {
		return zdr;
	}

	public void setZdr(double zdr) {
		this.zdr = zdr;
	}

	public double getDj() {
		return dj;
	}

	public void setDj(double dj) {
		this.dj = dj;
	}

	public double getNezap() {
		return nezap;
	}

	public void setNezap(double nezap) {
		this.nezap = nezap;
	}

	public Date getVaziOd() {
		return vaziOd;
	}

	public void setVaziOd(Date vaziOd) {
		this.vaziOd = vaziOd;
	}

	public Date getVaziDo() {
		return vaziDo;
	}

	public void setVaziDo(Date vaziDo) {
		this.vaziDo = vaziDo;
	}

}
