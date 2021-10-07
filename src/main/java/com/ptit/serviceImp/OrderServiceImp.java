package com.ptit.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Order;
import com.ptit.repository.OrderDao;
import com.ptit.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	OrderDao orderdao;
	
	@Override
	public Page<Order> getAllOrders(Pageable page){
		return orderdao.findAll(page);
	}
	
	@Override
	public Order getOrderById(long id) throws ResourceNotFoundException{
		Optional<Order> order=orderdao.findById(id);
		if(!order.isPresent()) {
			throw new ResourceNotFoundException("Order not found by id");
		}
		return order.get();
	}
	@Override
	public List<Order> findOrder(String str){
		List<Order> list=orderdao.findByNameOfCustomerContainsOrPhoneOfCustomerContainsAllIgnoreCaseOrderByNameOfCustomerAsc(str, str);
		return list;
	}
	@Override
	public Page<Order> findPage(int pageNo, int pageSize){
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return orderdao.findAll(pageable);
	}
	@Override
	public Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, int status){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		if(status<0) {
			return orderdao.findAll(pageable);
		}
		return orderdao.findByOrderStatus(status, pageable);
	}
	@Override
	public int save(Order order) {
		orderdao.save(order);
		return 1;
	}
}
