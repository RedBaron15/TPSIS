package booking.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import booking.models.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Promotion findByPromocode(String promo);
}
