package kameleooon.testKameleoon.user.mapper;


import kameleooon.testKameleoon.quote.model.Quote;
import kameleooon.testKameleoon.user.dto.UserDto;
import kameleooon.testKameleoon.user.model.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .badQuote(getRateQuote(user.getBadQuote()))
                .goodQuote(getRateQuote(user.getGoodQuote()))
                .build();

    }

    private static Collection<String> getRateQuote(Collection<Quote> quotes) {
        Collection<String> rateQuotes = new ArrayList<>();
        if (quotes == null) {
            return rateQuotes;
        }
        for (Quote quote : quotes) {
            rateQuotes.add(quote.getQuote());
        }
        return rateQuotes;
    }

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return User.builder()
                .email(userDto.getEmail())
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
    }

}
