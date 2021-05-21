package ru.rsreu.astashkin0804.datalayer;

import ru.rsreu.astashkin0804.DbConfiguration;

public abstract class DaoFactory {
	public static DaoFactory getInstance(DbType dbType, DbConfiguration dbConfiguration) {
		DaoFactory result = dbType.getDaoFactory(dbConfiguration);
		return result;
	}

	public abstract BuyerDao getByerDao();

	public abstract DetailDealDao getDetailDealDao();

	public abstract CostByMonthSheetDao getCostByMonthSheetDao();
}