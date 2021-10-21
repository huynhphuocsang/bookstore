package com.ptit.admin.controller.api;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.model.Book;
import com.ptit.model.Order;
import com.ptit.model.OrderDetail;
import com.ptit.repository.OrderDetailDao;
import com.ptit.service.OrderDetailService;
import com.ptit.service.OrderService;
@RestController
@RequestMapping("api/admin")
public class OrderControllerAdminApi {
	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	private OrderDetailService orderDetailService;
//	@RequestMapping(value = "order/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Order> find(@PathVariable("id") long id,HttpServletResponse respon){
//		try {			
//			return new ResponseEntity<Order>(orderService.getOrderById(id),HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
	
	@RequestMapping(value = "order/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public void find(@PathVariable("id") long id,HttpServletResponse respon){
		try {
			
			List<OrderDetail> list = orderDetailService.getListDetailByOrderId(id);
			List<Book> listBook = orderDetailService.getListBookOfOrderDetail(id);
			
			respon.setContentType("text/html;charset=UTF-8");
			PrintWriter out = respon.getWriter();
			for (int i = 0; i < list.size(); i++) {
				out.println("<li class=\"col-md-4\">\r\n"
						+ "									<figure class=\"itemside mb-3\">\r\n"
						+ "										<div class=\"aside\">\r\n"
						+ "											<img src=\"/image/"+listBook.get(i).getPicture()+"\"\r\n"
						+ "												class=\"img-sm border\">\r\n"
						+ "										</div>\r\n"
						+ "										<figcaption class=\"info align-self-center\">\r\n"
						+ "											<p class=\"title\" id=\"book-name\">"+listBook.get(i).getBookName()+" <br> \r\n"
						+ "												<span id=\"book-author\">"+listBook.get(i).getAuthor().getName()+"</span>\r\n"
						+ "											</p>\r\n"
						+ "											<span class=\"text-muted\" id=\"book-price\">"+list.get(i).getPrice()+"</span>\r\n"
						+ "											x <span class=\"text-muted\" id=\"order-quantity\">"+list.get(i).getQuantity()+"</span>\r\n"
						+ "										</figcaption>\r\n"
						+ "									</figure>\r\n"
						+ "								</li>	");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
