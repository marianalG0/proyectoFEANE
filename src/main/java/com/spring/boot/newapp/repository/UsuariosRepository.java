package com.spring.boot.newapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username); // va buscar el nombre del usuario
}
