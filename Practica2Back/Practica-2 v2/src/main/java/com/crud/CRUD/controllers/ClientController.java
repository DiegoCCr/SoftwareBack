package com.crud.CRUD.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.CRUD.models.ClientModel;
import com.crud.CRUD.services.ClientServices;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    
    @Autowired
    private ClientServices userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody Map<String, Object> userData) {
        try {
            ClientModel user = new ClientModel();
            user.setNombre((String) userData.get("nombre"));
            user.setApellidoPaterno((String) userData.get("apellidoPaterno"));
            user.setApellidoMaterno((String) userData.get("apellidoMaterno"));
            user.setCorreo((String) userData.get("correo"));
            user.setContrasena((String) userData.get("contrasena"));
            user.setRol(userData.get("rol") != null ? Integer.valueOf(userData.get("rol").toString()) : 1);

            // Validar y decodificar fotoPerfil
            if (userData.get("fotoPerfil") != null) {
                try {
                    user.setFotoPerfil(java.util.Base64.getDecoder().decode((String) userData.get("fotoPerfil")));
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.badRequest().body("Error: Foto de perfil no válida.");
                }
            }

            // Intentar guardar el usuario
            ClientModel savedUser = this.userService.saveUser(user);
            return ResponseEntity.ok(savedUser);

        } catch (IllegalArgumentException e) {
            // Manejar error de correo duplicado u otras validaciones de lógica de negocio
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getUser/{id}")
    public Optional<ClientModel> getUserById(@PathVariable Long id){
        return this.userService.getById(id); 
    }

    @PutMapping("/updateUserByEmail")
    public ResponseEntity<?> updateUserByEmail(@RequestBody Map<String, Object> userData) {
        try {
            // Buscar el usuario por correo
            Optional<ClientModel> existingUser = this.userService.findByEmail((String) userData.get("correo"));

            // Verificar si el usuario existe
            if (!existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            ClientModel user = existingUser.get();

            // Actualizar los campos del usuario
            user.setNombre((String) userData.get("nombre"));
            user.setApellidoPaterno((String) userData.get("apellidoPaterno"));
            user.setApellidoMaterno((String) userData.get("apellidoMaterno"));
            user.setContrasena((String) userData.get("contrasena"));
            user.setRol(userData.get("rol") != null ? Integer.valueOf(userData.get("rol").toString()) : 1);

            // Validar y decodificar fotoPerfil si está presente
            if (userData.get("fotoPerfil") != null) {
                try {
                    user.setFotoPerfil(java.util.Base64.getDecoder().decode((String) userData.get("fotoPerfil")));
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.badRequest().body("Error: Foto de perfil no válida.");
                }
            }

            // Intentar guardar el usuario actualizado
            ClientModel updatedUser = this.userService.updateUser(user);
            return ResponseEntity.ok(updatedUser); // Retornar el usuario actualizado en la respuesta

        } catch (IllegalArgumentException e) {
            // Manejar cualquier otro tipo de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUserByEmail")
    public ResponseEntity<?> deleteUserByEmail(@RequestBody Map<String, String> payload) {
        String correo = payload.get("correo");
        Optional<ClientModel> user = userService.findByEmail(correo);

        if (user.isPresent()) {
            userService.deleteUserByEmail(correo);
            return ResponseEntity.ok().body("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody ClientModel request) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientModel> user = userService.findByEmailAndPassword(request.getCorreo(), request.getContrasena());
        if (user.isPresent()) {
            response.put("success", true);
            response.put("message", "Login exitoso");
            response.put("userId", user.get().getId());
            response.put("rol", user.get().getRol()); // Enviar el rol al frontend
        } else {
            response.put("success", false);
            response.put("message", "Correo o contraseña incorrectos");
        }
        return response;
    }

    // Nuevo endpoint para obtener los datos del usuario autenticado
    @GetMapping("/getUserData/{id}")
    public Optional<ClientModel> getUserData(@PathVariable Long id) {
        return userService.getById(id);
    }

    // Nuevo endpoint para obtener todos los usuarios (solo para el administrador)
    @GetMapping("/getAllUsers")
    public List<ClientModel> getAllUsers() {
        return userService.getUsers();
    }


    // Endpoint de prueba para obtener información de un usuario por su id
    @GetMapping("/public/getUserData/{id}")
    public Optional<ClientModel> getUserDataPublic(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("updatePhoto/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            String fotoPerfilBase64 = payload.get("fotoPerfil");
            if (fotoPerfilBase64 == null || fotoPerfilBase64.isEmpty()) {
                return ResponseEntity.badRequest().body("La foto de perfil no puede estar vacía.");
            }

            boolean updated = userService.updatePhoto(id, fotoPerfilBase64);
            if (updated) {
                return ResponseEntity.ok("Foto de perfil actualizada con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la foto de perfil.");
        }
    }
}