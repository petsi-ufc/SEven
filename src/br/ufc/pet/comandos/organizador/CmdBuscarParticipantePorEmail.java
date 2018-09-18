package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.evento.Usuario;
import br.ufc.pet.evento.Utility;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.services.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Mardson
 */
public class CmdBuscarParticipantePorEmail implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Inscricao inscricoesdoevento;


        if (session.getAttribute("pago") == null) {

            String email = request.getParameter("email");
            Evento evento = (Evento) session.getAttribute("evento");

            if (email == null || email.equals("") || email.trim().isEmpty()) {
                session.setAttribute("mensagem", "vazio");
                return "/org/organ_listar_pagamento_part.jsp";
            } else if (evento == null) {
                session.setAttribute("mensagem", "vazio");
                return "/org/organ_listar_pagamento_part.jsp";
            }
            
            
            UsuarioService us = new UsuarioService();
            Usuario user = us.getByEmail(email);
            
            
            if(user == null){
                session.setAttribute("mensagem", "naoencontrado");
                return "/org/organ_listar_pagamento_part.jsp";
            } 


            ParticipanteService ps = new ParticipanteService();
            Participante part = ps.getByUsuarioId(user.getId());


            InscricaoService inscricaoservice = new InscricaoService();
            Utility u = new Utility();
            u.setEvent_id(evento.getId());
            u.setPart_id(part.getId());
            
            inscricoesdoevento = inscricaoservice.getInscricaoParticipanteEvento(u);
            
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
        return "/org/organ_listar_pagamento_part.jsp";
    }
}
