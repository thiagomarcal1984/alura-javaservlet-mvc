package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/entrada") // Removendo a anota��o do filtro.
public class AutorizacaoFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("AutoricacaoFilter");
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		String paramAcao = request.getParameter("acao");

		HttpSession sessao = request.getSession();

		boolean usuarioNaoEstaLogado = sessao.getAttribute("usuarioLogado") == null;
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm")); 
		// Sem a vari�vel ehUmaAcaoProtegida, o comando response.sendRedirect entra em
		// recursividade infinita e a p�gina de login n�o � alcan�ada.
		// Qualquer a��o que n�o seja Login ou LoginForm � protegida.
		
		if (ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			return; // Sem este return, o servlet vai tentar reaproveitar a response, que vai 
					// morrer logo ap�s o comando sendRedirect. Respostas mortas n�o podem dar andamento pra requisi��o.  
					// Cannot call sendRedirect() after the response has been committed
		}
		chain.doFilter(request, response);
	}
}
