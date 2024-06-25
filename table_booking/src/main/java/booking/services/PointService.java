package booking.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import booking.models.Point;
import booking.repositories.PointRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public Point getPointById(Long id) {
        return pointRepository.getById(id);
    }
}
