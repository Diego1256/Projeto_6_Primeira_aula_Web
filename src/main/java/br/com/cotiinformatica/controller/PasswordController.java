package br.com.cotiinformatica.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.messages.EmailMessage;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class PasswordController {

	// método para mapear a rota (endereço) da página de recuperação
	// de senha do usuário /WEB-INF/views/password.jsp
	@RequestMapping(value = "/password") // ROTA (URL)
	public ModelAndView password() {

		// WEB-INF/views/password.jsp
		ModelAndView modelAndView = new ModelAndView("password");
		return modelAndView;
	}

	// método para receber a requisição da página .jsp
	@RequestMapping(value = "/password-user", method = RequestMethod.POST)
	public ModelAndView passwordUser(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("password");
		
		try {
			
			//capturando o valor do campo email enviado pela página (formulário)
			String email = request.getParameter("email");
			
			//verificar se o email informado existe no banco de dados
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.findByEmail(email);
			
			//verificar se o usuário foi encontrado
			if(usuario != null) {

				//gerando uma nova senha para o usuário:
				Faker faker = new Faker(new Locale("pt-BR"));
				String novaSenha = faker.internet().password(8, 10);
				
				//escrevendo a mensagem
				String assunto = "Recuperação de senha acesso - Agenda de Contatos";
				String mensagem = "Olá " + usuario.getNome()
							   + "\n\nUma nova senha de acesso foi gerada com sucesso!"
							   + "\nUtilize a senha: " + novaSenha + " para acessar sua conta."
							   + "\n\nAtt"
							   + "\nEquipe Agenda de Contatos";
				
				//enviando esta senha para o email do usuário
				EmailMessage.send(usuario.getEmail(), assunto, mensagem);
				
				//atualizando a senha do usuário no banco de dados
				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);
				
				//gerandi mensagem de sucesso
				modelAndView.addObject("mensagem_sucesso", "Uma nova senha foi enviada para o seu email com sucesso!");
			}
			else {
				//gerando uma mensagem de erro
				modelAndView.addObject("mensagem_erro", "O email informado não foi encontrado. Tente outro.");
			}
		}
		catch(Exception e) {
			//gerando uma mensagem de erro
			modelAndView.addObject("mensagem_erro", "Falha ao recuperar senha: " + e.getMessage());
		}
		
		return modelAndView;		
	}

}


