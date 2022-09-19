package com.dbtablemonitor.model.dto;

import java.util.Objects;

public class ColumnDTO {
	
	private String tableSchema;
	
	private String tableName;
	
	private String columnName;
	
	private Integer ordinalPosition;
	
	private String dataType;
	
	private Boolean isNullable;

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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(Boolean isNullable) {
		this.isNullable = isNullable;
	}

	@Override
	public String toString() {
		return "ColumnDTO [tableSchema=" + tableSchema + ", tableName=" + tableName + ", columnName=" + columnName
				+ ", ordinalPosition=" + ordinalPosition + ", dataType=" + dataType + ", isNullable=" + isNullable
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnName, dataType, isNullable, ordinalPosition, tableName, tableSchema);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnDTO other = (ColumnDTO) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(dataType, other.dataType)
				&& Objects.equals(isNullable, other.isNullable)
				&& Objects.equals(ordinalPosition, other.ordinalPosition) && Objects.equals(tableName, other.tableName)
				&& Objects.equals(tableSchema, other.tableSchema);
	}

}
