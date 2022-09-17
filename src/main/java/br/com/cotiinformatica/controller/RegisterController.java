package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RegisterController {

	//método para mapear a página que será exibida por este controlador
	@RequestMapping(value = "/register") //ROTA (endereço) da página
	public ModelAndView register() {
		
		//Definir o nome da página que o controlador deverá exibir na rota
		//esta página deve estar dentro de /WEB-INF/views e ter extensão .jsp
		
		//WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}
	
	//método para receber o SUBMIT POST do formulário
	@RequestMapping(value = "/register-user", method = RequestMethod.POST) 
	public ModelAndView registerUser(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("register");
		
		try {
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			//verificar se já existe um usuário cadastrado com o email informado
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {				
				//exibindo mensagem de erro na página
				modelAndView.addObject("mensagem_email", "Erro! O email informado já está cadastrado, tente outro.");
			}
			else {				
				//cadastrando no banco de dados
				usuarioRepository.create(usuario);				
				//exibindo mensagem de sucesso na página
				modelAndView.addObject("mensagem_sucesso", "Parabéns, sua conta foi criada com sucesso.");
			}			
		}
		catch(Exception e) {
			
			//exibindo mensagem de erro na página
			modelAndView.addObject("mensagem_erro", "Erro: " + e.getMessage());
		}		
		
		return modelAndView;
	}
	
}

