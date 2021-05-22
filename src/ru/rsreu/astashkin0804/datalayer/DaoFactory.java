package ru.rsreu.astashkin0804.datalayer;

import java.sql.Connection;
import java.sql.SQLException;

import ru.rsreu.astashkin0804.datalayer.configuration.DbConfiguration;

public abstract class DaoFactory {
	protected Connection connection;
	public static DaoFactory getInstance(DbType dbType, DbConfiguration dbConfiguration) {
		DaoFactory result = dbType.getDaoFactory(dbConfiguration);
		return result;
	}

	public abstract BuyerDao getByerDao();

	public abstract DetailDealDao getDetailDealDao();

	public abstract MonthlyRevenueSheetDao getCostByMonthSheetDao();
	
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
}