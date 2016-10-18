package br.com.saojudas.maven.projetointegrado.model;

public class ItemCadastroEmpresa {
	private Empresa empresa;
	private Conjunto conjunto;
	
	public ItemCadastroEmpresa(Empresa empresa, Conjunto conjunto) {
		setEmpresa(empresa);
		setConjunto(conjunto);
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Conjunto getConjunto() {
		return conjunto;
	}
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}
	
}
