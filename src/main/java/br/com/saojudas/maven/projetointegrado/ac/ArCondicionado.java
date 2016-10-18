package br.com.saojudas.maven.projetointegrado.ac;

import java.util.Random;

public class ArCondicionado {

	private String conjunto;
	private int idEmpresa;
	private int temperaturaAtual;
	private int temperaturaReconf;
	private boolean status;
	private Random gerador = new Random();
	
	public ArCondicionado(String conjunto, int idEmpresa)
	{
		setConjunto(conjunto);
		setIdEmpresa(idEmpresa);
		gerarTemperatura();
		setTemperaturaReconf(0);
		setStatus(false);
	}
	
	public String getConjunto() {
		return conjunto;
	}
	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}
	public int getTemperaturaAtual() {
		return temperaturaAtual;
	}
	public void setTemperaturaAtual(int temperaturaAtual) {
		this.temperaturaAtual = temperaturaAtual;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public int getTemperaturaReconf() {
		return temperaturaReconf;
	}

	public void setTemperaturaReconf(int temperaturaReconf) {
		this.temperaturaReconf = temperaturaReconf;
	}
	
	public void gerarTemperatura()
	{
		int numero = gerador.nextInt(15) + 15;
		setTemperaturaAtual(numero);
	}
	
	public String dados()
	{
		String str = "";
		return str += getConjunto() + " " + getIdEmpresa() + " " + getTemperaturaAtual() + "\n";
	}
	
	public String dados2()
	{
		String str = "";
		return str += "Conjunto.......................: " + getConjunto() + "\n" + 
		              "Id Empresa.....................: " + getIdEmpresa() + "\n" + 
				      "Temperatura Atual..............: " + getTemperaturaAtual() + "\n" +
		              "Temperatura para Reconfiguração: " + getTemperaturaReconf() + "\n" + 
				      "Ligar AC.......................: " + isStatus() + "\n" +
		              "---------------------------------------------------\n";
	}
	
}
