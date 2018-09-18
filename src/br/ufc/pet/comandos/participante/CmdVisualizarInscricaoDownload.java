package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.services.UsuarioService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author welligton
 */
public class CmdVisualizarInscricaoDownload implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Participante p = (Participante) session.getAttribute("user");
        ParticipanteService ps = new ParticipanteService();
        p.setUsuario(new UsuarioService().getById(p.getUsuario().getId()));
        Long id = Long.parseLong(request.getParameter("id"));
        ArrayList<Inscricao> inscricoes = ps.getByUsuarioId(id).getInscricoes();
        session.setAttribute("inscricoes", inscricoes);
        return "/part/part_gerar_boleto_pagamento.jsp";
    }
}
