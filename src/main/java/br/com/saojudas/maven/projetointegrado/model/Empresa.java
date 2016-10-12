package br.com.saojudas.maven.projetointegrado.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cnpj", length = 20, unique = true, nullable = false)
	private String cnpj;
	
	@Column(name = "razaoSocial", length = 100, nullable = false)
	private String razaoSocial;
	
	@Column(name = "horarioDeFuncionamento", length = 20, nullable = false)
	private String horarioDeFuncionamento;
	
	@OneToMany(mappedBy= "empresa")
	private List<Conjunto> conjuntos;
	
	@Column(name = "temperaturaMaximaArCondicionado", length = 20, nullable = false)
	private Double temperaturaMaximaArCondicionado;
	
	@Column(name = "statusUsuario", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	public Empresa(String cnpj, String razaoSocial, String horarioDeFuncionamento, Conjunto conjunto,
			Double temperaturaMaximaArCondicionado, Status status) {
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setHorarioDeFuncionamento(horarioDeFuncionamento);
		addConjunto(conjunto);
		setTemperaturaMaximaArCondicionado(temperaturaMaximaArCondicionado);
		setStatusEmpresa(status);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getHorarioDeFuncionamento() {
		return horarioDeFuncionamento;
	}

	public void setHorarioDeFuncionamento(String horarioDeFuncionamento) {
		this.horarioDeFuncionamento = horarioDeFuncionamento;
	}
	
	public List<Conjunto> getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(List<Conjunto> conjuntos) {
		this.conjuntos = conjuntos;
	}
	
	public void addConjunto(Conjunto conjunto)
	{
		conjuntos.add(conjunto);
	}

	public Double getTemperaturaMaximaArCondicionado() {
		return temperaturaMaximaArCondicionado;
	}

	public void setTemperaturaMaximaArCondicionado(Double temperaturaMaximaArCondicionado) {
		this.temperaturaMaximaArCondicionado = temperaturaMaximaArCondicionado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatusEmpresa(Status status) {
		this.status = status;
	}
}
