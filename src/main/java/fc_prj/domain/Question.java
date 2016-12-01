package fc_prj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User user;
	
	@Column(nullable=false, length=40)
	private String writer;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Column(nullable=false)
	@Lob
	private String contents;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setWriter(User user) {
		System.out.println("question.setWriter() : user name : " + user.getName());
		this.user = user;
		this.writer = user.getName(); 
	}
	
}
