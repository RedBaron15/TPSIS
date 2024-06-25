package booking.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import booking.models.Point;

public interface PointRepository extends JpaRepository<Point,Long> {
}
