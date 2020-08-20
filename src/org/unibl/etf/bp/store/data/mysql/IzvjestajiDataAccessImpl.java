package org.unibl.etf.bp.store.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.unibl.etf.bp.store.data.IzvjestajiDataAccess;


public class IzvjestajiDataAccessImpl implements IzvjestajiDataAccess {

	@Override
	public Vector<Vector<Object>> ukupanPromet() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM ukupan_promet";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getString(2));
				red.add(rs.getString(3));
				red.add(rs.getDouble(4));
				retVal.add(red);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs, rs);
		}
		return retVal;
	}

	@Override
	public Vector<Vector<Object>> plate() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM plata_zaposlenog";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getString(2));
				red.add(rs.getDouble(3));
				red.add(rs.getDouble(4));
				red.add(rs.getDouble(5));
				red.add(rs.getDouble(6));
				red.add(rs.getDouble(7));
				red.add(rs.getDouble(8));
				retVal.add(red);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs, rs);
		}
		return retVal;
	}

	@Override
	public Vector<Vector<Object>> ukupanProdaja() {
			Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
			Connection conn = null;
			CallableStatement cs = null;
			ResultSet rs = null;

			String query = "SELECT * FROM ukupna_prodaja";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				cs = conn.prepareCall(query);

				rs = cs.executeQuery();
				while (rs.next()) {
					Vector<Object> red = new Vector<Object>();
					red.add(rs.getString(1));
					red.add(rs.getString(2));
					red.add(rs.getString(3));
					red.add(rs.getDouble(4));
					retVal.add(red);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				MySQLUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				MySQLUtilities.getInstance().close(cs, rs);
			}
			return retVal;
	}

	@Override
	public Vector<Vector<Object>> ukupanoIznajmljivanje() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM ukupno_iznajmljivanje";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getString(2));
				red.add(rs.getString(3));
				red.add(rs.getDouble(4));
				retVal.add(red);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs, rs);
		}
		return retVal;
	}

}
