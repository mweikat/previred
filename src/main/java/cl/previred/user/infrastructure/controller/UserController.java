package cl.previred.user.infrastructure.controller;

import cl.previred.user.application.*;
import cl.previred.user.infrastructure.controller.requests.UserUpdateRequest;
import cl.previred.user.infrastructure.controller.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.previred.user.infrastructure.controller.requests.UserCreateRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Users", description = "Users CRUD")
public class UserController {

    @Autowired
    private GetAll getAll;

    @Autowired
    private Create create;

    @Autowired
    private UpdateById updateUser;

    @Autowired
    private GetUserById getUserById;

    @Autowired
    private DeleteById deleteUserById;

    @Autowired
    private SearchByNameAndLastName searchByNameAndLastName;

    @GetMapping("/users")
    @Operation(summary = "Get all Users", description = "List all users in the database")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(getAll.invoke());
    }

    @PostMapping("/user")
    @Operation(summary = "Create User", description = "New user save in the database.")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest user) {
        return ResponseEntity.ok(create.invoke(user));
    }

    @PutMapping("/user/{id}")
    @Operation(summary = "Update User", description = "Update user by id, only Name, Last Name, Email and birthDate ")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest user) {
        updateUser.invoke(id, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Delete User", description = "Delete User by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserById.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("user/{id}")
    @Operation(summary = "Get User by ID", description = "Get User by unique ID")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserById.invoke(id));
    }

    @GetMapping("/users/search")
    public ResponseEntity<Page<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(searchByNameAndLastName.invoke(name, lastName, page, size));
    }


}

