package com.ptit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;
import com.ptit.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{

	@Query("SELECT SUM(totalPrice)\n"
			+ "FROM Order\r\n"
			+ "WHERE year(orderDay) = ?1\r\n"
			+ "AND orderStatus = 1\r\n"
			+ "group by month(orderDay)")
	public List<Float> getPrice(int year);
	public List<Order> findByNameOfCustomerContainsOrPhoneOfCustomerContainsAllIgnoreCaseOrderByNameOfCustomerAsc(String name,String phone); 
	public Page<Order> findByOrderStatus(int status, Pageable pageable);
}
