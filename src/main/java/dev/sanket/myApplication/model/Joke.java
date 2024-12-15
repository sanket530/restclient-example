package dev.sanket.myApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Joke {
    @Id
    private Long id;
    @Column(length = 50)
    private String type;
    private String setup;
    private String punchline;
}
