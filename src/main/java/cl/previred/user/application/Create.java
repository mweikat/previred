package cl.previred.user.application;

import cl.previred.exceptions.user.custom.UserExistHandler;
import cl.previred.user.domain.User;
import cl.previred.user.domain.UserRepository;
import cl.previred.user.infrastructure.controller.requests.UserCreateRequest;
import cl.previred.user.infrastructure.controller.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Create {

    @Autowired
    private UserRepository userRepository;

    public UserResponse invoke(UserCreateRequest uRq){

        User userToEdt = userRepository.findByEmailOrRut(uRq.getEmail(), uRq.getRut());
        if(userToEdt!=null) throw new UserExistHandler();
        
        User u =  new User();

        u.setName(uRq.getName());
        u.setLast_name(uRq.getLastName());
        u.setEmail(uRq.getEmail());
        u.setRut(uRq.getRut());
        u.setDv(uRq.getDv());
        u.setBirth_date(uRq.getBirthDate());
        
        //encript
        u.setPassword(uRq.getPassword());

        User user = this.userRepository.save(u);

        UserResponse userRes = new UserResponse();
        userRes.setId(user.getId());
        userRes.setName(user.getName());
        userRes.setLast_name(user.getLast_name());
        userRes.setRut(user.getRut());
        userRes.setDv(user.getDv());
        userRes.setBirth_date(user.getBirth_date());
        userRes.setEmail(user.getEmail());

        return userRes;
    }
}
