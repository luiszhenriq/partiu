package br.com.luis.partiu.models;


import br.com.luis.partiu.dto.event.EventRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)

    private String description;

    @Column(name = "cover_url", nullable = false)
    private String coverUrl;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "ends_in", nullable = false)
    private LocalDateTime endsIn;

    @Column(nullable = false)
    private Integer fee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;


    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locale_id")
    private Locale locale;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Event(EventRequestDto eventDto) {
        this.title = eventDto.title();
        this.description = eventDto.description();
        this.coverUrl = eventDto.coverUrl();
        this.startAt = eventDto.startAt();
        this.endsIn = eventDto.endsIn();
        this.fee = eventDto.fee();
    }
}
