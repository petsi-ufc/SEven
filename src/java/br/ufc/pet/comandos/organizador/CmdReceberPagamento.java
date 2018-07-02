package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.util.SendMail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author USER
 */
public class CmdReceberPagamento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
//
//        String pag = (String) session.getAttribute("pag");
//        
        Inscricao inscrito;
        if (request.getParameter("id_confirm") == null) {
            InscricaoService insc = new InscricaoService();

            inscrito = insc.getInscricaoById(Long.parseLong(request.getParameter("id_inscricao")));

            session.setAttribute("receberpagamentodoparticipante", inscrito);
            return "/org/organ_receber_pagamento.jsp";
        }
        
        InscricaoService inscS = new InscricaoService();
        inscrito = inscS.getInscricaoById(Long.parseLong(request.getParameter("id_confirm")));
        
        if(inscrito.isConfirmada()){
            session.setAttribute("erro", "Já está pago!");
            return "/org/organ_gerenciar_inscricoes.jsp";
        }
        
        inscrito.setConfirmada(true);
        inscS.confirmaPagamento(inscrito);
        session.setAttribute("pago", inscrito);

        try {
            SendMail.sendMail(inscrito.getParticipante().getUsuario().getEmail(), inscrito.getEvento().getSigla(), "Seu pagamento foi realizado com sucesso. Parabéns por participar do evento " + inscrito.getEvento().getNome());
        } catch (Exception ex) {
            Logger.getLogger(CmdReceberPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if(pag.equals("1")){
//            return "/ServletCentral?comando=CmdBuscarParticipantedeEvento";
//        
//        }else if(pag.equals("2")){
//            return "/ServletCentral?comando=CmdBuscarParticipantePorEmail";
//        }
//        return "/ServletCentral?comando=CmdBuscarParticipantedeEvento";
        session.setAttribute("sucesso", "Pagamento Recebido com Sucesso!");
//        return "/org/organ_gerenciar_inscricoes.jsp";
        return "/ServletCentral?comando=CmdGerenciarInscricoes&cod_evento=" + inscrito.getEvento().getId();
    }
}
