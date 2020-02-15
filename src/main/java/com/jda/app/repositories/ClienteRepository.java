package com.jda.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jda.app.domain.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
