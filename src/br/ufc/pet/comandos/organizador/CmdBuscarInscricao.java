package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.ModalidadeInscricao;
import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.services.ModalidadeInscricaoService;
import br.ufc.pet.services.TipoAtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author mardson
 */
public class CmdBuscarInscricao implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");

        if(id == null || id.trim().isEmpty()){
            return "/org/organ_gerenciar_inscricoes.jsp";
        }else{
            InscricaoService is = new InscricaoService();

            Inscricao insc = is.getInscricaoById(Long.parseLong(id));

            ModalidadeInscricaoService ms = new ModalidadeInscricaoService();
            ArrayList<ModalidadeInscricao> modalidades = ms.getModalidadesInscricaoByEventoId(insc.getEvento().getId());

            TipoAtividadeService ts = new TipoAtividadeService();
            ArrayList<TipoAtividade> arrayDeTipos= ts.getTiposDeAtividadesByEventoId(insc.getEvento().getId());

            session.setAttribute("inscricao", insc);
            session.setAttribute("eventoSelecionado", insc.getEvento());
            session.setAttribute("arrayDeTipos", arrayDeTipos);
            session.setAttribute("modalidades", modalidades);
            
            return "/org/org_editar_inscricao.jsp";
        }
        
    }

}
