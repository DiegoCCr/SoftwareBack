package com.crud.CRUD.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.CRUD.models.ClientModel;
import com.crud.CRUD.repositories.IClientRepository;

@Service
public class ClientServices {

    @Autowired
    IClientRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ClientModel> getUsers() {
        return userRepository.findAll();
    }

    public ClientModel saveUser(ClientModel user) {
        // Validar si el correo ya está registrado
        if (findByEmail(user.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }
    
        // Establecer rol predeterminado si no se especifica
        if (user.getRol() == null) {
            user.setRol(1);
        }
    
        // Encriptar la contraseña antes de guardarla
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
    
        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }      

    public Optional<ClientModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public ClientModel updateById(ClientModel request, Long id) {
        ClientModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNombre(request.getNombre());
            user.setCorreo(request.getCorreo());
            // Encriptar la contraseña antes de actualizar
            user.setContrasena(passwordEncoder.encode(request.getContrasena()));
            userRepository.save(user);
        }
        return user;
    }

    public Boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Nuevo método para encontrar usuario por correo
    public Optional<ClientModel> findByEmail(String correo) {
        return userRepository.findByCorreo(correo);
    }

    // Método para eliminar usuario por correo
    public Boolean deleteUserByEmail(String correo) {
        Optional<ClientModel> user = findByEmail(correo);
        if (user.isPresent()) {
            try {
                userRepository.delete(user.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public Optional<ClientModel> findByEmailAndPassword(String correo, String contrasena) {
        Optional<ClientModel> user = userRepository.findByCorreo(correo);
        if (user.isPresent() && passwordEncoder.matches(contrasena, user.get().getContrasena())) {
            return user;
        }
        return Optional.empty();
    }

    // Método para actualizar usuario por correo
    public ClientModel updateUser(ClientModel user) {
        Optional<ClientModel> existingUser = userRepository.findByCorreo(user.getCorreo());
    
        if (existingUser.isPresent()) {
            ClientModel userToUpdate = existingUser.get();
            userToUpdate.setNombre(user.getNombre());
            userToUpdate.setApellidoPaterno(user.getApellidoPaterno());
            userToUpdate.setApellidoMaterno(user.getApellidoMaterno());
            userToUpdate.setContrasena(passwordEncoder.encode(user.getContrasena())); // Encriptar contraseña
            userToUpdate.setRol(user.getRol());
    
            if (user.getFotoPerfil() != null) {
                userToUpdate.setFotoPerfil(user.getFotoPerfil()); // Actualizar foto de perfil
            }
    
            return userRepository.save(userToUpdate); // Guardar el usuario actualizado
        } else {
            throw new RuntimeException("Usuario no encontrado con el correo: " + user.getCorreo());
        }
    }            

    public boolean updatePhoto(Long userId, String fotoPerfilBase64) {
        Optional<ClientModel> optionalUser = userRepository.findById(userId);
        System.out.println("(Si entro al servicio)");
    
        if (optionalUser.isPresent()) {
            System.out.println("(Si encontro al usuario)");
            ClientModel user = optionalUser.get();
    
            // Decodificar la imagen Base64 a byte[]
            byte[] fotoPerfilBytes = Base64.getDecoder().decode(fotoPerfilBase64);
            user.setFotoPerfil(fotoPerfilBytes);
    
            // Guardar el usuario actualizado
            userRepository.save(user);
            return true;
        }
    
        return false; // Usuario no encontrado
    }    
}