package com.blm.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.blm.minhasfinancas.model.entily.lancamentos;
import com.blm.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	lancamentos salvar(lancamentos lancamento);
	lancamentos atualizar(lancamentos lancamento);
	void deletar(lancamentos lancamento);
	List<lancamentos> buscar(lancamentos lancamentoFiltro);	
	void atualizarStatus(lancamentos lancamento, StatusLancamento status);
	void validar(lancamentos lancamento);
	

	Optional<lancamentos> obterPorId(long id);
	
	BigDecimal obterSaldoPorUsuario(long id);

}
