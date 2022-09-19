package com.dbtablemonitor.model.entity;

import java.util.Objects;

public class Tabela {
	
	private Integer id;
	
	private String tableSchema;
	
	private String tableName;
	
	private String columnPrimaryKey;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnPrimaryKey() {
		return columnPrimaryKey;
	}

	public void setColumnPrimaryKey(String columnPrimaryKey) {
		this.columnPrimaryKey = columnPrimaryKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tabela other = (Tabela) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tabela [tableSchema=" + tableSchema + ", tableName=" + tableName + ", columnPrimaryKey="
				+ columnPrimaryKey + "]";
	}

}
