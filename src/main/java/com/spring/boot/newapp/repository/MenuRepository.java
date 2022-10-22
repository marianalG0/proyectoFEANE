package com.spring.boot.newapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
