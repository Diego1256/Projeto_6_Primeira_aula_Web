package br.com.cotiinformatica.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Usuario {
	private Integer IdUsuario;
	private String nome;
	private String senha;
	private String email;
	private List<Contato> contatos;
	

}
