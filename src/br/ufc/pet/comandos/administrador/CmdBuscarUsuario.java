package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.UsuarioService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdBuscarUsuario implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String nome = request.getParameter("nome");
        nome = "%" + nome + "%";
        UsuarioService user = new UsuarioService();
        ArrayList<Usuario> users = user.getByNome(nome);
        if (users == null || users.isEmpty()) {

            session.setAttribute("erro", "Nome não encontrado!");
        }

        session.setAttribute("nomes", users);

        return "/admin/admin_buscar_organ.jsp";
    }
}
