package kameleooon.testKameleoon.quote.mapper;


import kameleooon.testKameleoon.quote.dto.QuoteDto;
import kameleooon.testKameleoon.quote.dto.ShowQuoteDto;
import kameleooon.testKameleoon.quote.model.Quote;

import java.time.format.DateTimeFormatter;

public class QuoteMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ShowQuoteDto toShowQuote(Quote quote) {
        return ShowQuoteDto.builder()
                .message(quote.getQuote())
                .rate(quote.getRate())
                .authorName(quote.getAuthor().getName())
                .createTime(quote.getCreateTime().format(formatter))
                .build();
    }

    public static QuoteDto toQuoteDto(Quote quote) {
        return QuoteDto.builder()
                .id(quote.getId())
                .author(quote.getAuthor().getName())
                .quote(quote.getQuote())
                .createTime(quote.getCreateTime().format(formatter))
                .build();
    }
}
