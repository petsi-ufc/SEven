package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdSubmeterInscricao implements Comando {

    public synchronized String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //este comando grava a inscricao no banco de dados
        Inscricao ins = (Inscricao) session.getAttribute("inscricao");
        InscricaoService iS = new InscricaoService();
        
        Evento evento = ins.getEvento();

        //reconhecer se é nova ou é uma edição, no caso de edição, deleta a antiga e insere a nova
        if (ins.getId() == null) {//nova, nao tem ID setado ainda
            //teste de vagas
            if (ins.getAtividades() != null) {
                for (Atividade a : ins.getAtividades()) {
                    try {
                        if (iS.getInscritosByAtividadeId(a.getId()) >= a.getVagas()) {//não há vagas
                            session.setAttribute("erro", "Infelizmente, a última vaga da atividade " + a.getNome() + " acabou de ser preenchida. Por favor, verifique suas atividades novamente.");
                            return "/part/part_fazer_inscricao.jsp";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CmdMontarInscricao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            
            //Verificando se o evento é gratuito, se for, a inscrição é confirmada.
            if(evento != null){
                if(evento.isGratuito()){
                    ins.setConfirmada(true);
                }
            }
            
            
            iS.adicionar(ins);
            ArrayList<Inscricao> array = ins.getParticipante().getInscricoes();
            array.add(ins);
            ins.getParticipante().setInscricoes(array);
            Participante p = (Participante) session.getAttribute("user");
            p.setInscricoes(iS.getAllInscricaoByParticipanteId(p.getId()));
            session.setAttribute("user", p);
        } else {//edição, ja tem ID

            //verificação das atividades anteriores:
            ArrayList<Atividade> atividadesAnteriores = iS.getInscricaoById(ins.getId()).getAtividades();

            //teste de vagas com checagem de atividades anteriores
            if (ins.getAtividades() != null) {//se hoyverem atividades
                for (Atividade a : ins.getAtividades()) {//para cada atividade atual
                    try {
                        if (iS.getInscritosByAtividadeId(a.getId()) >= a.getVagas()) {//se ela estiver lotada
                            boolean vaga = false;//verifique se existe uma vaga reservada pela antiga inscricao
                            for (Atividade antiga : atividadesAnteriores) {//procure nas atividades anteriores
                                if (a.getId().equals(antiga.getId())) {//se estava nas anteriores
                                    vaga = true;
                                    break;
                                    //tudo bem, pois apesar de estar lotada uma das vagas já era a dele.
                                }
                            }
                            if (!vaga) {
                                //erro, pois foi acrescentada uma atividade que nao estava nas anteriores e ela está lotada.
                                session.setAttribute("erro", "Infelizmente, a última vaga da atividade " + a.getNome() + " já foi preenchida. Por favor, verifique suas atividades novamente.");
                                return "/part/part_fazer_inscricao.jsp";
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CmdMontarInscricao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            iS.excluir(ins.getId());
            
            //Verificando se o evento é gratuito, se for, a inscrição é confirmada.
            if(evento != null){
                if(evento.isGratuito()){
                    ins.setConfirmada(true);
                }
            }
            
            iS.adicionar(ins);
            ArrayList<Inscricao> array = ins.getParticipante().getInscricoes();
            array.add(ins);
            ins.getParticipante().setInscricoes(array);
            Participante p = (Participante) session.getAttribute("user");
            p.setInscricoes(iS.getAllInscricaoByParticipanteId(p.getId()));
            session.setAttribute("user", p);
        }
        session.setAttribute("sucesso", "Inscrição realizada com sucesso.");
        return "/part/part_visualizar_inscricoes.jsp";

    }
}
