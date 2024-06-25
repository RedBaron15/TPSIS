package booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import booking.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
