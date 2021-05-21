package ru.rsreu.astashkin0804;

import java.text.ParseException;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0804.datalayer.DaoFactory;
import ru.rsreu.astashkin0804.datalayer.DbType;
import ru.rsreu.astashkin0804.datalayer.DbTypeException;

public class ApplicationRunner {

	public static void main(String[] args) throws ParseException {
		StringBuilder sb = new StringBuilder();
		DbConfiguration dbConfiguration = new DbConfiguration(Resourcer.getString("jdbc.driver.url"),
				Resourcer.getString("jdbc.driver.user"), Resourcer.getString("jdbc.driver.password"));
		try {
			DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE, dbConfiguration);
			sb.append(Resourcer.getString("jdbc.connection.fault"));
			System.out.println(factory.getByerDao().getBuyersByProduct(1));
			System.out.println(factory.getDetailDealDao().getDetailDealByDate(
					DateStringConverter.convertStringToDate(Resourcer.getString("demo.query.lowerComissionDate")),
					DateStringConverter.convertStringToDate(Resourcer.getString("demo.query.upperComissionDate"))));
			System.out.println(factory.getCostByMonthSheetDao().getCostByMonthSheets());
		} catch (DbTypeException e) {
			e.printStackTrace();
		}
	}
}
	