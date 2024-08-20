package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import demo.repo.*;
import demo.model.*;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepository;
    
    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
 
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado "));
    }
 
    @PostMapping("/user/save")
    public User createUser(@Validated @RequestBody User product) {
        return userRepository.save(product);
    }
 
    @PutMapping("user/update/{id}")
    public User updateUser(@PathVariable Long id, @Validated @RequestBody User userDetails) throws Exception {
    	User user = userRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
 
    	user.setUsu_nombre(userDetails.getUsu_nombre());
    	user.setUsu_clave(userDetails.getUsu_clave());
    	user.setUsu_email(userDetails.getUsu_email());
    	user.setFoto_perfil(userDetails.getFoto_perfil());
    	user.setNsfw_allow(userDetails.getNsfw_allow());
    	user.setDark_mode(userDetails.getDark_mode());
    	user.setFecha_alta(userDetails.getFecha_alta());
    	user.setFecha_baja(userDetails.getFecha_baja());


 
        return userRepository.save(user);
    }
 
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
    	User p = userRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
 
        userRepository.delete(p);
 
        return ResponseEntity.ok().build();
    }
}