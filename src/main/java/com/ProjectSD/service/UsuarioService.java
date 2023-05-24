/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjectSD.service;


import com.ProjectSD.model.Usuario;
import com.ProjectSD.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void update(Usuario usuario) {
        usuarioRepository.update(usuario);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
