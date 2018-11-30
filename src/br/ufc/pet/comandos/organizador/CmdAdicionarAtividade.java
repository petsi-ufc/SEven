package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Horario;
import br.ufc.pet.entity.ResponsavelAtividade;
import br.ufc.pet.entity.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.HorarioService;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.services.ResponsavelAtividadeService;
import br.ufc.pet.services.TipoAtividadeService;
import br.ufc.pet.util.UtilSeven;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        AtividadeService atividadeService = new AtividadeService();
        String nome = request.getParameter("nome_atividade");
        String local = request.getParameter("local");
        String vagas = request.getParameter("vagas");
        String tipo = request.getParameter("tipo_id");
        long vagasOcupadas = 0;
        
        InscricaoService inscricaoService = new InscricaoService();
        
        Atividade atividade = (Atividade) session.getAttribute("atividade");
        String aceitaInscricao = request.getParameter("inscritivel");
        Atividade ativTemp = new Atividade();
        
        if(atividade != null){
            try{
                vagasOcupadas = inscricaoService.getInscritosByAtividadeId(atividade.getId());
            }catch (SQLException ex) {
                Logger.getLogger(CmdAdicionarAtividade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            Evento evento = (Evento) session.getAttribute("evento");

            //Coletar os horários selecionados para o evento
            ArrayList<Horario> horariosEscolhidos = new ArrayList<Horario>();
            HorarioService horarios = new HorarioService();
            for (Horario h : horarios.getHorariosByEventoId(evento.getId())) {
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

            if (atividade == null) {       
             
                atividade = new Atividade();
                atividade.setAceitaInscricao(inscritivel);
                atividade.setNome(nome);
               
                atividade.setLocal(local);                
                atividade.setTipo(ta);
                atividade.setEvento(evento);
                atividade.setResponsaveis(resps); 
                
                int numeroVagas = Integer.parseInt(vagas);
                
                if(numeroVagas > 0 && numeroVagas <= 1000){
                   atividade.setVagas(numeroVagas);
                } else {
                    session.setAttribute("erro", "Número de vagas deve ser diverente de Zero e menor ou igual a Mil.");
                    return "/org/organ_add_atividades.jsp";
                }

                //horarios escolhidos sao setados na ativadade
                atividade.setHorarios(horariosEscolhidos);                
                if (atividadeService.adicionar(atividade)) {
                    //inclui atividade no evento da sessão
                    evento.getAtividades().add(atividade);
                    session.setAttribute("sucesso", "Atividade cadastrada com sucesso!");
                    return "/org/organ_gerenciar_atividades.jsp";
                }
                             
            } else {
                atividade.setAceitaInscricao(inscritivel);
                atividade.setNome(nome);
                atividade.setLocal(local);
                atividade.setTipo(ta);
                atividade.setEvento(evento);
                atividade.setResponsaveis(resps);
                
                //horarios escolhidos sao setados na ativadade
                atividade.setHorarios(horariosEscolhidos);
                
                int numeroVagas = Integer.parseInt(vagas);
                
                if(vagasOcupadas <= numeroVagas){
                
                    if(numeroVagas > 0 && numeroVagas <= 1000){
                       atividade.setVagas(numeroVagas);
                    } else {
                        session.setAttribute("erro", "Número de vagas deve ser diverente de Zero e menor ou igual a Mil.");
                        return "/org/organ_add_atividades.jsp";
                    }
                     
                } else{
                    session.setAttribute("erro", "Número de inscritos maior que número de vagas");
                    return "/org/organ_add_atividades.jsp";
                } 
                
                if (atividadeService.atualizar(atividade)) {
                    session.removeAttribute("atividade");
                    session.setAttribute("sucesso", "Atividade atualizada com sucesso!");
                    return "/org/organ_gerenciar_atividades.jsp";
                }
            }
            return "/org/organ_gerenciar_atividades.jsp";
        }
    }
}