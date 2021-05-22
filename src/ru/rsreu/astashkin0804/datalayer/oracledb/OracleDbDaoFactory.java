package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import ru.rsreu.astashkin0804.datalayer.BuyerDao;
import ru.rsreu.astashkin0804.datalayer.MonthlyRevenueSheetDao;
import ru.rsreu.astashkin0804.datalayer.DaoFactory;
import ru.rsreu.astashkin0804.datalayer.DetailDealDao;
import ru.rsreu.astashkin0804.datalayer.configuration.DbConfiguration;
import ru.rsreu.astashkin0804.datalayer.configuration.OracleDbConfiguration;

public class OracleDbDaoFactory extends DaoFactory {
	private static volatile OracleDbDaoFactory instance;

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
		OracleDbConfiguration oracleDbConfiguration = (OracleDbConfiguration) dbConfiguration;
		this.connection = DriverManager.getConnection(oracleDbConfiguration.getUrl(), oracleDbConfiguration.getUser(),
				oracleDbConfiguration.getPassword());

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
	public MonthlyRevenueSheetDao getCostByMonthSheetDao() {
		return new OracleDbMonthlyRevenueSheetDao(this.connection);
	}
}