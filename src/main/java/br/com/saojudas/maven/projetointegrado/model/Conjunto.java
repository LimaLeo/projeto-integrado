package br.com.saojudas.maven.projetointegrado.model;

import java.util.ArrayList;
import java.util.List;

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
	private Integer id;

	@Column(name = "bloco", length = 100, nullable = false)
	private String bloco;

	@Column(name = "temperaturaMedia", nullable = false)
	private double temperaturaMedia;

	@Column(name = "statusConjunto", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	private Empresa empresa;

	public Conjunto() {
		this("Sem bloco", 0, Status.ATIVO);
	}

	public Conjunto(String bloco, double temperaturaMedia, Status status) {
		setBloco(bloco);
		setTemperaturaMedia(temperaturaMedia);
		setStatusConjunto(status.ATIVO);
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public double getTemperaturaMedia() {
		return temperaturaMedia;
	}

	public void setTemperaturaMedia(double temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
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

	public void setStatusConjunto(Status status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
