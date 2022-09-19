package com.dbtablemonitor.model.entity;

import java.util.Date;
import java.util.Objects;

public class TabelaMonitorada {
	
	private Integer id;
	
	private Tabela tabela;
	
	private Date data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TabelaMonitorada [tabela=" + tabela + ", data=" + data + "]";
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
		TabelaMonitorada other = (TabelaMonitorada) obj;
		return Objects.equals(id, other.id);
	}

}
