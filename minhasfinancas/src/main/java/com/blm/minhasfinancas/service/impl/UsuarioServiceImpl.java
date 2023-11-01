package com.blm.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blm.minhasfinancas.exception.ErroAutenticacao;
import com.blm.minhasfinancas.exception.RegraNegocioException;
import com.blm.minhasfinancas.model.entily.Usuario;
import com.blm.minhasfinancas.model.repository.UsuarioRepository;
import com.blm.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	private UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}
		
		return null;
	}
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}
	
	@Override
	public void validarEmail(String email) {
		// TODO Auto-generated method stub
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
