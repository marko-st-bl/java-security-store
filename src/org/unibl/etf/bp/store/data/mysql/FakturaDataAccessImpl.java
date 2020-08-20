package org.unibl.etf.bp.store.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.FakturaDataAccess;
import org.unibl.etf.bp.store.entity.Faktura;
import org.unibl.etf.bp.store.util.Utilities;

public class FakturaDataAccessImpl implements FakturaDataAccess {

	@Override
	public List<Faktura> fakture() {
		List<Faktura> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM FAKTURA";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Faktura(rs.getInt(1), Utilities.getDataAccessFactory().getProizvodDataAccess().proizvod(rs.getInt(2)),
						Utilities.getDataAccessFactory().getDobavljacDataAccess().getDobavljac(rs.getInt(3)), rs.getDouble(4),
								rs.getInt(5), rs.getDate(6)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public List<Faktura> getFakturaBySifraProizvoda(int sifra) {
		List<Faktura> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM FAKTURA"
				+ "WHERE Sifra=?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sifra);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Faktura(rs.getInt(1), Utilities.getDataAccessFactory().getProizvodDataAccess().proizvod(rs.getInt(2)),
						Utilities.getDataAccessFactory().getDobavljacDataAccess().getDobavljac(rs.getInt(3)), rs.getDouble(4),
								rs.getInt(5), rs.getDate(6)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public boolean dodajFakturu(Faktura faktura, String kategorija) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;
		
		String query = "{CALL dodaj_novi_proizvod(?, ?, ?, ?, ?, ?, ?, ?)}";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, faktura.getDobavljac().getId());
			cs.setInt(2, faktura.getKolicna());
			cs.setDouble(3, faktura.getNabavnaCijena());
			cs.setString(4, faktura.getProizvod().getNaziv());
			cs.setString(5, faktura.getProizvod().getOpis());
			cs.setInt(6, faktura.getProizvod().getProizvodjac().getId());
			cs.setDouble(7, faktura.getProizvod().getProdajnaCijena());
			cs.setString(8, kategorija);
			
			retVal = cs.executeUpdate() == 1;
		} catch(SQLException e) {
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}

	@Override
	public boolean dodajFakturuZaPostojeciProizvod(Faktura faktura) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;
		
		String query = "{CALL dodaj_postojeci_proizvod(?, ?, ?, ?)}";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, faktura.getDobavljac().getId());
			cs.setInt(2, faktura.getKolicna());
			cs.setDouble(3, faktura.getNabavnaCijena());
			cs.setInt(4, faktura.getProizvod().getSifra());
			
			retVal = cs.executeUpdate() == 1;
		}catch(SQLException e) {
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}

}
