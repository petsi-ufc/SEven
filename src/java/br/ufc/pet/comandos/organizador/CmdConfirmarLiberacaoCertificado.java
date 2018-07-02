package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.InscricaoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.util.SendMail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Anderson
 */
public class CmdConfirmarLiberacaoCertificado implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        AtividadeService ativServ = new AtividadeService();
        String valores[] = request.getParameterValues("verificaAluno");
        if(valores == null) {
            session.setAttribute("erro", "Nenhum aluno selecionado");
            return "/org/organ_gerenciar_emissao_certificados.jsp";
        } else {
            try{
                String a = (String) request.getParameter("ativ_id");
                Long idAtividade = Long.parseLong(a);
                Long ids[] = new Long[valores.length];
                for(int k=0;k<valores.length;k++){
                    ids[k] = Long.parseLong(valores[k]);
                }

                InscricaoAtividade ia = new InscricaoAtividade();
                ia.setAtividadeId(idAtividade);
                Atividade ativ = ativServ.getAtividadeById(idAtividade);
                InscricaoService inscServ = new InscricaoService();
                for (Long id : ids) {
                    Inscricao insc = inscServ.getInscricaoById(id);
                    ia.setInscricaoId(id);
                    ia.setConfirmaCertificado(true);
                    ativServ.confirmaLiberacaoCertificadoAtividade(ia);
                    //enviar email
                    String messageBody = "Avisamos que seu certificado da atividade "+ativ.getNome()
                            + " está liberado para download na sua conta do SEven. "
                            + "Acesse o sistema com sua conta de usuário, depois vá em: \n"
                            + "Minhas Inscrições -> Gerar Certificado.\n\n"
                            + "Atenciosamente, \n\nEquipe PET";
                    String subject = "Liberação do Certificado da Atividade "+ativ.getNome()+" no evento "+insc.getEvento().getNome();
                    try {
                        SendMail.sendMail(insc.getParticipante().getUsuario().getEmail(), subject, messageBody);
                    } catch (MessagingException ex) {
                        Logger.getLogger(CmdConfirmarLiberacaoCertificado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            } catch (NumberFormatException e){
                session.setAttribute("erro", "Erro na liberação dos certificados");
                return "/org/organ_gerenciar_emissao_certificados.jsp";
            }
        
        }

        session.setAttribute("sucesso", "Certificados liberados com sucesso");
        return "/org/organ_gerenciar_atividades.jsp";
    }
}