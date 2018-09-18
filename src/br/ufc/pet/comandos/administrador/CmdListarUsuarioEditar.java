package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdListarUsuarioEditar implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(request.getParameter("idUsuario"));
        UsuarioService us = new UsuarioService();
        Usuario u = us.getById(id);
        session.setAttribute("usuario", u);

        return "/admin/editar_organ.jsp";
    }
}
