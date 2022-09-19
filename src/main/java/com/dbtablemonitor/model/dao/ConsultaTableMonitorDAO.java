package com.dbtablemonitor.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbtablemonitor.model.dto.ColumnDTO;
import com.dbtablemonitor.model.dto.PrimaryKeyDTO;
import com.dbtablemonitor.model.dto.TableDTO;

public class ConsultaTableMonitorDAO {
	
	//Tabelas
	private static final String SQL_TABLES =
		" select table_schema, table_name" +
		" from information_schema.tables" +
		" where table_schema not in ('pg_catalog', 'information_schema')"
	;

	//PK
	private static final String SQL_PRIMARY_KEYS =
		" select tc.table_schema, tc.table_name, tc.constraint_type, tc.constraint_name, kcu.column_name, kcu.ordinal_position" +
		" from information_schema.table_constraints tc" +
		" inner join information_schema.key_column_usage kcu on kcu.constraint_name = tc.constraint_name and kcu.constraint_schema = tc.constraint_schema" +
		" where tc.table_schema not in ('pg_catalog', 'information_schema')" +
		" 	and tc.constraint_type = 'PRIMARY KEY'"
	;
	
	private static final String SQL_PRIMARY_KEYS_TABLE =
			" select tc.table_schema, tc.table_name, tc.constraint_type, tc.constraint_name, kcu.column_name, kcu.ordinal_position" +
			" from information_schema.table_constraints tc" +
			" inner join information_schema.key_column_usage kcu on kcu.constraint_name = tc.constraint_name and kcu.constraint_schema = tc.constraint_schema" +
			" where tc.table_schema not in ('pg_catalog', 'information_schema')" +
			" 	and tc.constraint_type = 'PRIMARY KEY'" +
			"	and tc.table_schema = ?" +
			"	and tc.table_name = ?"
		;

	//colunas
	private static final String SQL_COLUMNS =
		" select table_schema, table_name, column_name, ordinal_position, data_type, is_nullable" +
		" from information_schema.columns" +
		" where table_schema not in ('pg_catalog', 'information_schema')"
	;
	
	private static final String SQL_COLUMNS_TABLE =
		" select table_schema, table_name, column_name, ordinal_position, data_type, is_nullable" +
		" from information_schema.columns" +
		" where table_schema not in ('pg_catalog', 'information_schema')" +
		"	and table_schema = ?" +
		"	and table_name = ?"
	;
	
	private static ConsultaTableMonitorDAO INSTANCE;
	
	private ConsultaTableMonitorDAO() {

	}
	
	public static ConsultaTableMonitorDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConsultaTableMonitorDAO();
		}
		return INSTANCE;
	}

	public List<TableDTO> findTables() {
		List<TableDTO> dtos = new ArrayList<TableDTO>();
		
		try {
			PreparedStatement pStatement = TableMonitorDAO.getConnection().prepareStatement(SQL_TABLES);
			
			ResultSet rs = pStatement.executeQuery();

			TableDTO dto = null;
			
			while(rs.next()) {
				
				dto = new TableDTO();
				
				dto.setTableSchema(rs.getString("table_schema"));
				dto.setTableName(rs.getString("table_name"));
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos;
	}
	
	public List<ColumnDTO> findColumns() {
		List<ColumnDTO> dtos = new ArrayList<ColumnDTO>();
		
		try {
			PreparedStatement pStatement = TableMonitorDAO.getConnection().prepareStatement(SQL_COLUMNS);
			
			ResultSet rs = pStatement.executeQuery();

			ColumnDTO dto = null;
			
			while(rs.next()) {
				
				dto = new ColumnDTO();
				
				dto.setTableSchema(rs.getString("table_schema"));
				dto.setTableName(rs.getString("table_name"));
				dto.setColumnName(rs.getString("column_name"));
				dto.setOrdinalPosition(rs.getInt("ordinal_position"));
				dto.setDataType(rs.getString("data_type"));
				dto.setIsNullable(rs.getBoolean("is_nullable"));
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos;
	}
	
	public List<ColumnDTO> findColumnsBy(String tableSchema, String tableName) {
		List<ColumnDTO> dtos = new ArrayList<ColumnDTO>();
		
		try {
			PreparedStatement pStatement = TableMonitorDAO.getConnection().prepareStatement(SQL_COLUMNS_TABLE);
			
			pStatement.setString(1, tableSchema);
			pStatement.setString(2, tableName);
			
			ResultSet rs = pStatement.executeQuery();

			ColumnDTO dto = null;
			
			while(rs.next()) {
				
				dto = new ColumnDTO();
				
				dto.setTableSchema(rs.getString("table_schema"));
				dto.setTableName(rs.getString("table_name"));
				dto.setColumnName(rs.getString("column_name"));
				dto.setOrdinalPosition(rs.getInt("ordinal_position"));
				dto.setDataType(rs.getString("data_type"));
				dto.setIsNullable(rs.getBoolean("is_nullable"));
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos;
	}
	
	public List<PrimaryKeyDTO> findPrimaryKeys() {
		List<PrimaryKeyDTO> dtos = new ArrayList<PrimaryKeyDTO>();
		
		try {
			PreparedStatement pStatement = TableMonitorDAO.getConnection().prepareStatement(SQL_PRIMARY_KEYS);
			
			ResultSet rs = pStatement.executeQuery();

			PrimaryKeyDTO dto = null;
			
			while(rs.next()) {
				
				dto = new PrimaryKeyDTO();
				
				dto.setTableSchema(rs.getString("table_schema"));
				dto.setTableName(rs.getString("table_name"));
				dto.setConstraintType(rs.getString("constraint_type"));
				dto.setConstraintName(rs.getString("constraint_name"));
				dto.setColumnName(rs.getString("column_name"));
				dto.setOrdinalPosition(rs.getInt("ordinal_position"));
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos;
	}

	public List<PrimaryKeyDTO> findPrimaryKeysBy(String tableSchema, String tableName) {
		List<PrimaryKeyDTO> dtos = new ArrayList<PrimaryKeyDTO>();
		
		try {
			PreparedStatement pStatement = TableMonitorDAO.getConnection().prepareStatement(SQL_PRIMARY_KEYS_TABLE);
			
			pStatement.setString(1, tableSchema);
			pStatement.setString(2, tableName);
			
			ResultSet rs = pStatement.executeQuery();

			PrimaryKeyDTO dto = null;
			
			while(rs.next()) {
				
				dto = new PrimaryKeyDTO();
				
				dto.setTableSchema(rs.getString("table_schema"));
				dto.setTableName(rs.getString("table_name"));
				dto.setConstraintType(rs.getString("constraint_type"));
				dto.setConstraintName(rs.getString("constraint_name"));
				dto.setColumnName(rs.getString("column_name"));
				dto.setOrdinalPosition(rs.getInt("ordinal_position"));
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos;
	}
}
