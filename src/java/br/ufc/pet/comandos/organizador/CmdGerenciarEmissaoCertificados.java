package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Anderson
 */
public class CmdGerenciarEmissaoCertificados implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        
        Evento evento = (Evento) session.getAttribute("evento");
        
        String path = "/SEVEN_ARQUIVOS/templates_certificados_upload/"+evento.getId();
        File file = new File(path);
        if(!file.exists()){
            session.setAttribute("erro", "Nenhum template encontrado!\nPara liberar certificados, faça upload do template do certificado para seu evento.");
            return "/org/organ_gerenciar_atividades.jsp";
        }

        return "/org/organ_gerenciar_emissao_certificados.jsp";

    }

}
