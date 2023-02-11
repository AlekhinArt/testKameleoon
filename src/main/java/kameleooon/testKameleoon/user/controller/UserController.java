package kameleooon.testKameleoon.user.controller;

import kameleooon.testKameleoon.user.dto.UserDto;
import kameleooon.testKameleoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@Validated @RequestBody UserDto user) {
        log.info("createUser user : {}", user);
        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public UserDto deleteUser(@PositiveOrZero @PathVariable long id) {
        log.info("deleteUser userId: {}", id);
        return userService.deleteUser(id);
    }

    @GetMapping
    public Collection<UserDto> getAllUsers(@RequestParam(name = "ids", required = false) Set<Long> ids,
                                           @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                           @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("getAllUsers with param ids: {}, from: {}, size: {}", ids, from, size);
        return userService.getAllUsers(ids, from, size);
    }


}
