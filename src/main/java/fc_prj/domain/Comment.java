package fc_prj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_question"))
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable=false, length=200)
	private String contents;
	
}
