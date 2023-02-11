package kameleooon.testKameleoon.user.service;

import kameleooon.testKameleoon.user.dto.UserDto;
import kameleooon.testKameleoon.user.model.User;

import java.util.Collection;
import java.util.Set;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto deleteUser(long id);

    Collection<UserDto> getAllUsers(Set<Long> ids, Integer from, Integer size);

    User getById(long userId);
}
