package kameleooon.testKameleoon.user.model;

import jakarta.persistence.*;
import kameleooon.testKameleoon.quote.model.Quote;
import lombok.*;

import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name", unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    @OneToMany
    @JoinTable(name = "good_quote",
            joinColumns = {@JoinColumn(name = "QUOTE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})

    private Collection<Quote> goodQuote;
    @OneToMany
    @JoinTable(name = "bad_quote",
            joinColumns = {@JoinColumn(name = "QUOTE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Collection<Quote> badQuote;

}
