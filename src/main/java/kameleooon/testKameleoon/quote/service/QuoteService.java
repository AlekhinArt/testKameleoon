package kameleooon.testKameleoon.quote.service;


import kameleooon.testKameleoon.quote.dto.QuoteDto;
import kameleooon.testKameleoon.quote.dto.ShowQuoteDto;

import java.util.Collection;

public interface QuoteService {

    ShowQuoteDto createQuote(Long userId, QuoteDto quoteDto);

    ShowQuoteDto deleteQuote(Long quoteId, Long userId);

    ShowQuoteDto changeQuote(Long userId, QuoteDto quoteDto);

    ShowQuoteDto getQuote(Long quoteId);

    QuoteDto getFullQuote(Long quoteId);

    Collection<ShowQuoteDto> showTopQuote();

    Collection<ShowQuoteDto> showFlopQuote();

    Collection<ShowQuoteDto> showLastQuote();

    ShowQuoteDto changeRate(Long userId, Long quoteId, boolean change);

}
