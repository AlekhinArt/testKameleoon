package kameleooon.testKameleoon.quote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ShowQuoteDto {
    private String authorName;
    private String message;
    private String createTime;
    private Long rate;
}
