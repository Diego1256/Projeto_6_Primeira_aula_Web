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

	// método para atualizar um contato no banco de dados
	public void update(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		// executar o comando SQL
		PreparedStatement statement = connection
				.prepareStatement("update contato set nome=?, email=?, telefone=? where idcontato=?");
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEmail());
		statement.setString(3, contato.getTelefone());
		statement.setInt(4, contato.getIdContato());
		statement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para excluir um contato no banco de dados
	public void delete(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		// executar o comando SQL
		PreparedStatement statement = connection.prepareStatement("delete from contato where idcontato=?");
		statement.setInt(1, contato.getIdContato());
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

		// declarando uma lista de contatos
		List<Contato> lista = new ArrayList<Contato>();

		// percorrer os dados obtidos do banco
		while (resultSet.next()) {

			Contato contato = new Contato();

			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));

			lista.add(contato); // adicionar o contato na lista
		}

		// fechando a conexão com o banco de dados
		connection.close();

		// retornando a lista
		return lista;
	}

	// método para consultar 1 contato de um usuário através do id
	public Contato findById(Integer idContato, Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		// executando um comando SQL
		PreparedStatement statement = connection
				.prepareStatement("select * from contato where idcontato=? and idusuario=?");
		statement.setInt(1, idContato);
		statement.setInt(2, idUsuario);
		ResultSet resultSet = statement.executeQuery();

		Contato contato = null;

		// verificar se algum registro foi encontrado
		if (resultSet.next()) {

			contato = new Contato();

			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
		}

		// fechando conexão com o banco de dados
		connection.close();
		return contato;
	}
}



