package jp.te4a.spring.boot.myapp13.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {
	  @Id
	  @GeneratedValue
	  private Integer id ;
	  @Column(nullable = false)
	  private String title;
	  private String writter;
	  private String publisher;
	  private Integer price;
}
