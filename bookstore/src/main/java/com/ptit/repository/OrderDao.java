package com.ptit.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;
import com.ptit.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{

	@Query("SELECT SUM(totalPrice)\n"
			+ "FROM Order\r\n"
			+ "WHERE year(orderDay) = ?1\r\n"
			+ "AND orderStatus = 0\r\n"
			+ "group by month(orderDay)"
			+ "ORDER BY orderDay")
	public List<Float> getPrice(int year);
	public List<Order> findByNameOfCustomerContainsOrPhoneOfCustomerContainsAllIgnoreCaseOrderByNameOfCustomerAsc(String name,String phone); 
	public Page<Order> findByOrderStatus(int status, Pageable pageable);
	
	
	@Query(value = "SELECT * FROM Order WHERE orderDay >= :startDate AND orderDay <= :endDate", nativeQuery = true)
	List<Order> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	 
	
	
}
