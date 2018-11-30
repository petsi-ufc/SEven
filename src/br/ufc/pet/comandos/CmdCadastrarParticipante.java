package br.ufc.pet.comandos;

import br.ufc.pet.entity.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.services.UsuarioService;
import br.ufc.pet.util.UtilSeven;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author fernando
 */
public class CmdCadastrarParticipante implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        //Recuperar dados do formulário.
        String nome = request.getParameter("nome");
        session.setAttribute("nome", nome);
        String email = request.getParameter("email");
        session.setAttribute("email", email);
        String instituicao = request.getParameter("instituicao");
        session.setAttribute("instituicao", instituicao);
        String senha = request.getParameter("senha");
        String confSenha = request.getParameter("r-senha");

        if (nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()
                || senha == null || senha.trim().isEmpty() || confSenha == null || confSenha.trim().isEmpty()) {
            session.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/cadastro.jsp";
        }

        if (!senha.trim().equals(confSenha)) {
            session.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/cadastro.jsp";
        }

        //Montar participante
        Participante part = new Participante();
        part.setStatus(true);
        part.setDataCriacao(new Date());
        part.getUsuario().setEmail(email);
        part.getUsuario().setInstituicao(instituicao);
        part.getUsuario().setNome(nome);
        part.getUsuario().setSenha(UtilSeven.criptografar(senha));
        //Validar a inserção
        UsuarioService us = new UsuarioService();
        if (us.getByEmail(part.getUsuario().getEmail()) != null) {
            session.setAttribute("erro", "E-Mail já cadastrado.");
            return "/cadastro.jsp";
        }

        ParticipanteService ps = new ParticipanteService();
        if (ps.insertParticipanteUsuario(part)) {
            session.setAttribute("user", part);
            EventoService es = new EventoService();
            session.setAttribute("eventosAbertos", es.buscarEventosComInscricoesAbertas());
            return "/part/index.jsp";
        } else {
            session.setAttribute("erro", "Erro ao tentar cadastrar participante.");
            return "/cadastro.jsp";
        }
    }
}
