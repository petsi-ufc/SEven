package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author USER
 */
public class CmdBuscarParticipantedeEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Inscricao inscricoesdoevento;


        if (session.getAttribute("pago") == null) {

            String inscricao = request.getParameter("inscricaobuscar");

            if (inscricao == null || inscricao.trim().isEmpty()) {
                session.setAttribute("mensagem", "vazio");
                return "/org/organ_listar_pagamento.jsp";
            }
            InscricaoService inscricaoservice = new InscricaoService();
            inscricoesdoevento = inscricaoservice.getInscricaoById(Long.parseLong(inscricao));
        } else {
            inscricoesdoevento = (Inscricao) session.getAttribute("pago");
            session.removeAttribute("pago");
        }
        Evento evento = (Evento) session.getAttribute("evento");


        if (inscricoesdoevento != null) {
            if (inscricoesdoevento.getEvento().getId().compareTo(evento.getId()) == 0) {
                session.setAttribute("inscricoesdoevento", inscricoesdoevento);
            } else {
                session.setAttribute("mensagem", "naoencontrado");
            }
        } else {
            session.setAttribute("mensagem", "naoencontrado");
        }
        return "/org/organ_listar_pagamento.jsp";
    }
}
