package com.dbtablemonitor.model.dto;

import java.util.Objects;

public class TableDTO {
	
	private String tableSchema; 
	
	private String tableName;

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

	@Override
	public String toString() {
		return "TableDTO [tableSchema=" + tableSchema + ", tableName=" + tableName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tableName, tableSchema);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableDTO other = (TableDTO) obj;
		return Objects.equals(tableName, other.tableName) && Objects.equals(tableSchema, other.tableSchema);
	}

}
