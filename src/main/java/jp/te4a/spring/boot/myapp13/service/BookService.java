package jp.te4a.spring.boot.myapp13.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.myapp13.bean.BookBean;
import jp.te4a.spring.boot.myapp13.form.BookForm;
import jp.te4a.spring.boot.myapp13.repository.BookRepository;

@Service
public class BookService {

//    @Autowired
//    BookRepository bookRepository;
//    
//    public BookBean save(BookBean bookBean) {
//        return bookRepository.save(bookBean);
//    }
//
//    public List<BookBean> findAll() {
//        return bookRepository.findAll();
//    }

	  @Autowired
	  BookRepository bookRepository;

	  public BookForm create(BookForm bookForm) {
//		  bookForm.setId(bookRepository.getBookId());
		  BookBean bookBean = new BookBean();
		  BeanUtils.copyProperties(bookForm, bookBean);
//		  bookRepository.create(bookBean);
		  bookRepository.save(bookBean);
		  return bookForm;
	  }

	  public BookForm update(BookForm bookForm) {
		    BookBean bookBean = new BookBean();
		    BeanUtils.copyProperties(bookForm, bookBean);
//		    bookRepository.update(bookBean);
		    bookRepository.save(bookBean);
		    return bookForm;
	  }
	  
	  public void delete(Integer id) {
		  // bookRepository.delete(id);
		  BookBean bookBean = new BookBean();
//		  bookBean.setId(id);
		  bookRepository.delete(bookBean);
	  }

	  public List<BookForm> findAll() {
		  List<BookBean> beanList = bookRepository.findAll();
		  List<BookForm> formList = new ArrayList<BookForm>();
		  for(BookBean bookBean: beanList) {
			  BookForm bookForm = new BookForm();
		      BeanUtils.copyProperties(bookBean, bookForm);
		      formList.add(bookForm);
		  }
		  return formList;
	  }
	  public BookForm findOne(Integer id) {
//		  BookBean bookBean = bookRepository.findOne(id);
		  Optional<BookBean> bookBeanOpt = bookRepository.findById(id);
		  BookForm bookForm = new BookForm();
		  bookBeanOpt.ifPresent(bookBean -> {
			  BeanUtils.copyProperties(bookBeanOpt.get(), bookForm);
		  });
		  return bookForm;
	  }
}