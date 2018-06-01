package jp.te4a.spring.boot.myapp6;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

//@RunWith(Enclosed.class) 
public class BookRepositoryTest {

	
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class 初回のデータ登録 {
//		
//		@Autowired
//		BookRepository bookRepository;
//		
//		@Test(expected = NullPointerException.class)
//		public void save_異常系_nullは追加されない() {
//			BookBean bookBean = null;
//			bookRepository.save(bookBean);
//		}
//		
//		@Test
//		public void save_正常系_数値以外nullは登録される() {
//			BookBean bookBean = new BookBean(0, null, null, null, 0);
//			BookBean actual = bookRepository.save(bookBean);
//			BookBean expected = null;
//			assertThat(actual, is(expected));
//		}
//		
//		@Test
//		public void save_正常系_数値以外全て空文字は登録される() {
//			BookBean bookBean = new BookBean(0, "", "", "", 0);
//			BookBean actual = bookRepository.save(bookBean);
//			BookBean expected = null;
//			assertThat(actual, is(expected));
//		}
//		
//		@Test
//		public void save_正常系_負のid値は登録される() {
//			BookBean bookBean = new BookBean(-1, "a", "b", "c", 100);
//			BookBean actual = bookRepository.save(bookBean);
//			BookBean expected = null;
//			assertThat(actual, is(expected));
//		}
//	}
//	
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class 二回目以降のデータ登録 {
//		
//		@Autowired
//		BookRepository bookRepository;
//		
//		@Test
//		public void save_正常系_同じidを持つBookBeanが登録される() {
//			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
//			BookBean newBookBean = new BookBean(1, "aa", "bb", "cc", 101);
//			bookRepository.save(oldBookBean);
//			BookBean actual = bookRepository.save(newBookBean);
//			BookBean expected = oldBookBean;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void save_正常系_異なるidを持つBookBeanが登録される() {
//			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
//			BookBean newBookBean = new BookBean(2, "aa", "bb", "cc", 101);
//			bookRepository.save(oldBookBean);
//			BookBean actual = bookRepository.save(newBookBean);
//			BookBean expected = null;
//			assertThat(actual, is(expected));
//		}
//		
//		@Test
//		public void save_正常系_同じidを持つBookBeanを登録すると登録数は1になる() {
//			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
//			BookBean newBookBean = new BookBean(1, "aa", "bb", "cc", 101);
//			bookRepository.save(oldBookBean);
//			bookRepository.save(newBookBean);
//			int actual = bookRepository.findAll().size();
//			int expected = 1;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void save_正常系_異なるidを持つBookBeanを登録すると登録数は2になる() {
//			BookBean oldBookBean = new BookBean(1, "a", "b", "c", 100);
//			BookBean newBookBean = new BookBean(2, "aa", "bb", "cc", 101);
//			bookRepository.save(oldBookBean);
//			bookRepository.save(newBookBean);
//			int actual = bookRepository.findAll().size();
//			int expected = 2;
//			assertThat(actual, is(expected));
//		}
//	}
//	
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class 既存データの削除 {
//		
//		@Autowired
//		BookRepository bookRepository;
//		
//		@Test
//		public void delete_正常系_一件だけ存在するidを指定したdeleteを実行するとBookBeanが削除される() {
//			BookBean bookBean = new BookBean(1, "a", "b", "c", 100);
//			bookRepository.save(bookBean);
//			bookRepository.delete(bookBean.getId());
//			int actual = bookRepository.findAll().size();
//			int expected = 0;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void delete_正常系_複数件だけ存在するidを指定したdeleteを実行するとBookBeanが削除される() {
//			BookBean bookBean1 = new BookBean(1, "a", "b", "c", 100);
//			BookBean bookBean2 = new BookBean(2, "aa", "bb", "cc", 101);
//			bookRepository.save(bookBean1);
//			bookRepository.save(bookBean2);
//			int expected = bookRepository.findAll().size() - 1;
//			bookRepository.delete(bookBean1.getId());
//			int actual = bookRepository.findAll().size();
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void delete_正常系_存在しないidを指定したdeleteを実行すると処理は行われない() {
//			BookBean BookBean = new BookBean(1, "a", "b", "c", 100);
//			bookRepository.save(BookBean);
//			bookRepository.delete(1);
//			int expected = bookRepository.findAll().size();
//			int actual = bookRepository.findAll().size();
//			assertThat(actual, is(expected));
//		}
//	}
//	
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class データの取得 {
//		
//		@Autowired
//		BookRepository bookRepository;
//		
//		@Test
//		public void findAll_登録数が0ならfindAllでサイズ0のListを取得する() {
//			int actual = bookRepository.findAll().size();
//			int expected = 0;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void findAll_登録数が1ならサイズ1のListを取得する() {
//			BookBean bookBean = new BookBean(1, "a", "b", "c", 100);
//			bookRepository.save(bookBean);
//			int actual = bookRepository.findAll().size();
//			int expected = 1;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void findAll_登録数が複数ならサイズが存在件数だけのListを取得する() {
//			BookBean bookBean1 = new BookBean(1, "a", "b", "c", 100);
//			BookBean bookBean2 = new BookBean(2, "aa", "bb", "cc", 101);
//			bookRepository.save(bookBean1);
//			bookRepository.save(bookBean2);
//			int actual = bookRepository.findAll().size();
//			int expected = 2;
//			assertThat(actual, is(expected));
//		}
//
//		@Test
//		public void findAll_登録したBookBeanと同じフィールドを持つBookBeanを取得する() {
//			BookBean bookBean = new BookBean(1, "a", "b", "c", 100);
//			bookRepository.save(bookBean);
//			BookBean actual = bookRepository.findAll().get(0);
//			BookBean expected = bookBean;
//			assertThat(actual, is(expected));
//		}
//	}
}
