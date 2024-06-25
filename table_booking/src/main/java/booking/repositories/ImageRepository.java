package booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import booking.models.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
