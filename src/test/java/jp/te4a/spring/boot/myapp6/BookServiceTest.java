package jp.te4a.spring.boot.myapp6;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Enclosed.class) 
public class BookServiceTest {

	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	@AutoConfigureMockMvc
	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public static class 初回のデータ登録 {
		
		@Autowired
		BookService bookService;
		
		@Test(expected = NullPointerException.class)
		public void save_異常系_nullは追加されない() {
			BookBean bookBean = null;
			bookService.save(bookBean);
		}
		
		@Test
		public void save_正常系_数値以外nullは登録される() {
			BookBean bookBean = new BookBean(0, null, null, null, 0);
			BookBean actual = bookService.save(bookBean);
			BookBean expected = null;
			assertThat(actual, is(expected));
		}
		
		@Test
		public void save_正常系_数値以外全て空文字は登録される() {
			BookBean bookBean = new BookBean(0, "", "", "", 0);
			BookBean actual = bookService.save(bookBean);
			BookBean expected = null;
			assertThat(actual, is(expected));
		}
		
		@Test
		public void save_正常系_負のid値は登録される() {
			BookBean bookBean = new BookBean(-1, "a", "b", "c", 100);
			BookBean actual = bookService.save(bookBean);
			BookBean expected = null;
			assertThat(actual, is(expected));
		}
	}
	
	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	@AutoConfigureMockMvc
	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public static class 二回目以降のデータ登録 {
		
		@Autowired
		BookService bookService;
		
		@Test
		public void save_正常系_同じidを持つBookBeanが登録される() {
			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
			BookBean newBookBean = new BookBean(1, "aa", "bb", "cc", 101);
			bookService.save(oldBookBean);
			BookBean actual = bookService.save(newBookBean);
			BookBean expected = oldBookBean;
			assertThat(actual, is(expected));
		}

		@Test
		public void save_正常系_異なるidを持つBookBeanが登録される() {
			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
			BookBean newBookBean = new BookBean(2, "aa", "bb", "cc", 101);
			bookService.save(oldBookBean);
			BookBean actual = bookService.save(newBookBean);
			BookBean expected = null;
			assertThat(actual, is(expected));
		}
		
		@Test
		public void save_正常系_同じidを持つBookBeanを登録すると登録数は1になる() {
			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
			BookBean newBookBean = new BookBean(1, "aa", "bb", "cc", 101);
			bookService.save(oldBookBean);
			bookService.save(newBookBean);
			int actual = bookService.findAll().size();
			int expected = 1;
			assertThat(actual, is(expected));
		}

		@Test
		public void save_正常系_異なるidを持つBookBeanを登録すると登録数は2になる() {
			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
			BookBean newBookBean = new BookBean(2, "aa", "bb", "cc", 101);
			bookService.save(oldBookBean);
			bookService.save(newBookBean);
			int actual = bookService.findAll().size();
			int expected = 2;
			assertThat(actual, is(expected));
		}
	}

	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	@AutoConfigureMockMvc
	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public static class データの取得 {
		
		@Autowired
		BookService bookService;
		
		@Test
		public void findAll_登録数が0ならfindAllでサイズ0のListを取得する() {
			int actual = bookService.findAll().size();
			int expected = 0;
			assertThat(actual, is(expected));
		}

		@Test
		public void findAll_登録数が1ならサイズ1のListを取得する() {
			BookBean bookBean = new BookBean(1, "a", "b", "c", 100);
			bookService.save(bookBean);
			int actual = bookService.findAll().size();
			int expected = 1;
			assertThat(actual, is(expected));
		}
		
		@Test
		public void findAll_登録数が複数ならサイズが存在件数だけのListを取得する() {
			BookBean bookBean1 = new BookBean(1, "a", "b", "c", 100);
			BookBean bookBean2 = new BookBean(2, "aa", "bb", "cc", 101);
			bookService.save(bookBean1);
			bookService.save(bookBean2);
			int actual = bookService.findAll().size();
			int expected = 2;
			assertThat(actual, is(expected));
		}

		@Test
		public void findAll_登録したBookBeanと同じフィールドを持つBookBeanを取得する() {
			BookBean bookBean = new BookBean(1, "a", "b", "c", 100);
			bookService.save(bookBean);
			BookBean actual = bookService.findAll().get(0);
			BookBean expected = bookBean;
			assertThat(actual, is(expected));
		}
	}
}
