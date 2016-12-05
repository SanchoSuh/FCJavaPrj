package fc_prj.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_writer"))
	private User writer;
	
	@Lob
	@Column(nullable=false)
	private String contents;
	
	private LocalDateTime createdDate;
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Comment() {
	}
	
	public Comment(Question bindedQuestion, User writer, String contents) {
		this.question = bindedQuestion;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = LocalDateTime.now();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", question=" + question + ", writer=" + writer + ", contents=" + contents
				+ ", createdDate=" + createdDate + "]";
	}
	
	
	
}
