package fc_prj.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	ArrayList<Comment> findById(Long questionId);
}
