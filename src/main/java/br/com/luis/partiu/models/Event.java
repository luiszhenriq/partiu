package br.com.luis.partiu.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "ends_in")
    private LocalDateTime endsIn;

    private Integer fee;
}
