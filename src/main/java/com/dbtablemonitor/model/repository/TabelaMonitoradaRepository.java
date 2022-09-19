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
import com.dbtablemonitor.model.dao.DAOUtil;
import com.dbtablemonitor.model.entity.Tabela;
import com.dbtablemonitor.model.entity.TabelaMonitorada;

public class TabelaMonitoradaRepository implements Repository<TabelaMonitorada, Integer>{
	
	private static TabelaMonitoradaRepository INSTANCE;
	
	private static final String SQL_CREATE_TABLE =
			" CREATE TABLE IF NOT EXISTS tabela_monitorada (" +
			" 	id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" 	id_tabela INTEGER," +
			" 	data INTEGER," +
			" 	FOREIGN KEY(id_tabela) REFERENCES tabela(id)" +
			" )";
	
	private static final String SQL_INSERT = "INSERT INTO tabela_monitorada (id_tabela, data) VALUES (?, ?)";
	
	private static final String SQL_UPDATE =
			" UPDATE tabela_monitorada" +
			" SET id_tabela = ?, data = ?" +
			" WHERE id = ?";
	
	
	private static final String SQL_DELETE_ID = "DELETE FROM tabela_monitorada WHERE id IN (?)";
	
	private static final String SQL_DELETE_ALL = "DELETE FROM tabela_monitorada";
	
	private static final String SQL_SELECT_ID = "SELECT id, id_tabela, data FROM tabela_monitorada WHERE id IN (?)";
	
	private static final String SQL_SELECT_ALL = "SELECT id, id_tabela, data FROM tabela_monitorada";
	
	private TabelaMonitoradaRepository() {
		
	}
	
	public static TabelaMonitoradaRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TabelaMonitoradaRepository();
			createTable();
		}
		return INSTANCE;
	}

	@Override
	public TabelaMonitorada save(TabelaMonitorada entity) {

		if (entity.getId() != null && entity.getId() > 0) {
			update(entity);
		} else {
			inserir(entity);
		}

		return null;
	}

	@Override
	public List<TabelaMonitorada> saveAll(List<TabelaMonitorada> entities) {
		for (TabelaMonitorada tabelaMonitorada : entities) {
			save(tabelaMonitorada);
		}
		return null;
	}
	
	private void inserir(TabelaMonitorada entity) {
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, entity.getTabela().getId());
			statement.setLong(2, DAOUtil.dateToUnixEpoch(entity.getData()));
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				entity.setId((int) rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update(TabelaMonitorada entity) {
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_UPDATE);
			statement.setInt(1, entity.getTabela().getId());
			statement.setLong(2, DAOUtil.dateToUnixEpoch(entity.getData()));
			statement.setInt(3, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TabelaMonitorada> findAll() {
		List<TabelaMonitorada> tabelas = new ArrayList<TabelaMonitorada>();
		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = statement.executeQuery();

			TabelaMonitorada t = null;
			
			while (rs.next()) {
				t = new TabelaMonitorada();
				t.setTabela(new Tabela());
				
				t.setId(rs.getInt("id"));
				t.getTabela().setId(rs.getInt("id_tabela"));
				t.setData(DAOUtil.unixEpochtoDate(rs.getLong("data")));
				
				tabelas.add(t);
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tabelas;
	}

	@Override
	public TabelaMonitorada findById(Integer id) {
		TabelaMonitorada t = null;

		try {
			PreparedStatement statement = ConfigurationDAO.getConnection().prepareStatement(SQL_SELECT_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				t = new TabelaMonitorada();
				t.setTabela(new Tabela());

				t.setId(rs.getInt("id"));
				t.getTabela().setId(rs.getInt("id_tabela"));
				t.setData(DAOUtil.unixEpochtoDate(rs.getLong("data")));

			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public void delete(TabelaMonitorada entity) {
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
	public void deleteAll(List<TabelaMonitorada> entities) {
		deleteAllById(entities.stream().map(TabelaMonitorada::getId).collect(Collectors.toList()));	
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
