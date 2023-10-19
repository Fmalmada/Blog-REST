package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.modelo.Entrada;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {

}
