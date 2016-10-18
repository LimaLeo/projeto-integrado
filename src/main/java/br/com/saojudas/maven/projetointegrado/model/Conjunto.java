package br.com.saojudas.maven.projetointegrado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Conjunto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_conjunto;

	@Column(name = "bloco", length = 100, nullable = false)
	private String bloco;

	@Column(name = "temperaturaMedia", nullable = false)
	private int temperaturaMedia;

	@Column(name = "statusConjunto", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	private Empresa empresa_conjunto;

	public Conjunto() {
		
	}

	public Conjunto(String bloco, int temperaturaMedia, Status status,Empresa empresa_conjunto) {
		setBloco(bloco);
		setTemperaturaMedia(temperaturaMedia);
		setStatus(status);
		setEmpresa_conjunto(empresa_conjunto);
	}
	
	public Conjunto(String bloco, int temperaturaMedia, Status status) {
		setBloco(bloco);
		setTemperaturaMedia(temperaturaMedia);
		setStatus(status);
	}

	public Integer getId_conjunto() {
		return id_conjunto;
	}

	public void setId_conjunto(Integer id_conjunto) {
		this.id_conjunto = id_conjunto;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getTemperaturaMedia() {
		return temperaturaMedia;
	}

	public void setTemperaturaMedia(int temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Empresa getEmpresa_conjunto() {
		return empresa_conjunto;
	}

	public void setEmpresa_conjunto(Empresa empresa_conjunto) {
		this.empresa_conjunto = empresa_conjunto;
	}
}
