package com.ptit.serviceImp;


import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Author;
import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.model.OrderDetail;
import com.ptit.model.Review;
import com.ptit.repository.BookDao;
import com.ptit.repository.OrderDao;
import com.ptit.repository.OrderDetailDao;
import com.ptit.repository.ReviewDao;
import com.ptit.service.BookService;


@Service
public class BookServiceImp implements BookService{
	@Autowired
	BookDao bookdao; 
	
	@Autowired
	OrderDetailDao orderdetaildao;
	
	@Autowired
	ReviewDao reviewDao; 
	
	@Override
	public Page<Book> getAllBooks(Pageable page) {
		return bookdao.findAll(page);  
	}

	@Override
	public Book getBookById(long id) throws ResourceNotFoundException {
		Optional<Book> book = bookdao.findById(id);
		if(!book.isPresent()) {
			throw new ResourceNotFoundException("Book not found by id"); 
		}
		return book.get();
	}

	@Override
	public int getTotalQuantity() {
		return bookdao.findAll().size(); 
	}

	
	//test thử trường hợp bình thường: 
	@Override
	public List<Book> getBookByCategory(Category category) {

		
//		String sortDirection = "desc"; 
//		String sortField = "idBook"; 
//		int pageNo = 2; 
//		int pageSize = 2; 
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
//				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
//		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
//		Page<Book> page = bookdao.findByCategory(category, pageable); 
//		List<Book> list = page.getContent(); 
//		return list; 
		return bookdao.findByCategory(category);
	}
	@Override
	public Page<Book> getPageViaCategory(int pageNo, int pageSize, String sortField, String sortDirection,
			Category category) {
		
		//paging via category: 
		Specification<Book> spec = new Specification<Book>() {

			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Book> path = root.get("category");
				Predicate equal = criteriaBuilder.equal(path, category);
				
				return equal;
			}
		
		};
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		
		return bookdao.findAll(spec, pageable);
	}
	@Override
	public Page<Book> findBook(String key,int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		return bookdao.findByBookNameContainsOrDescribeBookContainsAllIgnoreCaseOrderByBookNameAsc(key,key, pageable); 
		
	}

	@Override
	public Page<Book> findPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return bookdao.findAll(pageable);
	}

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		return bookdao.findAll(pageable);
	}

	@Override
	public int save(Book book) {
		 bookdao.save(book);
		 return bookdao.getLastIdBook();
		
	}

	@Override
	public int deleteById(long idBook) {
		try {
			List<OrderDetail> listOrder= orderdetaildao.findByBook(getBookById(idBook));
			List<Review> listReview= reviewDao.findByIdIdBook(idBook);
			if(listOrder.size()>0) {return 0;}//ko thành công vì có đơn đặt
			if(listReview.size()>0) {return 1;}//ko thành công vì có đánh giá
			
			bookdao.deleteById(idBook);
			return 2;
			
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	
		
	}

	@Override
	public List<Book> getTopBook(){
		return bookdao.getTopBook();
	}

	@Override
	public List<Book> getNewBook(){
		return bookdao.getNewBook();
	}
	
	@Override
	public List<Book> getBookByAuthor(Author author){
		return bookdao.findByAuthor(author);
	}
}



















