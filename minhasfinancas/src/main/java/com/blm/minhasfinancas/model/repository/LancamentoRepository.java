package com.blm.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blm.minhasfinancas.model.entily.lancamentos;

public interface LancamentoRepository extends JpaRepository<lancamentos, Long> {

}
