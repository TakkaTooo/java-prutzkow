package ru.rsreu.astashkin0804.datalayer;

import java.sql.SQLException;

import ru.rsreu.astashkin0804.DbConfiguration;
import ru.rsreu.astashkin0804.datalayer.oracledb.OracleDbDaoFactory;

public enum DbType {
	ORACLE {
		@Override
		public DaoFactory getDaoFactory(DbConfiguration dbConfiguration) {
			DaoFactory oracleDbDaoFactory = null;
			try {
				oracleDbDaoFactory = OracleDbDaoFactory.getInstance(dbConfiguration);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oracleDbDaoFactory;
		}
	};

	public static DbType getTypeByName(String dbType) {
		try {
			return DbType.valueOf(dbType.toUpperCase());
		} catch (Exception e) {
			throw new DbTypeException();
		}
	}

	public abstract DaoFactory getDaoFactory(DbConfiguration dbConfiguration);

}
