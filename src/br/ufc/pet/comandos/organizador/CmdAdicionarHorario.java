package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Horario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.HorarioService;
//import br.ufc.pet.util.UtilSeven;
//import java.sql.Time;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdAdicionarHorario implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
//        String hi = request.getParameter("hora_inicial");
//        String mi = request.getParameter("min_inicial");
//        String hf = request.getParameter("hora_final");
//        String mf = request.getParameter("min_final");
//        String dia = request.getParameter("dia");
        String time_inicial = request.getParameter("inicial");
        String time_final = request.getParameter("final");
        String data = request.getParameter("data_completa");
        
        Horario novoH = new Horario();
        Horario horEdit = (Horario) session.getAttribute("horario");
        session.removeAttribute("horario");
        
        Evento evento = (Evento) session.getAttribute("evento");
        HorarioService hs = new HorarioService(evento.getInicioPeriodoEvento(), evento.getFimPeriodoEvento());

        
        
        
        if (time_inicial == null || time_inicial.equals("") || time_final == null || time_final.equals("") || data == null || data.equals("")) {
            session.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/org/organ_add_horario.jsp";
        } else {
            if (horEdit == null) {
                try {
                    //Conversor do String para Date para usar getHours and getMinutes
                    SimpleDateFormat formatadorTime = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");
                    
                    Date hour_inicial = formatadorTime.parse(time_inicial);
                    Date hour_final = formatadorTime.parse(time_final);
                    Date date = formatadorDate.parse(data);
                    
                    novoH.setHoraInicial(hour_inicial.getHours());
                    novoH.setMinutoInicial(hour_inicial.getMinutes());
                    novoH.setHoraFinal(hour_final.getHours());
                    novoH.setMinutoFinal(hour_final.getMinutes());
                    novoH.setDia(date);
                    
                } catch (Exception ex) {
                    session.setAttribute("erro", "Digite números inteiros nos campos para hora e minuto!");
                    return "/org/organ_add_horario.jsp";
                }
//                if (UtilSeven.validaData(data) != true) {
//                    session.setAttribute("erro", "Data Inválida, digite no formato dd/mm/aaaa");
//                    return "/org/organ_add_horario.jsp";
//                } else {
//                    novoH.setDia(UtilSeven.treatToDate(data));
//                }
                
                novoH.setEventoId(evento.getId());
                
                
                if(!hs.adicionar(novoH)){
                    session.setAttribute("erro", "A data informada não é válida!");
                    return "/org/organ_add_horario.jsp";
                }
                ArrayList<Horario> hors = (ArrayList<Horario>) session.getAttribute("horarios");
                hors.add(novoH);
                session.setAttribute("sucesso", "Horário adicionado com sucesso!");

            } else {
                try {
                    //Conversor do String para Date para usar getHours and getMinutes
                    SimpleDateFormat formatadorTime = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");
                    
                    Date hour_inicial = formatadorTime.parse(time_inicial);
                    Date hour_final = formatadorTime.parse(time_final);
                    Date date = formatadorDate.parse(data);
                    
                    horEdit.setHoraInicial(hour_inicial.getHours());
                    horEdit.setMinutoInicial(hour_inicial.getMinutes());
                    horEdit.setHoraFinal(hour_final.getHours());
                    horEdit.setMinutoFinal(hour_final.getMinutes());
                    horEdit.setDia(date);

                } catch (Exception ex) {
                    session.setAttribute("erro", "Digite números inteiros nos campos para hora e minuto!");
                    return "/org/organ_add_horario.jsp";
                }
//                if (UtilSeven.validaData(dia) != true) {
//                    session.setAttribute("erro", "A data deve estar no formato dd/mm/aaaa, por exemplo 01/01/1900!");
//                    return "/org/organ_add_horario.jsp";
//                } else {
//                    horEdit.setDia(UtilSeven.treatToDate(dia));
//                }
                
                if (hs.atualizar(horEdit)) {
                    session.setAttribute("sucesso", "Horário atualizado com sucesso!");
                } else {
                    session.setAttribute("erro", "A data informada não é válida!");
                    return "/org/organ_add_horario.jsp";
                }
            }
        }
        return "/org/organ_gerenciar_horario.jsp";
    }
}
