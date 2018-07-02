package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Anderson
 */
public class CmdGerenciarLiberacaoCertificadoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String temp = request.getParameter("ativ_id");
        temp = temp.replace(" ", "");
        Long idAtividade = Long.parseLong(temp);
        
        ParticipanteService partS = new ParticipanteService();
        ArrayList<Participante> parts = partS.getParticipanteByAtividadeIdQuites(idAtividade);
        if (parts == null || parts.isEmpty()) {
            session.setAttribute("erro", "Atividade sem participantes quites no momento");
            return "/org/organ_gerenciar_emissao_certificados.jsp";
        } else {
            session.setAttribute("participantes", parts);
            session.setAttribute("ativ_id", idAtividade);
            
            return "/org/organ_gerenciar_liberacao_certificados.jsp";
        }
        
    }

    
}
