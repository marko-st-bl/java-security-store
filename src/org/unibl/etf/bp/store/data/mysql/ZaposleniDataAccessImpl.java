package org.unibl.etf.bp.store.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.ZaposleniDataAccess;
import org.unibl.etf.bp.store.entity.Pozicija;
import org.unibl.etf.bp.store.entity.Zaposleni;

public class ZaposleniDataAccessImpl implements ZaposleniDataAccess {

	@Override
	public boolean dodajZaposlenog(Zaposleni zaposleni, int idPoslovnica) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL dodaj_zaposlenog(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setString(1, zaposleni.getJMB());
			cs.setString(2, zaposleni.getIme());
			cs.setString(3, zaposleni.getPrezime());
			cs.setString(4, zaposleni.getAdresa());
			cs.setString(5, zaposleni.getTelefon());
			cs.setDate(6, new java.sql.Date(zaposleni.getDatumRodjenja().getTime()));
			cs.setDate(7, new java.sql.Date(zaposleni.getDatumPrijema().getTime()));
			cs.setString(8, zaposleni.getPozicija().toString());
			cs.setInt(9, idPoslovnica);

			retVal = cs.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}

	@Override
	public boolean azurirajZaposlenog(Zaposleni zaposleni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiZaposlenog(String JMB) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM zaposleni WHERE JMB=?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, JMB);
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
	public Zaposleni prijavi(String korisnickoIme, String lozinka) {
		Zaposleni retVal = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "{CALL prijava(?, ?, ?, ?, ?, ?)}";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setString(1, korisnickoIme);
			cs.setString(2, lozinka);
			cs.registerOutParameter(3, Types.BOOLEAN);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);

			cs.execute();
			if (cs.getBoolean(3)) {
				retVal = new Zaposleni(cs.getString(4), cs.getString(5), cs.getString(6));
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
	public List<Zaposleni> zaposleniBezNaloga() {
		List<Zaposleni> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM zaposleni_bez_naloga_info";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.add(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3),
						Pozicija.valueOf(rs.getString(4))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
			MySQLUtilities.getInstance().close(rs);
		}
		return retVal;
	}

	@Override
	public boolean dodijeliNalogZaposlenom(Zaposleni zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL dodijeli_nalog(?)}";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setString(1, zaposleni.getJMB());
			retVal = cs.executeUpdate() == 1;
		} catch (SQLException e) {
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}

	@Override
	public boolean promjeniPlatu(Zaposleni zaposleni, double bruto) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL promjena_plate(?, ?)}";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setString(1, zaposleni.getJMB());
			cs.setDouble(2, bruto);
			retVal = cs.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}

}
