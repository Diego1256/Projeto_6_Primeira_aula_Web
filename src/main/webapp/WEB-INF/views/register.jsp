<html>
<head>
	<title>Criar Conta</title>

	<!-- link para os arquivos de extensão .CSS -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css" />
	<style>
			label.error { color: #d9534f; }
			input.error { border: 2px solid #d9534f; }
		</style>
	
	
</head>
<body class="bg-secondary">

	<div class="row">
		<div class="col-md-4 offset-md-4">
			<div class="card mt-5 mb-5">
				<div class="card-body">
					<div class="text-center">
						<h2>Criar Conta de Usuário</h2>
						<p>Entre com seus dados para cadastrar sua conta:</p>
						<hr />
					</div>

					<form id="form_register" method="post" action="register-user">

						<div class="mb-3">
							<label>Informe seu nome:</label> <input name="nome" id="nome" type="text"
								placeholder="Digite aqui" class="form-control" />
						</div>

						<div class="mb-3">
							<label>Informe seu email de acesso:</label> <input name="email" id="email"
								type="text" placeholder="Digite aqui" class="form-control" />
								<span class="text-danger">
									${mensagem_email }
								</span>
						</div>

						<div class="mb-3">
							<label>Informe sua senha de acesso:</label> <input name="senha" id="senha"
								type="password" placeholder="Digite aqui" class="form-control" />
						</div>

						<div class="mb-3">
							<label>Confirme sua senha de acesso:</label> <input
								name="senhaConfirmacao" id="senhaConfirmacao" type="password"
								placeholder="Digite aqui" class="form-control" />
						</div>

						<div class="mb-3">

							<div class="d-grid">
								<input type="submit" value="Realizar Cadastro"
									class="btn btn-success" />
							</div>

							<div class="d-grid">
								<a href="/projetoweb01/" class="btn btn-light"> Já possui
									conta? <strong>Acesse aqui!</strong>
								</a>
							</div>

						</div>

					</form>
					
					<div class="text-sucess text-center">
					
						<strong>${mensagem_sucesso}</strong>
					
					</div>
					
					<div class="text-danger text-center">
					
						<strong>${mensagem_erro}</strong>
					
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- link para os arquivos de extensão JS -->
	<script src="resources/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
	<script src="resources/js/jquery-3.6.1.min.js" type="text/javascript"></script>
	<script src="resources/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="resources/js/messages_pt_BR.min.js" type="text/javascript"></script>
	<script src="additional-metbods.min.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#form_register").validate({
				rules : {
					"nome" : { required : true, minlength : 6, maxlength : 150 },

					"email" : {	required : true, email : true },

					"senha" : { required : true, minlength : 8, maxlength : 20	},
					
					"senhaConfirmacao" : {required: true, equalTo:"#senha"},

				}
			});
		});
	</script>

</body>
</html>

