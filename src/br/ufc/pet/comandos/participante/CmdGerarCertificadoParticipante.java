package br.ufc.pet.comandos.participante;

import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.Inscricao;
import br.ufc.pet.entity.InscricaoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.services.UsuarioService;
import br.ufc.pet.util.UtilSeven;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author mardson
 */
public class CmdGerarCertificadoParticipante implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String inscricao_id = request.getParameter("insc_id");

        if (inscricao_id == null || inscricao_id.trim().isEmpty()) {
            session.setAttribute("erro", "Inscrição Inválida!");
            return "/part/part_visualizar_inscricoes.jsp";
        } else {
            InscricaoService is = new InscricaoService();
            Inscricao inscricao = is.getInscricaoById(Long.parseLong(inscricao_id));

            
//                Inscricao insc = inscServ.getInscricaoById(id);
//                ia.setInscricaoId(id);
//                ia.setConfirmaCertificado(true);
//                ativServ.confirmaLiberacaoCertificadoAtividade(ia);
            
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", " attachment; filename=\"certificado_" + inscricao.getParticipante().getUsuario().getNome() + ".pdf\"");

            Document document = new Document(PageSize.LETTER.rotate());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            if(inscricao.getCodigoValidacaoCertificado()==null || inscricao.getCodigoValidacaoCertificado().trim().isEmpty()){
               String chave = "";
               chave += inscricao.getId();
               chave += inscricao.getParticipante().getUsuario().getNome();
               for(Atividade a : inscricao.getAtividades()){
                   chave += a.getNome();
               }
               String codigo = UtilSeven.criptografar(chave);
               inscricao.setCodigoValidacaoCertificado(codigo);
               is.atualizar(inscricao);
            } 
            
            if(!inscricao.getParticipante().getUsuario().isCertificadoGerado()){
                inscricao.getParticipante().getUsuario().setCertificadoGerado(true);
                UsuarioService us = new UsuarioService();
                us.update(inscricao.getParticipante().getUsuario());
            }
            
//            session.setAttribute("user", inscricao.getParticipante());

            try {
                PdfWriter writer = PdfWriter.getInstance(document, baos);
                
                String linkVerificacao = "https://sistemas.quixada.ufc.br/SEVEN/validacao/";                           
                String rodape = "Para verificar a autenticidade deste documento acesse o endereço\n"+linkVerificacao+" e informe o código:\n";
                
                
                Font fonteRodape = new Font();
                fonteRodape.setSize(9);
                Phrase ph1 = new Phrase(rodape, fonteRodape);
                
                Font fonteCodigo = new Font();
                fonteCodigo.setSize(10);
                fonteCodigo.setStyle(Font.BOLD);                
                Phrase ph2 = new Phrase(inscricao.getCodigoValidacaoCertificado(), fonteCodigo);
                ph1.add(ph2);
                
                
                HeaderFooter hf = new HeaderFooter(ph1, false);
                hf.disableBorderSide(HeaderFooter.TOP);
                hf.disableBorderSide(HeaderFooter.BOTTOM);
                document.setFooter(hf);
                
                
                document.open();
                //document.setPageSize(PageSize.A4);
                
                
                AtividadeService as = new AtividadeService();
                ArrayList<InscricaoAtividade> ia = as.getIncricaoAtividadeByInscricao(inscricao.getId());
                ArrayList<Long> idsAtiv = Atividade.getIdsAtividadeCeriticadoLiberado(ia);

                for (Atividade a : inscricao.getAtividades()) {
                    
                    if(!idsAtiv.contains(a.getId())){
                        continue;
                    }    
                    
                    String path = "/SEVEN_ARQUIVOS/templates_certificados_upload/"+inscricao.getEvento().getId();
                    
                    Image jpgTemplate = Image.getInstance(path);
                       

                    PdfContentByte canvas = writer.getDirectContentUnder();
                    jpgTemplate.scaleAbsolute(document.getPageSize().getWidth(), document.getPageSize().getHeight());
                    jpgTemplate.setAbsolutePosition(0, 0);
                    canvas.addImage(jpgTemplate);


                    Paragraph cert2 = new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD));
                    cert2.setAlignment(Element.ALIGN_CENTER);
                    cert2.setSpacingBefore(10);
                    cert2.setSpacingAfter(20);
                    document.add(cert2);

                    Paragraph cert = new Paragraph(inscricao.getParticipante().getUsuario().getNome().toUpperCase(), FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD));
                    cert.setAlignment(Element.ALIGN_CENTER);
                    cert.setSpacingBefore(150);
                    cert.setSpacingAfter(55);
                    document.add(cert);
                    Paragraph p1 = new Paragraph(a.getTipo().getNome() + " de " + a.getNome(), FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD));
                    p1.setAlignment(Element.ALIGN_CENTER);
                    document.add(p1);

                    int cargaHoraria = a.getCargaHoraria();
                    String aux = (cargaHoraria > 1) ? " horas." : " hora.";
                    Paragraph p4 = new Paragraph("    Carga horária: " + cargaHoraria + aux, FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
                    p4.setSpacingBefore(48);

                    document.add(p4);
                    
                    document.newPage();

                }

                document.close();

            } catch (DocumentException ex) {
                System.out.println("DOCUMENTEXCEPTION");
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/part/part_visualizar_inscricoes.jsp";
            } catch (IOException ex) {
                System.out.println("IOEXCEPTION");
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/part/part_visualizar_inscricoes.jsp";
            }



            // escreve o pdf no servlet
            response.setContentLength(baos.size());
            ServletOutputStream out;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                System.out.println("IOEXCEPTION");
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/part/part_visualizar_inscricoes.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
            } catch (Exception ex) {
                System.out.println("EXCEPTION");
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/part/part_visualizar_inscricoes.jsp";
            }
        }

        return "/part/part_visualizar_inscricoes.jsp";
    }
}