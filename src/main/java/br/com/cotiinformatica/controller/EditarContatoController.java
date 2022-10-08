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
public class EditarContatoController {

	@RequestMapping(value = "/admin/editar-contato")
	public ModelAndView editarContato(HttpServletRequest request) {

		// WEB-INF/views/admin/editar-contato.jsp
		ModelAndView modelAndView = new ModelAndView("admin/editar-contato");

		try {

			// capturar o usuário autenticado no sistema (sessão)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");

			// capturar o id do contato enviado pela URL
			Integer idContato = Integer.parseInt(request.getParameter("idContato"));

			// consultar o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(idContato, usuario.getIdUsuario());

			// verificar se o contato foi encontrado
			if (contato != null) {

				// enviando os dados do contato para a página exibir
				modelAndView.addObject("contato", contato);
			} else {
				// redirecionar de volta para a página de consulta
				modelAndView.setViewName("redirect:/admin/consultar-contato");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao obter contato: " + e.getMessage());
		}

		return modelAndView;
	}

	// método para capturar o SUBMIT POST do formulário
	@RequestMapping(value = "/admin/executar-edicao-contato", method = RequestMethod.POST)
	public ModelAndView executarEdicaoContato(HttpServletRequest request) {

		// WEB-INF/views/admin/editar-contato.jsp
		ModelAndView modelAndView = new ModelAndView("admin/editar-contato");

		try {
			
			//capturando os dados do contato enviados pelo formulário
			Contato contato = new Contato();
			
			contato.setIdContato(Integer.parseInt(request.getParameter("idcontato")));
			contato.setNome(request.getParameter("nome"));
			contato.setTelefone(request.getParameter("telefone"));
			contato.setEmail(request.getParameter("email"));
			
			//atualizando o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			contatoRepository.update(contato);
			
			modelAndView.addObject("mensagem_sucesso", "Contato atualizado com sucesso!");
			modelAndView.addObject("contato", contato);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao atualizar contato: " + e.getMessage());
		}
		
		return modelAndView;

	}

}



