package fc_prj.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
	
	@OneToMany(mappedBy="question")
	@OrderBy("id asc")
	private List<Comment> comments;
	
	
	public Long getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	
	public String getWriter() {
		return this.writer;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public List<Comment> getComments() {
		return this.comments;
	}
	
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
