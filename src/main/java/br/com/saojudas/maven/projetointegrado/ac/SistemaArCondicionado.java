package br.com.saojudas.maven.projetointegrado.ac;

import java.util.ArrayList;

public class SistemaArCondicionado {

	protected static ArrayList<ArCondicionado> listaAC;
	protected String str;
	
	public String getStr() {
		return str;
	}
	
	public static void populaListaAC()
	{
		listaAC = new ArrayList<ArCondicionado>();
		listaAC.add(new ArCondicionado("A", 1));
		listaAC.add(new ArCondicionado("B", 2));
		listaAC.add(new ArCondicionado("C", 3));
		listaAC.add(new ArCondicionado("D", 4));
		listaAC.add(new ArCondicionado("E", 5));
		listaAC.add(new ArCondicionado("F", 6));
		listaAC.add(new ArCondicionado("G", 7));
		listaAC.add(new ArCondicionado("H", 8));
		listaAC.add(new ArCondicionado("I", 9));
		listaAC.add(new ArCondicionado("J", 10));
	}
	
}
