package com.ptit.repository;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;
import com.ptit.model.Order;
import com.ptit.model.User;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{

	@Query("SELECT SUM(totalPrice)\n"
			+ "FROM Order\r\n"
			+ "WHERE year(orderDay) = ?1\r\n"
			+ "AND orderStatus = 2\r\n"
			+ "group by month(orderDay)"
			+ "ORDER BY orderDay")
	public List<Float> getPrice(int year);
	
	@Query(value ="select month(order_day)\r\n"
			+ "from bookstore.orders o\r\n"
			+ "where year(o.order_day) = ?1\r\n"
			+ "and order_status = 2\r\n"
			+ "group by month(order_day)\r\n"
			+ "ORDER BY order_day", nativeQuery = true)
	public List<Integer> getMonthByYearOrderDate(int year);
	public List<Order> findByNameOfCustomerContainsOrPhoneOfCustomerContainsAllIgnoreCaseOrderByNameOfCustomerAsc(String name,String phone); 
	public Page<Order> findByOrderStatus(int status, Pageable pageable);
	
	
	@Query(value = "select o FROM Order o WHERE o.orderDay  BETWEEN  :startDate AND :endDate")
	public Page<Order> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query(value = "select o FROM Order o WHERE orderStatus = :orderStatus and  o.orderDay  BETWEEN  :startDate AND :endDate")
	public Page<Order> getAllBetweenDatesAndStatus(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("orderStatus") int orderStatus, Pageable pageable);
	 
//	get stack overflow when: infinitive recursive
	public List<Order> findByUserOrderByOrderIdDesc (User user); 
	
//	@Query(value = "SELECT * FROM Orders WHERE user_id = :userId", nativeQuery = true)
//	List<Order> getAllOrdersByUserid(@Param("userId") long userId);
	
	
	@Query(value ="select distinct year(order_day)\r\n"
			+ "from bookstore.orders o\r\n"
			+ "where order_status = 2\r\n"
			+ "group by year(order_day)\r\n"
			+ "ORDER BY order_day desc", nativeQuery = true)
	public List<Integer> getListYear();
	
	public List<Order> findByUser(User user);
}




































