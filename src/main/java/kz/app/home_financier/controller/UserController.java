package kz.app.home_financier.controller;

import kz.app.home_financier.facade.UserFacade;
import kz.app.home_financier.model.dto.UpdateUserDTO;
import kz.app.home_financier.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<UserDTO> getProfile() {
        return ResponseEntity.ok(userFacade.getProfile());
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserDTO userDTO) {
        return ResponseEntity.ok(userFacade.updateUser(userDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser() {
        userFacade.deleteUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
