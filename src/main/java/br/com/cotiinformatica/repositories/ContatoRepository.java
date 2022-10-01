package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContatoRepository {

	// método para gravar um contato no banco de dados
	public void create(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		// executar o comando SQL
		PreparedStatement statement = connection
				.prepareStatement("insert into contato(nome, email, telefone, idusuario) values(?,?,?,?)");
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEmail());
		statement.setString(3, contato.getTelefone());
		statement.setInt(4, contato.getUsuario().getIdUsuario());
		statement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para consultar os contatos de um usuário
	public List<Contato> findByUsuario(Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		// executar o comando SQL
		PreparedStatement statement = connection.prepareStatement("select * from contato where idusuario = ?");
		statement.setInt(1, idUsuario);
		ResultSet resultSet = statement.executeQuery();
		
		//declarando uma lista de contatos
		List<Contato> lista = new ArrayList<Contato>();
		
		//percorrer os dados obtidos do banco
		while(resultSet.next()) {
			
			Contato contato = new Contato();
			
			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
			
			lista.add(contato); //adicionar o contato na lista
		}
		
		//fechando a conexão com o banco de dados
		connection.close();
		
		//retornando a lista
		return lista;
	}

}



