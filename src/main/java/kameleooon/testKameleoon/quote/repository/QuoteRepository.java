package kameleooon.testKameleoon.quote.repository;

import kameleooon.testKameleoon.quote.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "select * from QUOTES q order by q.rate DESC LIMIT 10", nativeQuery = true)
    Collection<Quote> topQuotes();

    @Query(value = "select * from QUOTES q order by q.rate ASC LIMIT 10", nativeQuery = true)
    Collection<Quote> flopQuotes();

    @Query(value = "select * from QUOTES q order by q.createTime ASC LIMIT 10 ", nativeQuery = true)
    Collection<Quote> findByOrderByCreateTimeDesc();


}
