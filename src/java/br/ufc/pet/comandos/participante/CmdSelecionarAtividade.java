package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdSelecionarAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        AtividadeService AtividadeS = new AtividadeService();
        HttpSession session = request.getSession();
        //este comando copia uma atividade da oferta para o array de selecionadas pelo participante

        ArrayList<Atividade> arrayDeSelecionadas = new ArrayList();// guardarÃ¡ as atividades selecionadas anteriormente
        ArrayList<Atividade> arrayDeConflitantes = new ArrayList();// guardarÃ¡ as atividades em conflito
        Inscricao anterior;

        if (session.getAttribute("inscricao") != null) {
            anterior = (Inscricao) session.getAttribute("inscricao");
            arrayDeSelecionadas.addAll(anterior.getAtividades());
        } else {
            anterior = new Inscricao();
        }
        
        //reconhecimento da atividade pelo ID, que esta na url como 'ativ'
        Atividade selecionada = AtividadeS.getAtividadeById(Long.parseLong(request.getParameter("ativ")));
        
        
        //Verfica se a quantidade de atividades selecionadas esta dentro do permitido
        if (!(selecionada.getEvento().getLimiteAtividadePorParticipante() <= 0)) { //Se a inscricoes forem limitadas
            if (selecionada.getEvento().getLimiteAtividadePorParticipante() <= arrayDeSelecionadas.size()) {
                session.setAttribute("erro", "Permitido no máximo " + selecionada.getEvento().getLimiteAtividadePorParticipante()+" atividade(s) neste evento");
                return "/part/part_fazer_inscricao.jsp";//nada a fazer
            }
        }
        
         
        
        Atividade a;
        //verificaÃ§Ã£o de conflitos de horarios, todas as conflitantes com a atualmente selecionada serÃ£o removidas.
        for (int i = 0; i < arrayDeSelecionadas.size(); i++) {
            a = arrayDeSelecionadas.get(i);
            if (a.getId().equals(selecionada.getId())) {//atividade ja foi selecionada anteriormente
                return "/part/part_fazer_inscricao.jsp";//nada a fazer
            }
            if (AtividadeS.conflitam(a, selecionada)) {
                arrayDeConflitantes.add(a);             //conflito detectado
            }
        }

        if (arrayDeConflitantes.isEmpty()) {// nÃ£o hÃ¡ conflitos
            arrayDeSelecionadas.add(selecionada);
            anterior.setAtividades(arrayDeSelecionadas);

            session.setAttribute("inscricao", anterior);
        } else {//existem conflitos, uma mensagem de erro serÃ¡ gerada.
            String nomesConflitantes = new String();
            for (Atividade at : arrayDeConflitantes) {
                nomesConflitantes += at.getNome() + ", ";
            }
            int tam = nomesConflitantes.length();
            nomesConflitantes = nomesConflitantes.substring(0, tam - 2); //retira a ultima virgula, desnecessÃ¡ria.
            session.setAttribute("erro", "Atenção: A atividade " + selecionada.getNome() + " conflita com as seguintes atividades: " + nomesConflitantes + ". Para selecioná-la, você deve retirar primeiramente as conflitantes da seleção.");
        }
        return "/part/part_fazer_inscricao.jsp";
    }
}
