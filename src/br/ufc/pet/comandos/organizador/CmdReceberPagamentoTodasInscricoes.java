package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Franklin
 */
public class CmdReceberPagamentoTodasInscricoes implements Comando{
  
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Evento evento = (Evento) session.getAttribute("evento");
        InscricaoService inscS = new InscricaoService();
        inscS.confirmarTodosPagamentos(evento.getId());
        session.setAttribute("todosPagamentosRecebidos", "ok");
    return "/org/organ_financeiro.jsp";
    }
}
