package ru.sema1ary.vedrocraftapi.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PreLoginEvent;
import lombok.RequiredArgsConstructor;
import ru.sema1ary.vedrocraftapi.ormlite.model.User;
import ru.sema1ary.vedrocraftapi.ormlite.model.gender.Gender;
import ru.sema1ary.vedrocraftapi.service.user.UserService;

@RequiredArgsConstructor
public class JoinListener {
    private final UserService userService;

    @Subscribe
    public void onJoin(PreLoginEvent event) {
        String username = event.getUsername();

        if(username.isEmpty()) {
            return;
        }

        if(userService.findByUsername(username).isEmpty()) {
            userService.save(User.builder()
                    .username(username)
                    .reputation(5000)
                    .gender(Gender.OTHER)
                    .isVanishActive(false)
                    .isSticksEnabled(true)
                    .build());
        }
    }
}
