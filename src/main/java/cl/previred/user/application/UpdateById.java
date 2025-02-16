package cl.previred.user.application;

import cl.previred.exceptions.user.custom.UserNotExistHandler;
import cl.previred.user.domain.User;
import cl.previred.user.domain.UserRepository;
import cl.previred.user.infrastructure.controller.requests.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateById {

    @Autowired
    private UserRepository userRepository;

    public void invoke(UUID uuid, UserUpdateRequest uur){

        User userToEdt = userRepository.findById(uuid).orElseThrow( UserNotExistHandler::new );

        userToEdt.setRut(uur.getRut());
        userToEdt.setDv(uur.getDv());
        userToEdt.setName(uur.getName());
        userToEdt.setLast_name(uur.getLast_name());
        userToEdt.setBirth_date(uur.getBirth_date());
        userToEdt.setEmail(uur.getEmail());

        if(!uur.getPassword().isEmpty()) userToEdt.setPassword(uur.getPassword());

        userRepository.save(userToEdt);

    }
}
