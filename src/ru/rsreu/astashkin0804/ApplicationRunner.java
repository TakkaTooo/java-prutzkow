package ru.rsreu.astashkin0804;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0804.datalayer.DaoFactory;
import ru.rsreu.astashkin0804.datalayer.DbType;
import ru.rsreu.astashkin0804.datalayer.DbTypeException;
import ru.rsreu.astashkin0804.datalayer.configuration.DbConfiguration;
import ru.rsreu.astashkin0804.datalayer.configuration.OracleDbConfiguration;
import ru.rsreu.astashkin0804.datalayer.model.Buyer;
import ru.rsreu.astashkin0804.datalayer.model.DetailDeal;
import ru.rsreu.astashkin0804.datalayer.model.MonthlyRevenueSheet;

public class ApplicationRunner {
	private static DateStringConverter dateStringConverter = new DateStringConverter(
			Resourcer.getString("demo.datePattern"));
	private static int productIdForDemoQuery = 1;

	private ApplicationRunner() {

	}

	public static void main(String[] args) throws ParseException {
		StringBuilder output = new StringBuilder();
		DbConfiguration dbConfiguration = new OracleDbConfiguration(Resourcer.getString("jdbc.driver.url"),
				Resourcer.getString("jdbc.driver.user"), Resourcer.getString("jdbc.driver.password"));
		try (DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE, dbConfiguration);) {
			List<Buyer> foundBuyers = factory.getByerDao().getBuyersByProduct(productIdForDemoQuery);
			output.append(Resourcer.getString("jdbc.connection.success")).append("\n");
			List<DetailDeal> foundDetailDeals = factory.getDetailDealDao().getDetailDealByDate(
					dateStringConverter.convertStringToDate(Resourcer.getString("demo.query.lowerComissionDate")),
					dateStringConverter.convertStringToDate(Resourcer.getString("demo.query.upperComissionDate")));
			List<MonthlyRevenueSheet> calculatedMonthlyRevenueSheet = factory.getCostByMonthSheetDao()
					.getCostByMonthSheets();
			output.append(Resourcer.getString("demo.message.buyers"))
					.append(new ReflectionTableGenerator<Buyer>(foundBuyers).generateTable())
					.append(Resourcer.getString("demo.message.deals"))
					.append(new ReflectionTableGenerator<DetailDeal>(foundDetailDeals).generateTable())
					.append(Resourcer.getString("demo.message.mothlyrevenue"))
					.append(new ReflectionTableGenerator<MonthlyRevenueSheet>(calculatedMonthlyRevenueSheet)
							.generateTable());
		} catch (SQLException | DbTypeException | NullPointerException e) {
			output.append(Resourcer.getString("demo.message.connection.error"));
		}
		System.out.println(output);
	}
}
