package com.blm.minhasfinancas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blm.minhasfinancas.exception.RegraNegocioException;
import com.blm.minhasfinancas.model.entily.lancamentos;
import com.blm.minhasfinancas.model.enums.StatusLancamento;
import com.blm.minhasfinancas.model.repository.LancamentoRepository;
import com.blm.minhasfinancas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private LancamentoRepository repository;
	
	public LancamentoServiceImpl(LancamentoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public lancamentos salvar(lancamentos lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Transactional
	@Override
	public lancamentos atualizar(lancamentos lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Transactional
	@Override
	public void deletar(lancamentos lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<lancamentos> buscar(lancamentos lancamentoFiltro) {
		Example example = Example.of(lancamentoFiltro, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
	
		return repository.findAll(example);
		
	}

	
	@Override
	public void atualizarStatus(lancamentos lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);		
	}

	@Override
	public void validar(lancamentos lancamento) {
		
	
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma Descrição válida");
		}		
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido");
		}		
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um Ano válido");
		}
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() != 0) {
			throw new RegraNegocioException("Informe um Ano válido");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO)< 1) {
			throw new RegraNegocioException("Informe um Valor válido");			
		}
		if(lancamento.getTipo() == null){
			throw new RegraNegocioException("Informe um Tipo de Lançamento");			
		}
	}

	
}
