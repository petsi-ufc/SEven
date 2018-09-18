package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author junior
 */
public class CmdGerenciarUploadCertificados implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        
        Evento evento = (Evento) session.getAttribute("evento");

        return "/org/organ_gerenciar_upload_certificados.jsp";

    }

}
