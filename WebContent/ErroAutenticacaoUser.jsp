<%@page import="br.ufc.pet.entity.Perfil" %>

<%
            Perfil perfilUser = (Perfil) session.getAttribute("user");
            if (perfilUser == null) {
                session.setAttribute("erro", "Ops! Para acessar a página desejada é necessário autenticação!");
                response.sendRedirect(request.getContextPath()+"/index.jsp");
                return;
                
            }
%>


