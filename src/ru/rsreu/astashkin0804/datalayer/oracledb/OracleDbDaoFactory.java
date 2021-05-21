package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import ru.rsreu.astashkin0804.DbConfiguration;
import ru.rsreu.astashkin0804.datalayer.BuyerDao;
import ru.rsreu.astashkin0804.datalayer.CostByMonthSheetDao;
import ru.rsreu.astashkin0804.datalayer.DaoFactory;
import ru.rsreu.astashkin0804.datalayer.DetailDealDao;


public class OracleDbDaoFactory extends DaoFactory {
	private static volatile OracleDbDaoFactory instance;
	private Connection connection;

	private OracleDbDaoFactory() {
	}

	public static OracleDbDaoFactory getInstance(DbConfiguration dbConfiguration)
			throws ClassNotFoundException, SQLException {
		OracleDbDaoFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDbDaoFactory.class) {
				instance = factory = new OracleDbDaoFactory();
				factory.connected(dbConfiguration);
			}
		}
		return factory;
	}

	private void connected(DbConfiguration dbConfiguration) throws ClassNotFoundException, SQLException {
		Locale.setDefault(Locale.ENGLISH);
		this.connection = DriverManager.getConnection(dbConfiguration.getUrl(), dbConfiguration.getUser(),
				dbConfiguration.getPassword());
		
	}
	
	@Override
	public BuyerDao getByerDao() {
		return new OracleDbBuyerDao(this.connection);
	}

	@Override
	public DetailDealDao getDetailDealDao() {
		return new OracleDbDetailDealDao(this.connection);
	}

	@Override
	public CostByMonthSheetDao getCostByMonthSheetDao() {
		return new OracleDbCostByMonthSheetDao(this.connection);
	}
}
