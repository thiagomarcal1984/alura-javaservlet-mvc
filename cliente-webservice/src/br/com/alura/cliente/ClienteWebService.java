package br.com.alura.cliente;

import org.apache.http.client.fluent.Request;

public class ClienteWebService {
	public static void main(String[] args) throws Exception {
		String pathWebService = "http://localhost:8080/gerenciador/empresas";
		
		String conteudo = Request
			.Post(pathWebService)
			.addHeader("Accept", "application/json")
//			.addHeader("Accept", "application/xml")
			.execute()
			.returnContent()
			.asString();
		
		System.out.println(conteudo);
	}
}
