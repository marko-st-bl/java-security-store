package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.PoslovnicaDataAccess;
import org.unibl.etf.bp.store.entity.Mjesto;
import org.unibl.etf.bp.store.entity.Poslovnica;
import org.unibl.etf.bp.store.entity.Proizvod;

public class PoslovnicaDataAccessImpl implements PoslovnicaDataAccess {

	@Override
	public List<Proizvod> proizvodiUPoslovnici(int idPoslovnica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodajPoslovnicu(Poslovnica poslovnica) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO poslovnica VALUES "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, poslovnica.getAdresa());
			ps.setString(2, poslovnica.getTelefon());
			ps.setInt(3, poslovnica.getMjesto().getPosta());

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
	public boolean obrisiPoslovnicu(int idPoslovnica) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM poslovnica "
				+ "WHERE IdPoslovnica=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idPoslovnica);

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
	public boolean azurirajPoslovnicu(Poslovnica poslovnica) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE poslovnica SET "
				+ "IdPoslovnica=?, "
				+ "Adresa=?, "
				+ "Telefon=?,"
				+ "Posta=? "
				+ "WHERE IdPoslovnica=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, poslovnica.getIdPoslovnica());
			ps.setString(2, poslovnica.getAdresa());
			ps.setString(3, poslovnica.getTelefon());
			ps.setInt(4, poslovnica.getMjesto().getPosta());
			ps.setInt(5, poslovnica.getIdPoslovnica());

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
	public Poslovnica poslovnicaZaposlenog(String JMB) {
		Poslovnica retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query="SELECT p.IdPoslovnica, Adresa, Telefon "
				+"FROM zaposleni z inner join zaposleni_poslovnica zp on z.JMB=zp.JMBZaposleni "
				+"inner join poslovnica p on p.IdPoslovnica=zp.IdPoslovnica "
				+"WHERE z.JMB=? AND DatumDo IS NULL";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, JMB);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Poslovnica(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

	@Override
	public List<Poslovnica> poslovnice() {
		List<Poslovnica> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT IdPoslovnica, Adresa, Telefon, p.Posta, Naziv "
				+ "FROM poslovnica p inner join mjesto m on p.Posta=m.Posta";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Poslovnica(rs.getInt(1), rs.getString(2), rs.getString(3), new Mjesto(rs.getInt(4),rs.getString(5))));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

}
