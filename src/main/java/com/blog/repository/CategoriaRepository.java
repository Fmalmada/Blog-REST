package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}