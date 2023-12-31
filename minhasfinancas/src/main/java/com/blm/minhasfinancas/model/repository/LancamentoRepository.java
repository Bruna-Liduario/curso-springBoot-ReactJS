package com.blm.minhasfinancas.model.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blm.minhasfinancas.model.entily.lancamentos;

public interface LancamentoRepository extends JpaRepository<lancamentos, Long> {
	
	@Query(value = 
			" select sum() from Lancamento l join l.usuario u "
			+ "where u.id = :idUsuario and l.tipo =:tipo group by u")
	BigDecimal obterSaldoPorTipoLancamentoEUsuario( @Param("idUsuario") Long idUsuario, @Param("tipo") String tipo);
	
}
