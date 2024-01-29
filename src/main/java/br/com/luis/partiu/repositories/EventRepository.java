package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e " +
            "WHERE (:fee IS NULL OR e.fee = :fee) " +
            "AND (:city IS NULL OR e.locale.city = :city) " +
            "AND (:state IS NULL OR e.locale.state = :state) " +
            "AND (:name IS NULL OR e.category.name = :name)")
    Page<Event> findWithFilters(
            @Param("fee") Integer fee,
            @Param("city") String city,
            @Param("state") String state,
            @Param("name") String name,
            Pageable pageable
    );

    @Query("SELECT e FROM Event e WHERE e.startAt BETWEEN :startDate AND :endDate")
    Page<Event> findEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

}
