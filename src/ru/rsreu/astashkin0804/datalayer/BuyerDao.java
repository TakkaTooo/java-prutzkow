package ru.rsreu.astashkin0804.datalayer;

import java.util.List;

import ru.rsreu.astashkin0804.datalayer.model.Buyer;

public interface BuyerDao {
	public List<Buyer> getBuyersByProduct(int productId);
}
