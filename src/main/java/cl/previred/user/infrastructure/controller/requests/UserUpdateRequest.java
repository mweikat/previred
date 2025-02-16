package cl.previred.user.infrastructure.controller.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
public class UserUpdateRequest {

    private Long rut;
    private String dv;
    private String name;
    private String last_name;
    private String email;
    private LocalDate birth_date;
    private String password = null;

    public Long getRut() {
        return rut;
    }

    public void setRut(@NotEmpty  Long rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(@NotEmpty String dv) {
        this.dv = dv;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotEmpty @Size(min = 2, max = 30) String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@Size(min = 2, max = 30) String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
