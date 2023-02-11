package kameleooon.testKameleoon.user.repository;

import kameleooon.testKameleoon.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByIdIn(Set<Long> ids, Pageable pageable);


    @Modifying
    @Query(value = "DELETE from BAD_QUOTE where USER_ID = ? and QUOTE_ID = ? ", nativeQuery = true)
    void deleteFromBadQuote(Long userId, Long quoteId);


    @Modifying
    @Query(value = "DELETE from GOOD_QUOTE where USER_ID = ? and QUOTE_ID = ? ", nativeQuery = true)
    void deleteFromGoodQuote(Long userId, Long quoteId);

}
