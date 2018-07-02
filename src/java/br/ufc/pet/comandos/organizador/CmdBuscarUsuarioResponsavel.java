package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.UsuarioService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdBuscarUsuarioResponsavel implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String nome = request.getParameter("nome_busca");
        if (nome == null || nome.trim().equals("")) {
            session.setAttribute("erro", "Preencha o campo de busca!");
        } else {
            nome = "%" + nome + "%";
            UsuarioService usuService = new UsuarioService();
            ArrayList<Usuario> usuarios = usuService.getByNome(nome);
            session.setAttribute("usuarios", usuarios);
            if (usuarios.isEmpty()) {
                session.setAttribute("erro", "Nenhum resultado para esta busca!");
            }

        }
        return "/org/organ_add_responsavel.jsp";
    }
}
