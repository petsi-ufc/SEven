package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.ResponsavelAtividade;
import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ResponsavelAtividadeService;
import br.ufc.pet.services.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdIncluirResponsavel implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String usuarioID = request.getParameter("usuario_id");
        ResponsavelAtividadeService ras = new ResponsavelAtividadeService();
        ResponsavelAtividade responsavelBuscado = ras.getByUsuarioId(Long.parseLong(usuarioID));
        if (responsavelBuscado == null) {
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = usuarioService.getById(Long.parseLong(usuarioID));
            responsavelBuscado = new ResponsavelAtividade();
            responsavelBuscado.setUsuario(usuario);
            responsavelBuscado.setStatus(true);
            responsavelBuscado.setDataCriacao(new Date());
        }
        ArrayList<ResponsavelAtividade> resps = (ArrayList<ResponsavelAtividade>) session.getAttribute("responsaveisEscolhidos");
        //Verificar se o responsavel ja nao foi selecionado

        for (ResponsavelAtividade r : resps) {
            if (r.getUsuario().getId().compareTo(Long.parseLong(usuarioID)) == 0) {
                session.setAttribute("erro", "Atividade já possui este Responsável!");
                return "/org/organ_add_responsavel.jsp";
            }
        }

        resps.add(responsavelBuscado);
        return "/org/organ_add_atividades.jsp";
    }
}
