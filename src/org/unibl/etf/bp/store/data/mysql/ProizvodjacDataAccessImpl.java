package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.ProizvodjacDataAccess;
import org.unibl.etf.bp.store.entity.Proizvodjac;

public class ProizvodjacDataAccessImpl implements ProizvodjacDataAccess {

	@Override
	public List<Proizvodjac> proizvodjaci() {
		List<Proizvodjac> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT Id, Naziv FROM proizvodjac";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				retVal.add(new Proizvodjac(rs.getInt(1), rs.getString(2)));
			}
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
	public boolean dodajProivodjaca(Proizvodjac proizvodjac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO proizvodjac VALUES "
				+ "(?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvodjac.getNaziv());

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
	public boolean obrisiProizvodjaca(int id) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM proizvodjac "
				+ "WHERE IdProizvodjac=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

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
	public boolean azurirajProizvodjaca(Proizvodjac proizvodjac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE proizvodjac SET "
				+ "IdProizvodjac=?, "
				+ "Naziv=? "
				+ "WHERE IdProizvodjac=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, proizvodjac.getId());
			ps.setString(2,proizvodjac.getNaziv());
			ps.setInt(3, proizvodjac.getId());

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
	public Proizvodjac getProizvodjacById(int id) {
		Proizvodjac retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query="SELECT Id, Naziv "
				+ "FROM proizvodjac "
				+"WHERE Id=?";
		
		try {
			conn=ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Proizvodjac(rs.getInt(1), rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		}finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

}
