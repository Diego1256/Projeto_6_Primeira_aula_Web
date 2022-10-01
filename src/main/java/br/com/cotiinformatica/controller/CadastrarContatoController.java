package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContatoRepository;

@Controller
public class CadastrarContatoController {

	@RequestMapping(value = "/admin/cadastrar-contato")
	public ModelAndView cadastrarContato() {

		// WEB-INF/views/admin/cadastrar-contato
		ModelAndView modelAndView = new ModelAndView("admin/cadastrar-contato");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/executar-cadastro-contato", method = RequestMethod.POST)
	public ModelAndView executarCadastroContato(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/cadastrar-contato");
		
		try {
			
			Contato contato = new Contato();
			
			contato.setNome(request.getParameter("nome"));
			contato.setEmail(request.getParameter("email"));
			contato.setTelefone(request.getParameter("telefone"));
			contato.setUsuario((Usuario) request.getSession().getAttribute("usuario_auth"));
			
			//gravar o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			contatoRepository.create(contato);
			
			modelAndView.addObject("mensagem_sucesso", "Parabéns! Contato " + contato.getNome() + ", cadastrado com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao cadastrar contato: " + e.getMessage());
		}
		
		return modelAndView;		
	}

}



