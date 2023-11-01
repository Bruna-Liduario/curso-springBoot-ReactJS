package com.blm.minhasfinancas.service;

import java.util.Optional;

import com.blm.minhasfinancas.model.entily.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);
	
}
