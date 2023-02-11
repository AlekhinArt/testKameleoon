package kameleooon.testKameleoon.quote.service;

import kameleooon.testKameleoon.exceptions.DataConflictException;
import kameleooon.testKameleoon.exceptions.NotFoundException;
import kameleooon.testKameleoon.quote.dto.QuoteDto;
import kameleooon.testKameleoon.quote.dto.ShowQuoteDto;
import kameleooon.testKameleoon.quote.mapper.QuoteMapper;
import kameleooon.testKameleoon.quote.model.Quote;
import kameleooon.testKameleoon.quote.repository.QuoteRepository;
import kameleooon.testKameleoon.user.model.User;
import kameleooon.testKameleoon.user.repository.UserRepository;
import kameleooon.testKameleoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteServiceImpl implements QuoteService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;


    @Override
    public ShowQuoteDto createQuote(Long userId, QuoteDto quoteDto) {
        User user = userService.getById(userId);

        return QuoteMapper.toShowQuote(quoteRepository.save(Quote.builder()
                .author(user)
                .quote(quoteDto.getQuote())
                .createTime(LocalDateTime.now())
                .rate(0L)
                .build()));
    }

    @Override
    public ShowQuoteDto deleteQuote(Long quoteId, Long userId) {
        Quote quote = getQuoteFromRep(quoteId);
        if (userId != null) {
            userService.getById(userId);
            if (!Objects.equals(quote.getAuthor().getId(), userId)) {
                log.debug("deleteQuote.NotFoundException(Quote not found)");
                throw new NotFoundException("Quote not found");
            }
        }
        try {
            log.info("deleteQuote id: {}", quoteId);
            return QuoteMapper.toShowQuote(quote);
        } catch (Exception e) {
            log.debug("deleteQuote.NotFoundException(Quote not found)");
            throw new NotFoundException("Quote not found");
        }
    }

    @Override
    public ShowQuoteDto changeQuote(Long userId, QuoteDto quoteDto) {
        userService.getById(userId);
        Quote quote = quoteRepository.findById(quoteDto.getId())
                .orElseThrow(() -> new NotFoundException("This Quote not found"));
        if (!userId.equals(quote.getAuthor().getId())) {
            log.debug("changeQuote.NotFoundException(no rights to edit)");
            throw new NotFoundException("no rights to edit");
        }
        quote.setQuote(quoteDto.getQuote());

        return QuoteMapper.toShowQuote(quoteRepository.save(quote));
    }

    @Override
    public ShowQuoteDto getQuote(Long quoteId) {
        return QuoteMapper.toShowQuote(quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("This Quote not found")));
    }

    @Override
    public QuoteDto getFullQuote(Long quoteId) {
        return QuoteMapper.toQuoteDto(quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("This Quote not found")));
    }

    @Override
    public Collection<ShowQuoteDto> showTopQuote() {

        return quoteRepository.topQuotes().stream()
                .map(QuoteMapper::toShowQuote)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ShowQuoteDto> showFlopQuote() {
        return quoteRepository.flopQuotes().stream()
                .map(QuoteMapper::toShowQuote)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ShowQuoteDto> showLastQuote() {
        return quoteRepository.findByOrderByCreateTimeDesc().stream()
                .map(QuoteMapper::toShowQuote)
                .collect(Collectors.toList());
    }

    @Override
    public ShowQuoteDto changeRate(Long userId, Long quoteId, boolean change) {
        User user = userService.getById(userId);
        Quote quote = getQuoteFromRep(quoteId);
        checkChange(user, change, quoteId);
        Collection<Quote> quotes;
        if (change) {
            quote.setRate(quote.getRate() + 1);
            quotes = user.getGoodQuote();
            quotes.add(quote);
            user.setGoodQuote(quotes);
            userRepository.deleteFromBadQuote(userId, quoteId);
        } else {
            quote.setRate(quote.getRate() - 1);
            quotes = user.getBadQuote();
            quotes.add(quote);
            user.setBadQuote(quotes);
            userRepository.deleteFromGoodQuote(userId, quoteId);
        }
        log.info(" {} ", quote.getId());
        userRepository.save(user);

        return QuoteMapper.toShowQuote(quoteRepository.save(quote));
    }

    public Quote getQuoteFromRep(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("This quote not found"));
    }

    public void checkChange(User user, boolean change, Long quoteId) {
        if (change) {
            for (Quote quote : user.getGoodQuote()) {
                if (quote.getId() == quoteId) {
                    log.info("DataConflictException.checkChange.Positive");
                    throw new DataConflictException("You can only vote once");
                }

            }
        } else {
            for (Quote quote : user.getBadQuote()) {
                if (quote.getId() == quoteId) {
                    log.info("DataConflictException.checkChange.Negative");
                    throw new DataConflictException("You can only vote once");
                }
            }
        }
    }
}
