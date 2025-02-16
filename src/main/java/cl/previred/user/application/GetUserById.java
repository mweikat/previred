package cl.previred.user.application;

import cl.previred.exceptions.user.custom.UserNotExistHandler;
import cl.previred.user.domain.UserRepository;
import cl.previred.user.infrastructure.controller.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserById {

    @Autowired
    private UserRepository userRepository;

    public UserResponse invoke(UUID uuid){

        UserResponse uur = new UserResponse();

        userRepository.findById(uuid).map(user -> {
            uur.setId(user.getId());
            uur.setName(user.getName());
            uur.setLast_name(user.getLast_name());
            uur.setEmail(user.getEmail());
            uur.setRut(user.getRut());
            uur.setDv(user.getDv());
            uur.setBirth_date(user.getBirth_date());

            return uur;

        }).orElseThrow(UserNotExistHandler::new);

        return uur;
    }
}
