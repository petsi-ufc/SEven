package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdVisualizarInscricao implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        InscricaoService is = new InscricaoService();
        //recupera qual foi a inscrição selecionada para visualização/edição por meio de parametro de url
        Inscricao i = is.getInscricaoById(Long.parseLong(request.getParameter("iId")));
        //coloca esta que vai ser visualizada na sessão já setada com o usuário atual.
        i.setParticipante((Participante) session.getAttribute("user"));
        session.setAttribute("inscricao", i);

        return "/part/part_visualizar_inscricao.jsp";
    }

}
