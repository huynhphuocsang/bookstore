package com.ptit.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Order;

public interface OrderService {
	public Page<Order> getAllOrders(Pageable page);
	public Order getOrderById(long id) throws ResourceNotFoundException;
	public List<Order> findOrder(String str);
	public Page<Order> findPage(int pageNo, int pageSize);
	public Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, int status);
	public int save(Order order);
}
