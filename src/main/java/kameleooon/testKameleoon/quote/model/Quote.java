package kameleooon.testKameleoon.quote.model;

import jakarta.persistence.*;
import kameleooon.testKameleoon.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    private String quote;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "rate")
    private Long rate;

}
