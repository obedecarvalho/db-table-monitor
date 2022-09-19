package com.dbtablemonitor.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dbtablemonitor.model.Repository;
import com.dbtablemonitor.model.dao.ConfigurationDAO;
import com.dbtablemonitor.model.entity.Tabela;

public class TabelaRepository implements Repository<Tabela, Integer>{
	
	private static final String SQL_CREATE_TABLE = 
			" CREATE TABLE IF NOT EXISTS tabela (" +
			" 	id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" 	table_schema TEXT," +
			" 	table_name TEXT," +
			" 	column_primary_key TEXT" +
			" )";
	
	private static final String SQL_INSERT = "INSERT INTO tabela (table_schema, table_name, column_primary_key) VALUES (?, ?, ?)";
	
	private static final String SQL_UPDATE =
			" UPDATE tabela" +
			" SET table_schema = ?, table_name = ?, column_primary_key = ?" +
			" WHERE id = ?";
	
	private static final String SQL_DELETE_ID = "DELETE FROM tabela WHERE id IN (?)";
	
	private static final String SQL_DELETE_ALL = "DELETE FROM tabela";
	
	private static final String SQL_SELECT_ID = "SELECT id, table_schema, table_name, column_primary_key FROM tabela WHERE id IN (?)";
	
	private static final String SQL_SELECT_ALL = "SELECT id, table_schema, table_name, column_primary_key FROM tabela";
	
	private static TabelaRepository INSTANCE;
	
	private TabelaRepository() {

	}

	public static TabelaRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TabelaRepository();
			createTable();
		}
		return INSTANCE;
	}

	@Override
	public Tabela save(Tabela entity) {
		if (entity.getId() != null && entity.getId() > 0) {
			update(entity);
		} else {
			inserir(entity);
		}
		return null;
	}
	
	private void inserir(Tabela entity) {
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getTableSchema());
			statement.setString(2, entity.getTableName());
			statement.setString(3, entity.getColumnPrimaryKey());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				entity.setId((int) rs.getLong(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update(Tabela entity) {
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_UPDATE);
			statement.setString(1, entity.getTableSchema());
			statement.setString(2, entity.getTableName());
			statement.setString(3, entity.getColumnPrimaryKey());
			statement.setInt(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Tabela> saveAll(List<Tabela> entities) {
		for (Tabela tabela: entities) {
			save(tabela);
		}
		return null;
	}

	@Override
	public List<Tabela> findAll() {
		List<Tabela> tabelas = new ArrayList<Tabela>();
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = statement.executeQuery();

			Tabela t = null;
			
			while (rs.next()) {
				t = new Tabela();
				
				t.setId(rs.getInt("id"));
				t.setTableSchema(rs.getString("table_schema"));
				t.setTableName(rs.getString("table_name"));
				t.setColumnPrimaryKey(rs.getString("column_primary_key"));
				
				tabelas.add(t);
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tabelas;
	}

	@Override
	public Tabela findById(Integer id) {

		Tabela t = null;

		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_SELECT_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				t = new Tabela();

				t.setId(rs.getInt("id"));
				t.setTableSchema(rs.getString("table_schema"));
				t.setTableName(rs.getString("table_name"));
				t.setColumnPrimaryKey(rs.getString("column_primary_key"));

			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public void delete(Tabela entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Statement statement = ConfigurationDAO.getConnection().createStatement();
			statement.executeUpdate(SQL_DELETE_ID, id);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try {
			Statement statement = ConfigurationDAO.getConnection().createStatement();
			statement.executeUpdate(SQL_DELETE_ALL);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAllById(List<Integer> ids) {
		try {
			Statement statement = ConfigurationDAO.getConnection().createStatement();
			statement.executeUpdate(SQL_DELETE_ID, ids.stream().mapToInt(i -> i).toArray());
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll(List<Tabela> entities) {
		deleteAllById(entities.stream().map(Tabela::getId).collect(Collectors.toList()));
	}
	
	private static void createTable() {
		try {
			Statement statement = ConfigurationDAO.getConnection().createStatement();
			statement.executeUpdate(SQL_CREATE_TABLE);
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
