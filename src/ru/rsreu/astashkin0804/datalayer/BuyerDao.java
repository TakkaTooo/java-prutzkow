package ru.rsreu.astashkin0804.datalayer;

import java.util.List;

import ru.rsreu.astashkin0804.datalayer.model.Buyer;

public interface BuyerDao {
	List<Buyer> getBuyersByProduct(int productId);
}
