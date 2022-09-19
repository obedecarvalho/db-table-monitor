package com.dbtablemonitor.model.dto;

import java.util.Objects;

public class PrimaryKeyDTO {
	
	private String tableSchema;

	private String tableName;

	private String constraintType;

	private String constraintName;

	private String columnName;

	private Integer ordinalPosition;

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

	public String getConstraintType() {
		return constraintType;
	}

	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
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

	@Override
	public int hashCode() {
		return Objects.hash(columnName, constraintName, constraintType, ordinalPosition, tableName, tableSchema);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimaryKeyDTO other = (PrimaryKeyDTO) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(constraintName, other.constraintName)
				&& Objects.equals(constraintType, other.constraintType)
				&& Objects.equals(ordinalPosition, other.ordinalPosition) && Objects.equals(tableName, other.tableName)
				&& Objects.equals(tableSchema, other.tableSchema);
	}

	@Override
	public String toString() {
		return "PrimaryKeyDTO [tableSchema=" + tableSchema + ", tableName=" + tableName + ", constraintType="
				+ constraintType + ", constraintName=" + constraintName + ", columnName=" + columnName
				+ ", ordinalPosition=" + ordinalPosition + "]";
	}

}
