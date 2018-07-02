package br.ufc.pet.comandos.participante;

import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.util.UtilSeven;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author mardson
 */
public class CmdEditPart implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        ParticipanteService part = new ParticipanteService();
        String temp = request.getParameter("id");
        long id = Long.parseLong(temp);
        
        String tempOldSenha = request.getParameter("oldsenha");
        String oldsenha = UtilSeven.criptografar(tempOldSenha);
        if(oldsenha.equals(part.getSenhaPart(id)) && temp != null){
            String senha = request.getParameter("senha");
            String rsenha = request.getParameter("rsenha");
            if(senha.equals(rsenha)){
                part.alterarSenhaPart(id, senha);
                session.setAttribute("sucesso", "Senha alterada com Sucesso!");
                return "/part/index.jsp";
            }else{
                session.setAttribute("erro", "Senhas não correspondem!");
            }
        }else{
            session.setAttribute("erro", "Senha Antiga incorreta!");
        }
        
        return "/part/part_edit_senha.jsp";
    }
}