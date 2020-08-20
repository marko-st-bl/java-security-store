package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Zaposleni;

public interface ZaposleniDataAccess {
	
	public boolean dodajZaposlenog(Zaposleni zaposleni, int idPoslovnica);
	public boolean azurirajZaposlenog(Zaposleni zaposleni);
	public boolean obrisiZaposlenog(String JMB);
	public Zaposleni prijavi(String korisnickoIme, String lozinka);
	public List<Zaposleni> zaposleniBezNaloga();
	public boolean dodijeliNalogZaposlenom(Zaposleni zaposleni);
	public boolean promjeniPlatu(Zaposleni zaposleni, double bruto);

}
