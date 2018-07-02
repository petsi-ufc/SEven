package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Horario;
import br.ufc.pet.evento.ResponsavelAtividade;
import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.ResponsavelAtividadeService;
import br.ufc.pet.services.TipoAtividadeService;
import br.ufc.pet.util.UtilSeven;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdAdicionarAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        AtividadeService as = new AtividadeService();
        String nome = request.getParameter("nome_atividade");
        String local = request.getParameter("local");
        String vagas = request.getParameter("vagas");
        String tipo = request.getParameter("tipo_id");

        Atividade ativ = (Atividade) session.getAttribute("atividade");
        String aceitaInscricao = request.getParameter("inscritivel");
        Atividade ativTemp = new Atividade();
        if (nome != null) {
            ativTemp.setNome(nome);
        } else {
            ativTemp.setNome("");
        }
        if (local != null) {
            ativTemp.setLocal(local);
        } else {
            ativTemp.setLocal("");
        }

        try {
            ativTemp.setVagas(Integer.parseInt(vagas));
        } catch (Exception e) {
            ativTemp.setVagas(0);
            session.setAttribute("atividadeTemp", ativTemp);
            session.setAttribute("erro", "Campo vagas deve ter um valor numérico.");
            return "/org/organ_add_atividades.jsp";
        }

        if (tipo == null || nome == null || nome.trim().equals("") || local == null || local.equals("") || vagas == null || vagas.equals("")) {
            session.setAttribute("atividadeTemp", ativTemp);
            session.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/org/organ_add_atividades.jsp";
        } else {
            boolean inscritivel = true;
            Evento ev = (Evento) session.getAttribute("evento");

            //Coletar os horários selecionados para o evento
            ArrayList<Horario> horariosEscolhidos = new ArrayList<Horario>();
            for (Horario h : UtilSeven.getHorariosByEvento(ev.getId())) {
                String horario = request.getParameter("cb_horario_" + h.getId());
                if (horario != null) {
                    horariosEscolhidos.add(h);
                }
            }
            //Testa se pelo menos um horario foi selecionado
            if (horariosEscolhidos.size() < 1) {
                session.setAttribute("atividadeTemp", ativTemp);
                session.setAttribute("erro", "Pelo menos um horário deve ser selecionado!");
                return "/org/organ_add_atividades.jsp";
            }
            //Testa se existe pelo menos um responsavel selecionado.
            ArrayList<ResponsavelAtividade> resps = (ArrayList<ResponsavelAtividade>) session.getAttribute("responsaveisEscolhidos");
            Long nTipoId = Long.parseLong(tipo);
            TipoAtividadeService taService = new TipoAtividadeService();
            TipoAtividade ta = taService.getTipoDeAtividadeById(nTipoId);

            if (resps.size() < 1) {
                session.setAttribute("atividadeTemp", ativTemp);
                session.setAttribute("erro", "Pelo menos um responsável deve ser selecionado!");
                return "/org/organ_add_atividades.jsp";
            }
            if (aceitaInscricao.equals("NAO")) {
                inscritivel = false;
            }

            //Verifica se há conflito entre os horarios selecionados
            int x = 1;
            for (int i = 0; i < horariosEscolhidos.size(); i++) {
                for (int j = x; j < horariosEscolhidos.size(); j++) {
                    if (horariosEscolhidos.get(i).conflitaComHorario(horariosEscolhidos.get(j))) {
                        session.setAttribute("erro", "Voce selecionou horários conflitantes para esta atividade!");
                        return "/org/organ_add_atividades.jsp";
                    }

                }
                x++;
            }

            //Incluir os novos responsaveis
            ResponsavelAtividadeService ras = new ResponsavelAtividadeService();
            for (ResponsavelAtividade ra : resps) {
                if (ra.getId() == null) {
                    ras.insertPerfilResponsavelAtividade(ra);
                }
            }

            if (ativ == null) {
                ativ = new Atividade();
                ativ.setAceitaInscricao(inscritivel);
                ativ.setNome(nome);
                ativ.setLocal(local);
                ativ.setVagas(Integer.parseInt(vagas));
                ativ.setTipo(ta);
                ativ.setEvento(ev);
                ativ.setResponsaveis(resps);

                //horarios escolhidos sao setados na ativadade
                ativ.setHorarios(horariosEscolhidos);
                if (as.adicionar(ativ)) {
                    //inclui atividade no evento da sessão
                    ev.getAtividades().add(ativ);
                    session.setAttribute("sucesso", "Atividade cadastrada com sucesso!");
                    return "/org/organ_gerenciar_atividades.jsp";
                }
            } else {
                ativ.setAceitaInscricao(inscritivel);
                ativ.setNome(nome);
                ativ.setLocal(local);
                ativ.setVagas(Integer.parseInt(vagas));
                ativ.setTipo(ta);
                ativ.setEvento(ev);
                ativ.setResponsaveis(resps);

                //horarios escolhidos sao setados na ativadade
                ativ.setHorarios(horariosEscolhidos);
                if (as.atualizar(ativ)) {
                    session.removeAttribute("atividade");
                    session.setAttribute("sucesso", "Atividade atualizada com sucesso!");
                    return "/org/organ_gerenciar_atividades.jsp";
                }
            }
            return "/org/organ_gerenciar_atividades.jsp";
        }
    }
}
