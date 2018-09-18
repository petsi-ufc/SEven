package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.interfaces.Comando;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdRemoverAtividade implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //este comando retira do array de selecionadas alguma atividade X

        ArrayList<Atividade> arrayDeSelecionadas = new ArrayList();// guardará as atividades selecionadas anteriormente
        Inscricao anterior;

        if (session.getAttribute("inscricao") != null) {
            anterior = (Inscricao) session.getAttribute("inscricao");
            arrayDeSelecionadas.addAll(anterior.getAtividades());
        } else {
            anterior = new Inscricao();
        }

        //reconhecimento da atividade pelo ID, que está na url como ativ
        Long l = Long.parseLong(request.getParameter("ativ"));
        Atividade a;
        for (int i = 0; i < arrayDeSelecionadas.size(); i++) {//procura e remoção
            a = arrayDeSelecionadas.get(i);
            if (a.getId().equals(l)) {
                arrayDeSelecionadas.remove(a);
            }
        }
        anterior.setAtividades(arrayDeSelecionadas);

        session.setAttribute("inscricao", anterior);

        return "/part/part_fazer_inscricao.jsp";
    }
}
