package ru.rsreu.astashkin0804.datalayer;

import java.sql.SQLException;

import ru.rsreu.astashkin0804.datalayer.configuration.DbConfiguration;

public abstract class DaoFactory implements AutoCloseable {
	public static DaoFactory getInstance(DbType dbType, DbConfiguration dbConfiguration) {
		DaoFactory result = dbType.getDaoFactory(dbConfiguration);
		return result;
	}

	public abstract BuyerDao getByerDao();

	public abstract DetailDealDao getDetailDealDao();

	public abstract MonthlyRevenueSheetDao getCostByMonthSheetDao();

	public abstract void close() throws SQLException;
}