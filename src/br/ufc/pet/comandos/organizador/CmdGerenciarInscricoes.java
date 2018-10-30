package br.ufc.pet.comandos.organizador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Inscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.InscricaoService;

/*
 * @author Caio
 */
public class CmdGerenciarInscricoes implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String cod = request.getParameter("cod_evento");

        Long codigo = Long.parseLong(cod);

        EventoService es = new EventoService();
        Evento e = es.getEventoById(codigo);

        if (e != null) {
            InscricaoService is = new InscricaoService();
            session.setAttribute("evento", e);
            List<Inscricao> i = is.getAllInscricoesByEventoId(e.getId());
            
            for(Inscricao inscricao : i){
            	System.out.println(inscricao.getParticipante().getUsuario().getNome() + " ------ " + inscricao.getParticipante().getId() + " ----- " + inscricao.getEvento().getNome());
            }
            
            for (int j = 0; j < i.size(); j++) {
                Inscricao o = i.get(j);
                for (int k = j + 1; k < i.size(); k++) {
                    if (o.getId().equals(i.get(k).getId())) {
                        i.remove(k);
                        k--;
                    }
                }
            }
            session.setAttribute("inscricoes", i);
            return "/org/organ_gerenciar_inscricoes.jsp";
        }

        return "/org/index.jsp";
    }
}
