package ru.rsreu.astashkin0804.datalayer;

import java.util.List;

import ru.rsreu.astashkin0804.datalayer.model.CostByMonthSheet;

public interface CostByMonthSheetDao {
	public List<CostByMonthSheet> getCostByMonthSheets();
}
