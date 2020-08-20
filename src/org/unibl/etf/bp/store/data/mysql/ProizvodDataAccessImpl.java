package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.ProizvodDataAccess;
import org.unibl.etf.bp.store.entity.Proizvod;
import org.unibl.etf.bp.store.util.Utilities;

public class ProizvodDataAccessImpl implements ProizvodDataAccess {

	@Override
	public Proizvod proizvod(int sifra) {
		Proizvod retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT p.Sifra, Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac "
				+ "FROM proizvod p " 
				+ "WHERE Sifra=?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sifra);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Proizvod(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
						Utilities.getDataAccessFactory().getProizvodjacDataAccess().getProizvodjacById(rs.getInt(6)));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}

		return retVal;
	}

	@Override
	public List<Proizvod> proizvodi() {
		List<Proizvod> retVal = new ArrayList<Proizvod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM proizvod";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				retVal.add(new Proizvod(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
						Utilities.getDataAccessFactory().getProizvodjacDataAccess().getProizvodjacById(rs.getInt(6))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

	@Override
	public boolean dodajProizvod(Proizvod proizvod) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO proizvod VALUES " + "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvod.getNaziv());
			ps.setString(2, proizvod.getOpis());
			ps.setInt(3, proizvod.getKolicinaNaStanju());
			ps.setDouble(4, proizvod.getProdajnaCijena());
			ps.setInt(5, proizvod.getProizvodjac().getId());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public boolean azurirajProivod(Proizvod proizvod) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE proizvod SET " + "Sifra=?, " + "Naziv=?, " + "Opis=?," + "KolicinaNaStanju=?, "
				+ "ProdajnaCijena=?, " + "IdProizvodjac=? " + "WHERE Sifra=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, proizvod.getSifra());
			ps.setString(2, proizvod.getNaziv());
			ps.setString(3, proizvod.getOpis());
			ps.setInt(4, proizvod.getKolicinaNaStanju());
			ps.setDouble(5, proizvod.getProdajnaCijena());
			ps.setInt(6, proizvod.getProizvodjac().getId());
			ps.setInt(7, proizvod.getSifra());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public boolean obrisiProizvod(int sifra) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM proizvod " + "WHERE Sifra=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sifra);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public List<String> kategorije() {
		List<String> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT DISTINCT Naziv FROM kategorija_proizvoda";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(rs.getString(1));
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

}
