package ru.rsreu.astashkin0804.datalayer;

import java.util.List;

import ru.rsreu.astashkin0804.datalayer.model.MonthlyRevenueSheet;

public interface MonthlyRevenueSheetDao {
	List<MonthlyRevenueSheet> getCostByMonthSheets();
}
