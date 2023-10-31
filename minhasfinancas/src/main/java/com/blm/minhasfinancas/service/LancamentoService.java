package com.blm.minhasfinancas.service;

import java.util.List;

import com.blm.minhasfinancas.model.entily.lancamentos;
import com.blm.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	lancamentos salvar(lancamentos lancamento);
	lancamentos atualizar(lancamentos lancamento);
	void deletar(lancamentos lancamento);
	List<lancamentos> buscar(lancamentos lancamentoFiltro);	
	void atualizarStatus(lancamentos lancamento, StatusLancamento status);
	void validar(lancamentos lancamento);

}
