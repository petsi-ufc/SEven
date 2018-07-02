package br.ufc.pet.comandos;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.InscricaoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.InscricaoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Anderson
 */
public class CmdValidarDocumento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String codigo = request.getParameter("codigo");
        
        if (codigo == null || codigo.trim().isEmpty()) {
            session.setAttribute("erro", "Preencha todos os campos.");
        } else {
            InscricaoService is = new InscricaoService();
            Inscricao inscricao = is.getInscricaoByCodigoValidacao(codigo);
            if(inscricao != null){
                AtividadeService as = new AtividadeService();
                ArrayList<InscricaoAtividade> ias = as.getIncricaoAtividadeByInscricao(inscricao.getId());
                ArrayList<Long> idsAtiv = Atividade.getIdsAtividadeCeriticadoLiberado(ias);
                
                ArrayList<Atividade> atividadesCertificadoLiberado = new ArrayList<Atividade>();
                for(Atividade a : inscricao.getAtividades()){
                    if(!idsAtiv.contains(a.getId())){
                        continue;
                    }
                    atividadesCertificadoLiberado.add(a);
                }
                inscricao.setAtividades(atividadesCertificadoLiberado);
                session.setAttribute("sucesso", "Documento válido e emitido pelo SEVEN.");
            } else {
                session.setAttribute("erro", "Este documento não foi encontrado no sistema, portanto ele é inválido.");
            }
            session.setAttribute("dados_inscricao", inscricao);
        }

        return "/validacao_documento.jsp";
    }
}
