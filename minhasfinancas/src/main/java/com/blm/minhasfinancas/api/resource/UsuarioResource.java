package com.blm.minhasfinancas.api.resource;


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blm.minhasfinancas.api.dto.UsuarioDTO;
import com.blm.minhasfinancas.exception.ErroAutenticacao;
import com.blm.minhasfinancas.exception.RegraNegocioException;
import com.blm.minhasfinancas.model.entily.Usuario;
import com.blm.minhasfinancas.service.LancamentoService;
import com.blm.minhasfinancas.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {
	
	private final UsuarioService service;
	private final LancamentoService lancamentoService;
	
	@PostMapping("/autenticar")
    public ResponseEntity autenticar (@RequestBody UsuarioDTO dto) {
		try {
		Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
		return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha()).build();
	    
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity<>(usuarioSalvo,HttpStatus.CREATED);	
		
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping ("{id}/saldo")
	public ResponseEntity obterSaldo(@PathVariable("id")long id) {
		Optional<Usuario> usuario = service.obterPorId(id);
		
		if(!usuario.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}
}



