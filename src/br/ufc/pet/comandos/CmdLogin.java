package br.ufc.pet.comandos;

import br.ufc.pet.evento.Administrador;
import br.ufc.pet.evento.Organizador;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.evento.Perfil;
import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.UsuarioService;
import br.ufc.pet.util.UtilSeven;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdLogin implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String conta = request.getParameter("conta");

        if (senha == null || email == null || conta == null || senha.trim().isEmpty()
                || email.trim().isEmpty() || conta.trim().isEmpty()) {
            session.setAttribute("erro", "Preencha todos os campos.");
        } else {
            Usuario user = new Usuario();
            user.setEmail(email);
            user.setSenha(UtilSeven.criptografar(senha));

            UsuarioService userService = new UsuarioService();
            Perfil perfil = userService.validaUsuario(user, conta);

            if (perfil == null) {
                session.setAttribute("erro", "Usuário e senha não conferem.");
            } else if (perfil instanceof Participante) {
                session.setAttribute("user", perfil);
                EventoService es = new EventoService();
                session.setAttribute("eventosAbertos", es.buscarEventosComInscricoesAbertas());
                return "/part/index.jsp";
            } else if (perfil instanceof Organizador) {
                session.setAttribute("user", perfil);
                return "/org/index.jsp";
            } else if (perfil instanceof Administrador) {
                session.setAttribute("user", perfil);
                return "/admin/index.jsp";
            }
        }

        return "/index.jsp";
    }
}
