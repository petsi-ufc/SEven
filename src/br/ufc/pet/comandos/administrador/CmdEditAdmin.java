package br.ufc.pet.comandos.administrador;

import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AdministradorService;
import br.ufc.pet.util.UtilSeven;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author mardson
 */
public class CmdEditAdmin implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        AdministradorService adm = new AdministradorService();
        String temp = request.getParameter("id");
        long id = Long.parseLong(temp);
        
        String tempOldSenha = request.getParameter("oldsenha");
        String oldsenha = UtilSeven.criptografar(tempOldSenha);
        if(oldsenha.equals(adm.getSenhaAdmin(id)) && temp != null){
            String senha = request.getParameter("senha");
            String rsenha = request.getParameter("rsenha");
            if(senha.equals(rsenha)){
                adm.alterarSenhaAdmin(id, senha);
                session.setAttribute("sucesso", "Senha alterada com Sucesso!");
                return "/admin/index.jsp";
            }else{
                session.setAttribute("erro", "Senhas não correspondem!");
            }
        }else{
            session.setAttribute("erro", "Senha Antiga incorreta!");
        }
        
        return "/admin/edit_senha_admin.jsp";
    }
}