package cl.previred.user.application;

import cl.previred.exceptions.user.custom.UserNotExistHandler;
import cl.previred.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteById {

    @Autowired
    private UserRepository userRepository;

    public void invoke(UUID uuid){

        if (!userRepository.existsById(uuid)) {
            throw new UserNotExistHandler();
        }

        userRepository.deleteById(uuid);
    }
}
