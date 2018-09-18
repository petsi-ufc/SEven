package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Organizador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.util.SendMail;
import br.ufc.pet.util.UtilSeven;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Franklin
 */
public class CmdAlterarPeriodoInscricaoeEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String inicioEvento = request.getParameter("inicioEvento");
        session.setAttribute("inicioEvento", inicioEvento);
        String fimEvento = request.getParameter("fimEvento");
        session.setAttribute("fimEvento", fimEvento);
        String inicioInscricao = request.getParameter("inicioInscricao");
        session.setAttribute("inicioInscricao", inicioInscricao);
        String fimInscricao = request.getParameter("fimInscricao");
        session.setAttribute("fimInscricao", fimInscricao);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Date data = UtilSeven.treatToDate(dateFormat.format(date));

        if(inicioEvento==null || inicioEvento.trim().isEmpty() || 
                fimEvento==null || fimEvento.trim().isEmpty() ||
                inicioInscricao==null || inicioInscricao.trim().isEmpty() || 
                fimInscricao==null || fimInscricao.trim().isEmpty()){
            session.setAttribute("erro", "Preencha todos os campos.");
            return "/org/organ_periodos_inscricao_e_evento.jsp";
        }

        if (!UtilSeven.validaData(inicioEvento) || !UtilSeven.validaData(fimEvento)
                || !UtilSeven.validaData(inicioInscricao) || !UtilSeven.validaData(fimInscricao)) {
            session.setAttribute("erro", "Digite as datas no seguinte formato dd/mm/aaaa");
            return "/org/organ_periodos_inscricao_e_evento.jsp";
        }

//        if (UtilSeven.treatToDate(inicioEvento).before(data)) {
//            session.setAttribute("erro", "Data de início do evento anterior a data de hoje.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(inicioEvento).after(UtilSeven.treatToDate(fimEvento))) {
//            session.setAttribute("erro", "Data de início do evento posterior ao término do evento.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(inicioInscricao).before(data)) {
//            session.setAttribute("erro", "Data de início das incrições anterior a data de hoje.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(fimEvento))) {
//            session.setAttribute("erro", "Data de início das inscrições posterior ao término do evento.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(inicioEvento))) {
//            session.setAttribute("erro", "Data de início das inscrições posterior ao início do evento.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(fimInscricao))) {
//            session.setAttribute("erro", "Data de início das inscrições posterior ao término das inscrições.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }
//        if (UtilSeven.treatToDate(fimInscricao).after(UtilSeven.treatToDate(inicioEvento))) {
//            session.setAttribute("erro", "Data de fim das inscrições posterior ao início do evento.");
//            return "/org/organ_periodos_inscricao_e_evento.jsp";
//        }

        //Variáveis usadas para pegar o periodo(data) antiga
        //que o evento iria ocorrer.
        String vDiaInicio, vDiaFim, vDiaInicioInsc, vDiaFimInsc;
        
        Evento evento = (Evento) session.getAttribute("evento");
        
        vDiaInicio = UtilSeven.treatToString(evento.getInicioPeriodoEvento());
        vDiaFim = UtilSeven.treatToString(evento.getFimPeriodoEvento());
        
        vDiaInicioInsc = UtilSeven.treatToString(evento.getInicioPeriodoInscricao());
        vDiaFimInsc = UtilSeven.treatToString(evento.getFimPeriodoInscricao());
        
        evento.setInicioPeriodoEvento(UtilSeven.treatToDate(inicioEvento));
        evento.setFimPeriodoEvento(UtilSeven.treatToDate(fimEvento));
        evento.setInicioPeriodoInscricao(UtilSeven.treatToDate(inicioInscricao));
        evento.setFimPeriodoInscricao(UtilSeven.treatToDate(fimInscricao));
        EventoService eventoService = new EventoService();
        
        if (!eventoService.atualizar(evento)) {
            session.setAttribute("erro", "Modificação sem sucesso");
            return "/org/organ_gerenciar_atividades.jsp";
        }
        
        //Enviando email para todos os organizadores 
        //informando que a data do evento foi alterada.
        String msg = "As datas do evento "+evento.getNome()+" foram alteradas.\n"
                + "De: "+vDiaInicio+" até "+vDiaFim+ 
                "  para\n"
                + "De: "+inicioEvento+" até "+fimEvento+
                "\n\nIncrições:\n"+
                "De: "+vDiaInicioInsc+" até "+vDiaFimInsc+
                "  para\n"+
                "De: "+inicioInscricao+" até "+fimInscricao+"\n"+
                "\nPor favor verifique as datas das atividades!";
        
        for(Organizador org : evento.getOrganizadores()){
            try {
                SendMail.sendMail(org.getUsuario().getEmail(), "(SEVEN) Alteração de data no evento "+evento.getNome(), msg);
            } catch (MessagingException ex) {
               System.out.println("ERRO AO ENVIAR E-MAIL");
            }
        }
        
        session.setAttribute("sucesso", "Modificação realizada com sucesso");
        session.setAttribute("evento", evento);

        return "/org/organ_gerenciar_atividades.jsp";
    }
}
