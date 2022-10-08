<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<meta charset="UTF-8">

<title>Consultar Contato</title>

<!-- link para os arquivos de extensão .CSS -->
<link rel="stylesheet" href="../resources/css/bootstrap.min.css"
	type="text/css" />
	
<link rel="stylesheet" href="../resources/css/jquery.dataTables.min.css"
	type="text/css"/>

</head>
<body>

	<!-- barra de menu superior do sistema -->
	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="container mt-4">

		<h3>Consulta de Contato</h3>
		<p>Listagem de contatos cadastrados.</p>
		<hr />
		
		<div class="text-success mb-3">
			<strong>${mensagem_sucesso}</strong>
		</div>
		
		<div class="text-danger mb-3">
			<strong>${mensagem_erro}</strong>
		</div>
		
		<table id="tabela-contatos" class="table table-hover table-sm">
			
			<thead>
				<tr>
					<th>Nome do contato</th>
					<th>Email</th>
					<th>Telefone</th>
					<th>Operações</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${lista_contatos}" var="contato">
					
					<tr>
						<td>${contato.nome}</td>
						<td>${contato.email}</td>
						<td>${contato.telefone}</td>
						<td>
					
							<a href="/projetoweb01/admin/editar-contato?idContato=${contato.idContato}" 
								class="btn btn-primary btn-sm">
								Editar
							</a>
							
							<a href="/projetoweb01/admin/excluir-contato?idContato=${contato.idContato}"
								onclick="return confirm('Deseja realmente excluir o contato ${contato.nome}?');" 
								class="btn btn-danger btn-sm">
								Excluir
							</a>
					
						</td>
					</tr>
					
				</c:forEach>			
				
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan="4">
						Quantidade de contatos: ${lista_contatos.size()}
					</td>
				</tr>
			</tfoot>
			
		</table>

	</div>

	<!-- link para os arquivos de extensão JS -->
	<script src="../resources/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
		
	<script src="../resources/js/jquery-3.6.1.min.js"
		type="text/javascript"></script>
		
	<script src="../resources/js/jquery.dataTables.min.js"
		type="text/javascript"></script>
		
	<script>
		$(document).ready( function () {
	    	$('#tabela-contatos').DataTable({
	    		language: {
	    			url : "//cdn.datatables.net/plug-ins/1.12.1/i18n/pt-BR.json"
	    		}
	    	});
		});	
	</script>	

</body>
</html>




