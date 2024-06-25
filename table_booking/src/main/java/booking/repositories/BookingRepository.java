package booking.repositories;

import booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import booking.models.Booking ;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
