package jp.te4a.spring.boot.myapp6;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookRepositoryTest {

	@Autowired
	BookRepository bookRepository;
	
	@Test(expected = NullPointerException.class)
	public void save_正常系_nullは追加されない() {
		BookBean bookBean = null;
		bookRepository.save(bookBean);
	}
	
	@Test
	public void save_正常系_数値以外nullは登録される() {
		BookBean bookBean = new BookBean(0, null, null, null, 0);
		BookBean actual = bookRepository.save(bookBean);
		BookBean expected = null;
		assertThat(actual, is(expected));
	}
	
	@Test
	public void save_正常系_数値以外全て空文字は登録される() {
		BookBean bookBean = new BookBean(0, "", "", "", 0);
		BookBean actual = bookRepository.save(bookBean);
		BookBean expected = null;
		assertThat(actual, is(expected));
	}
	
	@Test
	public void save_正常系_負のid値は登録される() {
		BookBean bookBean = new BookBean(-1, "a", "b", "c", 100);
		BookBean actual = bookRepository.save(bookBean);
		BookBean expected = null;
		assertThat(actual, is(expected));
	}
	
	@After
	public void tearDown() {
		for(BookBean bean:bookRepository.findAll()) {
			bookRepository.delete(bean.getId());
		}
	}
}
