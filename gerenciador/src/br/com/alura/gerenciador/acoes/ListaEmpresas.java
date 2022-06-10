package br.com.alura.gerenciador.acoes;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ListaEmpresas implements Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// long antes = System.currentTimeMillis(); // Início do monitoramento de perfomance, se não houvesse filters.

		System.out.println("Listando empresas.");
		
		Banco banco = new Banco();
		List<Empresa> lista = banco.getEmpresas();
		
		request.setAttribute("empresas", lista);

		// long depois = System.currentTimeMillis(); // Fim do monitoramento de perfomance, se não houvesse filters.	
		// System.out.println("Tempo de execução: " + (depois - antes));

		return "forward:listaEmpresas.jsp";
	}
}
