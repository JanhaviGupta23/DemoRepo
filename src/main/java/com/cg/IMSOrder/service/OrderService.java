package com.cg.IMSOrder.service;

import com.cg.IMSOrder.exception.IdNotFoundException;
import com.cg.IMSOrder.exception.IngredientsNotAvailable;
import com.cg.IMSOrder.pojos.Orders;

public interface OrderService {
	
	public Orders addOrder(Orders orders) throws IngredientsNotAvailable, IdNotFoundException;
	public Orders updateOrder(Orders orders) throws IdNotFoundException;
	public Orders getOrder(int orderId) throws IdNotFoundException;
	public Orders deleteOrder(int orderId) throws IdNotFoundException;

}
