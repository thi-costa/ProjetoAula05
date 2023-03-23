package com.example.ProjetoAula05.repository;

import com.example.ProjetoAula05.model.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Long> {
}
