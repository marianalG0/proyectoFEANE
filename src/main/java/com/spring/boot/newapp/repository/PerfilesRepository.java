package com.spring.boot.newapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Perfil;

public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {

}
