package cl.previred.user.application;

import cl.previred.user.domain.User;
import cl.previred.user.domain.UserRepository;
import cl.previred.user.infrastructure.controller.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAll {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> invoke() {

        List<User> result = userRepository.findAll();
        ArrayList<UserResponse> ur = new ArrayList<>();

        if(!result.isEmpty()){
            for(User user : result){
                UserResponse item = new UserResponse();
                item.setId(user.getId());
                item.setName(user.getName());
                item.setLast_name(user.getLast_name());
                item.setRut(user.getRut());
                item.setDv(user.getDv());
                item.setBirth_date(user.getBirth_date());
                item.setEmail(user.getEmail());

                ur.add(item);
            }
        }

        return ur;
    }
}
