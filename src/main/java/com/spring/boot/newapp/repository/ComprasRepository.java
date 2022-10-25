package com.spring.boot.newapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Compras;

public interface ComprasRepository extends JpaRepository<Compras, Integer> {

}
