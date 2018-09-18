package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdExcluirParticipante implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Participante part = (Participante) session.getAttribute("user");
        ParticipanteService ps = new ParticipanteService();
        if(ps.deleteParticipanteUsuario(part)){
        
        session.setAttribute("sucesso","Conta Excluída com sucesso!");        
            return "/index.jsp";
        }else{
            session.setAttribute("erro","Erro ao Excluir conta, por favor relate ao Administrador!");
            return "/part/gerencia_conta.jsp";
        }
    }

}
