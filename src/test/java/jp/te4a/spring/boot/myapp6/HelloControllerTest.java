package jp.te4a.spring.boot.myapp6;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(Enclosed.class) 
public class HelloControllerTest {

//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//    public static class index_要求 {
//    	@Autowired
//        private MockMvc mockMvc;
//
//        @Autowired
//        BookRepository bookRepository;
//
//    	@Test
//    	public void index_正常系_HTTPステータス200返却() throws Exception {
//            mockMvc.perform(get("/"))
//            		.andExpect(status().isOk());
//    	}
//    	@Test
//    	public void index_正常系_モデルに属性msgが存在する() throws Exception {
//            mockMvc.perform(get("/"))
//            		.andExpect(status().isOk())
//            		.andExpect(model().attributeExists("msg"));
//    	}
//    	@Test
//    	public void index_正常系_モデルの属性msgに規定文字列が格納される() throws Exception {
//            mockMvc.perform(get("/"))
//            		.andExpect(status().isOk())
//            		.andExpect(model().attribute("msg", is("this is a setting message")));
//    	}
//    	@Test
//    	public void index_正常系_viewがindexになる() throws Exception {
//            mockMvc.perform(get("/"))
//            		.andExpect(status().isOk())
//            		.andExpect(view().name("index"));
//    	}
//    }
//
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class postForm_パラメタ全指定 {
//    	@Autowired
//        private MockMvc mockMvc;
//        @Autowired
//        BookRepository bookRepository;
//        
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でHTTPステータス200返却() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "1")
//        		.param("title", "テストタイトル")
//        		.param("writter", "テスト著者")
//        		.param("publisher", "テスト出版社")
//        		.param("price", "100"))
//    		.andExpect(status().isOk());
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でモデルに属性msgが存在する() throws Exception {
//        	mockMvc.perform(post("/post").param("id", "1")
//        			.param("title", "テストタイトル")
//        			.param("writter", "テスト著者")
//        			.param("publisher", "テスト出版社")
//        			.param("price", "100"))
//    			.andExpect(status().isOk())
//            	.andExpect(model().attributeExists("msg"));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でモデルの属性msgに規定文字列が格納される() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "1")
//            		.param("title", "テストタイトル")
//            		.param("writter", "テスト著者")
//            		.param("publisher", "テスト出版社")
//            		.param("price", "100"))
//    			.andExpect(status().isOk())
//            	.andExpect(model().attribute("msg", is("<HR>ID:1<BR>タイトル:テストタイトル<BR>著者:テスト著者<BR>出版社:テスト出版社<BR>価格:100<BR><HR>")));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でviewがindexになる() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "1")
//            		.param("title", "テストタイトル")
//            		.param("writter", "テスト著者")
//            		.param("publisher", "テスト出版社")
//            		.param("price", "100"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でBookRepositoryに1件のBookBeanを生成する() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "1")
//            		.param("title", "テストタイトル")
//            		.param("writter", "テスト著者")
//            		.param("publisher", "テスト出版社")
//            		.param("price", "100"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//            int actual = bookRepository.findAll().size();
//            int expected = 1;
//            assertThat(actual, is(expected));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ全指定でBookRepositoryにパラメタ設定済みのBookBeanを生成する() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "1")
//            		.param("title", "テストタイトル")
//            		.param("writter", "テスト著者")
//            		.param("publisher", "テスト出版社")
//            		.param("price", "100"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//
//            BookBean actual = bookRepository.findAll().get(0);
//            BookBean expected = new BookBean(1, "テストタイトル", "テスト著者", "テスト出版社", 100);
//            assertThat(actual, is(expected));
//    	}
//    }
//
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//    public static class postForm_パラメタ数値0文字空指定 {
//    	@Autowired
//        private MockMvc mockMvc;
//        @Autowired
//        BookRepository bookRepository;
//        
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でHTTPステータス200返却() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk());
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でモデルに属性msgが存在する() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk())
//            	.andExpect(model().attributeExists("msg"));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でモデルの属性msgに規定文字列が格納される() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk())
//            	.andExpect(model().attribute("msg", is("<HR>ID:0<BR>タイトル:<BR>著者:<BR>出版社:<BR>価格:0<BR><HR>")));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でviewがindexになる() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//    	}
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でBookRepositoryに1件のBookBeanを生成する() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//            int actual = bookRepository.findAll().size();
//            int expected = 1;
//            assertThat(actual, is(expected));
//    	}
//
//    	@Test
//    	public void postForm_正常系_パラメタ数値0文字空指定でBookRepositoryにパラメタ設定済みのBookBeanを生成する() throws Exception {
//    		mockMvc.perform(post("/post").param("id", "0")
//                	.param("title", "")
//                	.param("writter", "")
//                	.param("publisher", "")
//                	.param("price", "0"))
//    			.andExpect(status().isOk())
//            	.andExpect(view().name("index"));
//
//            BookBean actual = bookRepository.findAll().get(0);
//            BookBean expected = new BookBean(0, "", "", "", 0);
//            assertThat(actual, is(expected));
//    	}
//    }
//
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//	@AutoConfigureMockMvc
//    public static class postForm_パラメタ指定なし {
//    	@Autowired
//        private MockMvc mockMvc;
//        @Autowired
//        BookRepository bookRepository;
//        
//		@Test
//		public void postForm_異常系_パラメタ一部指定なしでHTTPステータス400返却() throws Exception {
//	        mockMvc.perform(post("/post").param("id", "1").param("title", "テストタイトル"))
//	        		.andExpect(status().isBadRequest());
//		}
//	
//		@Test
//		public void postForm_異常系_パラメタ全て指定なしでHTTPステータス400返却() throws Exception {
//	        mockMvc.perform(post("/post"))
//	        		.andExpect(status().isBadRequest());
//		}
//    }
}
