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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "cnpj", length = 20, unique = true, nullable = false)
	private String cnpj;

	@Column(name = "razaoSocial", length = 100, nullable = false)
	private String razaoSocial;

	@Column(name = "horarioDeFuncionamento", length = 20, nullable = false)
	private String horarioDeFuncionamento;

	@Column(name = "temperaturaMaximaArCondicionado", length = 20, nullable = false)
	private int temperaturaMaximaArCondicionado;

	@Column(name = "statusUsuario", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "empresa_conjunto")
	private List<Conjunto> conjuntos;

	@OneToMany(mappedBy = "empresa_usuario")
	private List<Usuario> usuarios;

	public Empresa() {

	}

	public Empresa(String cnpj, String razaoSocial, String horarioDeFuncionamento, int temperaturaMaximaArCondicionado,
			Status status) {
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setHorarioDeFuncionamento(horarioDeFuncionamento);
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
		if (razaoSocial == null) {
			throw new RuntimeException("A razão nao pode ser nulo!");
		} else if (razaoSocial.trim().equals("")) {
			throw new RuntimeException("A razão nao pode ser vazio!");
		} else {
			this.razaoSocial = razaoSocial;
		}
	}

	public String getHorarioDeFuncionamento() {
		return horarioDeFuncionamento;
	}

	public void setHorarioDeFuncionamento(String horarioDeFuncionamento) {
		this.horarioDeFuncionamento = horarioDeFuncionamento;
		if (horarioDeFuncionamento == null) {
			throw new RuntimeException("O horario de funcionamento nao pode ser nulo!");
		} else if (razaoSocial.trim().equals("")) {
			throw new RuntimeException("O horario de funcionamento nao pode ser vazio!");
		} else {
			this.horarioDeFuncionamento = horarioDeFuncionamento;
		}
	}

	public List<Conjunto> getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(List<Conjunto> conjuntos) {
		this.conjuntos = conjuntos;
	}

	public void addConjunto(Conjunto conjunto) {
		conjuntos.add(conjunto);
	}

	public int getTemperaturaMaximaArCondicionado() {
		return temperaturaMaximaArCondicionado;
	}

	public void setTemperaturaMaximaArCondicionado(int temperaturaMaximaArCondicionado) {
		if (temperaturaMaximaArCondicionado < 0) {
			throw new RuntimeException("O nome nao pode ser vazio!");
		} else {
			this.temperaturaMaximaArCondicionado = temperaturaMaximaArCondicionado;
		}
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String toString() {
		return getRazaoSocial();
	}
}
