package cl.previred.user.infrastructure.controller.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
public class UserCreateRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private Long rut;
    private String dv;
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(@NotEmpty @Size(min = 2, max = 30) String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 30) String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "{jakarta.validation.constraints.Email.message}") @NotEmpty @Size(max = 100) String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 6, max = 12) String password) {
        this.password = password;
    }

    public Long getRut() {
        return rut;
    }

    public void setRut(@NotEmpty @Size(min = 8, max = 9) Long rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(@NotEmpty @Size(min = 1, max = 1) String dv) {
        this.dv = dv;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
