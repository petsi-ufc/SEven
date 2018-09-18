package br.ufc.pet.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/*
 * @author anderson
 */

@WebFilter("/validacao/*")
public class FiltroValidacaoDocumento implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse) response).sendRedirect("../validacao_documento.jsp");
    }
    
    @Override
    public void destroy() {}
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
   
}
