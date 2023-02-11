package kameleooon.testKameleoon.user.service;

import kameleooon.testKameleoon.exceptions.DataConflictException;
import kameleooon.testKameleoon.exceptions.NotFoundException;
import kameleooon.testKameleoon.user.dto.UserDto;
import kameleooon.testKameleoon.user.mapper.UserMapper;
import kameleooon.testKameleoon.user.model.User;
import kameleooon.testKameleoon.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser;
        try {
            newUser = userRepository.save(UserMapper.toUser(userDto));
        } catch (Exception e) {
            throw new DataConflictException(" Name or email are used");
        }
        log.info("createUser user: {}", newUser);
        return UserMapper.toUserDto(newUser);
    }

    @Override
    public UserDto deleteUser(long id) {
        User newUser = getById(id);
        try {
            log.info("deleteUser id: {}", id);
            userRepository.deleteById(id);
            return UserMapper.toUserDto(newUser);
        } catch (Exception e) {
            log.debug("deleteUser.NotFoundException(User not found)");
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public Collection<UserDto> getAllUsers(Set<Long> ids, Integer from, Integer size) {
        log.info("Get all users with param ids: {}, from: {}, size: {};", ids, from, size);
        Pageable pageable = PageRequest.of(from / size, size);
        if (ids.isEmpty()) {
            return userRepository.findAll(pageable).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        } else {
            return userRepository.findAllByIdIn(ids, pageable).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public User getById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
    }
}
