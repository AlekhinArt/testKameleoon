package kameleooon.testKameleoon.quote.dto;

import kameleooon.testKameleoon.valid.Create;
import kameleooon.testKameleoon.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class QuoteDto {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotNull(groups = {Create.class})
    @NotBlank(message = "The author cannot be empty.")
    private String author;
    @NotNull(message = "The quote cannot be empty.",
            groups = {Create.class, Update.class})
    @NotBlank(message = "The quote cannot be empty.",
            groups = {Create.class, Update.class})
    private String quote;
    private String createTime;
}
