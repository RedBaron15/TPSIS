package booking.services;

import booking.models.Booking;
import booking.models.User;
import booking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;


    public void cancelById(Long id) {
        Booking booking = bookingRepository.getById(id);
        booking.setCancelled(true);
        bookingRepository.save(booking);
    }

    public List<Booking> list() {
        return bookingRepository.findAll();
    }

    public void confirmedById(Long id) {
        Booking booking = bookingRepository.getById(id);
        booking.setConfirmed(true);
        bookingRepository.save(booking);
    }
}
