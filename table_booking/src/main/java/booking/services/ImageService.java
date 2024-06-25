package booking.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import booking.models.Image;
import booking.repositories.ImageRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getImageById(Long id) {
        return imageRepository.getById(id);
    }
}
