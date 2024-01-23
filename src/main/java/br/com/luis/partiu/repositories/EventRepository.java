package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByLocaleCity(String city);

    List<Event> findByLocaleState(String state);

    List<Event> findByCategoryName(String name);

    Page<Event> findByFee(Integer fee, Pageable pageable);
}
