package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.simplehateoasservice.models.Booking;

import java.util.List;


@RepositoryRestResource
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {

    @RestResource(path = "staffed", rel = "staffed")
    @Query("from Booking booking where booking.state='ON_ASSEMBLY'")
    Page<Booking> findAllOnAssembly(Pageable pageable);

    @RestResource(path = "send", rel = "send")
    @Query("from Booking booking where booking.state = 'SENT'")
    List<Booking> findAllSend();
}
