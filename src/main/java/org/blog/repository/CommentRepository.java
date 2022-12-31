package org.blog.repository;

import java.util.List;

import org.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value="SELECT * FROM comment c WHERE c.post_id = ?1",nativeQuery = true )
	public List<Comment> findBypost(Long postId);
	
	
	@Query(value="SELECT * FROM comment c WHERE c.user_id = ?1",nativeQuery = true )
	public List<Comment> findCommentsByUserId(Long userId);
}
