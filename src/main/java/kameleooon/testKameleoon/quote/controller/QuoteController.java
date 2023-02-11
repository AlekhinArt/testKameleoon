package kameleooon.testKameleoon.quote.controller;

import kameleooon.testKameleoon.quote.dto.QuoteDto;
import kameleooon.testKameleoon.quote.dto.ShowQuoteDto;
import kameleooon.testKameleoon.quote.service.QuoteService;
import kameleooon.testKameleoon.valid.Create;
import kameleooon.testKameleoon.valid.Update;
import kameleooon.testKameleoon.quote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class QuoteController {

    private final QuoteService quoteService;


    @PostMapping("/quote/{userId}")
    public ShowQuoteDto createQuote(@PositiveOrZero @PathVariable Long userId,
                                    @Validated(Create.class) @RequestBody QuoteDto quoteDto) {
        log.info("createQuote userid {}, quote author {}, text {}", userId, quoteDto.getAuthor(), quoteDto.getQuote());
        return quoteService.createQuote(userId, quoteDto);

    }

    @DeleteMapping("/quote/{userId}/{quoteId}")
    public ShowQuoteDto deleteQuote(@PositiveOrZero @PathVariable Long userId,
                                    @PathVariable Long quoteId) {
        log.info("deleteQuote userid {}, quoteId {}", userId, quoteId);
        return quoteService.deleteQuote(quoteId, userId);
    }

    @PutMapping("/quote/{userId}")
    public ShowQuoteDto changeQu0te(@PositiveOrZero @PathVariable Long userId,
                                    @Validated(Update.class) @RequestBody QuoteDto quoteDto) {
        log.info("changeQu0te userid {}, quote author {}, text {}", userId, quoteDto.getAuthor(), quoteDto.getQuote());
        return quoteService.changeQuote(userId, quoteDto);

    }

    @GetMapping("/{quoteId}")
    public ShowQuoteDto getQuote(@PositiveOrZero @PathVariable Long quoteId) {
        log.info("getQuote quoteId {}", quoteId);
        return quoteService.getQuote(quoteId);
    }

    @GetMapping("/quote/{quoteId}")
    public QuoteDto getFullQuote(@PositiveOrZero @PathVariable Long quoteId) {
        log.info("getQuote getFullQuote {}", quoteId);
        return quoteService.getFullQuote(quoteId);
    }

    @GetMapping("/quote/top")
    public Collection<ShowQuoteDto> getTopQuote() {
        log.info("get getTopQuote ");
        return quoteService.showTopQuote();
    }

    @GetMapping("/quote/flop")
    public Collection<ShowQuoteDto> getFlopQuote() {
        log.info("get getFlopQuote ");
        return quoteService.showFlopQuote();
    }

    @GetMapping("/quote/last")
    public Collection<ShowQuoteDto> getLastQuote() {
        log.info("get getLastQuote ");
        return quoteService.showLastQuote();
    }

    @PutMapping("/quote/{userId}/{quoteId}/{change}")
    public ShowQuoteDto changeRate(@PositiveOrZero @PathVariable Long userId,
                                   @PositiveOrZero @PathVariable Long quoteId,
                                   @NotNull @PathVariable Boolean change) {
        log.info("get changeRate userId {}, quoteId {}, change {} ", userId, quoteId, change);
        return quoteService.changeRate(userId, quoteId, change);

    }

}
