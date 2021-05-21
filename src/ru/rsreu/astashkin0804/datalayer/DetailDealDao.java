package ru.rsreu.astashkin0804.datalayer;

import java.util.Date;
import java.util.List;

import ru.rsreu.astashkin0804.datalayer.model.DetailDeal;

public interface DetailDealDao {
	public List<DetailDeal> getDetailDealByDate(Date lowerComissionDate, Date upperComissionDate);
}
