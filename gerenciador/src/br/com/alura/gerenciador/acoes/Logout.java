package br.com.alura.gerenciador.acoes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao =  request.getSession();
		
		// sessao.removeAttribute("usuarioLogado");
		/* 
		 * Remover usuarioLogado da sessão funciona, mas pode haver mais variáveis de sessão para remover.
		 * Há um jeito mais elegante, que destroi a sessão e remove o cookie JSESSIONID do cliente:
		 * o comando sessao.invalidate().
		 */
		sessao.invalidate();
		
		return 	"redirect:entrada?acao=LoginForm";
	}
}
