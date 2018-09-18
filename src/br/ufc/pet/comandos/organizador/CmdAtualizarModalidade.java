package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.ModalidadeInscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ModalidadeInscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Franklin
 */
public class CmdAtualizarModalidade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ModalidadeInscricaoService mis = new ModalidadeInscricaoService();
        ModalidadeInscricao modalidade = mis.getModalidadeInscricaoById(Long.parseLong(request.getParameter("mod_id")));

        session.setAttribute("atualiazar_modalidade", modalidade);

        return "/org/organ_add_modalidade.jsp";
    }
}
