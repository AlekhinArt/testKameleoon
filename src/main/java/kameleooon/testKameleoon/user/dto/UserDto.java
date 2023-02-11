package kameleooon.testKameleoon.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    @NotNull
    @NotBlank(message = "The login cannot be empty.")
    private String name;
    @NotNull
    @NotBlank(message = "The email cannot be empty.")
    @Email(regexp = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",
            message = "The email must match the format RFC 5322.")
    private String email;
    private Collection<String> goodQuote;
    private Collection<String> badQuote;
}
