package booking.services;

import booking.models.Image;
import booking.models.RestaurantTable;
import booking.repositories.ImageRepository;
import booking.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final ImageRepository imageRepository;

    public List<RestaurantTable> listTables() {
        return tableRepository.findAll();
    }

    public void add(MultipartFile img, String description, int capacity, String type, float price) throws IOException {
        Image image = new Image();
        image.setImageData(img.getBytes());
        imageRepository.save(image);
        RestaurantTable table = new RestaurantTable();
        table.setImage(image);
        table.setActive(true);
        table.setDescription(description);
        table.setType(type);
        table.setCapacity(capacity);
        table.setBase_price(price);
        tableRepository.save(table);
    }

    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }

    public void setActive(Long id) {
       RestaurantTable table = tableRepository.getById(id);
       if(table.isActive())
           table.setActive(false);
       else
           table.setActive(true);
       tableRepository.save(table);

    }

    public RestaurantTable getTableById(Long id) {
        return tableRepository.getById(id);
    }
}
