package cl.previred.user.application;

import cl.previred.user.domain.User;
import cl.previred.user.domain.UserRepository;
import cl.previred.user.infrastructure.controller.responses.UserResponse;
import cl.previred.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchByNameAndLastName {

    @Autowired
    private UserRepository userRepository;

    public Page<UserResponse> invoke(String name, String lastName, int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        //name = StringUtils.removeAccents(name);
        //lastName = StringUtils.removeAccents(lastName);

        Page<User> usersPage = userRepository.searchUsers(name, lastName, pageable);

        return usersPage.map(user -> {
            UserResponse uur = new UserResponse();
            uur.setId(user.getId());
            uur.setName(user.getName());
            uur.setLast_name(user.getLast_name());
            uur.setEmail(user.getEmail());
            uur.setRut(user.getRut());
            uur.setDv(user.getDv());
            uur.setBirth_date(user.getBirth_date());

            return uur;
        });
    }
}
