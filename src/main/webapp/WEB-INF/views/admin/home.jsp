<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>

<meta charset="ISO-8859-1">

<title>Acessar Conta</title>

<!-- link para os arquivos de extensão .CSS -->
<link rel="stylesheet" href="../resources/css/bootstrap.min.css"
	type="text/css" />

</head>
<body>

	<!-- barra de menu superior do sistema -->
	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="container mt-4">

		<h3>Seja bem vindo a agenda de contatos</h3>
		<hr />

		<div id="grafico"></div>

	</div>

	<!-- link para os arquivos de extensão JS -->
	<script src="../resources/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>

	<!-- referencias para o highcharts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-3d.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>

	<script>
		var array = [];

		array.push([ 'JAN/2022', 10 ]);
		array.push([ 'FEV/2022', 12 ]);
		array.push([ 'MAR/2022', 4 ]);
		array.push([ 'ABR/2022', 20 ]);
		array.push([ 'MAI/2022', 2 ]);
		array.push([ 'JUN/2022', 6 ]);
		array.push([ 'JUL/2022', 4 ]);
		array.push([ 'AGO/2022', 12 ]);
		array.push([ 'SET/2022', 8 ]);
		array.push([ 'OUT/2022', 10 ]);

		new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'grafico'
			},
			title : {
				text : 'Quantidade de contatos cadastrados por mês'
			},
			credits : {
				enabled : false
			},
			series : [ {
				data : array
			} ]
		});
	</script>

</body>
</html>