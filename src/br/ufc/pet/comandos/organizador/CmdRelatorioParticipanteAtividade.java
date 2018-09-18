package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.util.UtilSeven;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdRelatorioParticipanteAtividade implements Comando {

    @SuppressWarnings("static-access")
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        
        String caminhoImagem ="/SEVEN_ARQUIVOS/templates_certificados_upload/UFC.png";
        
        Long id = Long.parseLong(request.getParameter("idAtv"));
        Atividade at = new AtividadeService().getAtividadeById(id); //pega a atividade pelo id
        ParticipanteService partS = new ParticipanteService();
        ArrayList<Participante> parts = partS.getParticipanteByAtividadeId(id);
        if (parts == null || parts.isEmpty()) {
            session.setAttribute("erro", "Atividade sem participantes no momento");
            return "/org/organ_listar_atividades_download.jsp";
        }
        try {
            // cria o pdf
            // configura o content type para a respota pdf
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", " attachment; filename=\"relatorio_" + at.getNome() + ".pdf\"");


            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            /* Imagem */
//        caminho da iamgens "http://localhost:8080/SEVEN/imagens/ufc.jpg"
            Image jpg = Image.getInstance(caminhoImagem);
            jpg.setAlignment(Image.LEFT | Image.UNDERLYING); /* Ajusta o alinhamento da imagem. */

            /* Fontes */
            Font fonteCabecalho = new Font(Font.HELVETICA, 12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteDesc = new Font(Font.HELVETICA, 10, Font.BOLD); /* Será usada na descrição. */
            Font fonteConteudo = new Font(Font.HELVETICA, 10, Font.NORMAL); /* Será usada no corpo de relatório. */
            Font fonteRodaPe = new Font(Font.ITALIC, 8, Font.NORMAL); /* Será usada no rodapé de relatório. */
            /* Tabela para o cabeçalho. */
            PdfPTable cabecalho = new PdfPTable(2);
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Sistema de Eventos\n"
                    + "Relatório de Participantes por Atividade", fonteCabecalho));
            document.add(cabecalho); /* Adicionando ao documento. */
            try {
                /* Adicionando ao documento. */ 
             document.add(getCabecalhoAtividade(at, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_download.jsp";
            }
            /* Corpo do pdf. */
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(5f); /* Coloca um espaço antes da tabela. */
            table.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.05f, 0.50f, 0.20f, 0.25f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(0.5f);
            table.addCell(new Phrase("Nº", fonteDesc));
            table.addCell(new Phrase("\tNome", fonteDesc));
            table.addCell(new Phrase("Instituição", fonteDesc));
            table.addCell(new Phrase("E-mail", fonteDesc));
            // adiciona os compromissos no pdf
            for (int i = 0; parts != null && i < parts.size(); i++) {
                if (i % 2 == 0) {
                    table.getDefaultCell().setBackgroundColor(Color.WHITE);
                } else {
                    table.getDefaultCell().setGrayFill(0.80f);
                }
                Participante p = parts.get(i);
                table.addCell(new Phrase("" + (i + 1), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getNome(), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getInstituicao(), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getEmail(), fonteConteudo));
            }
            document.add(table);
            try {
                document.add(getRodaPeAtividade(at, fonteRodaPe));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_download.jsp";
            }
            document.close();
            // escreve o pdf no servlet
            response.setContentLength(baos.size());
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_download.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_download.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.setAttribute("erro", "Erro " + ex.getMessage());
            return "/org/organ_listar_atividades_download.jsp";
        }
        return "/org/organ_listar_atividades_download.jsp";
    }

    public PdfPTable getCabecalhoAtividade(Atividade a, Font fonteDesc) throws Exception {
        /* Tabela de descrição do relatório. */
        PdfPTable desc = new PdfPTable(3);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Cod.: " + a.getId(), fonteDesc));//pega o id da atividade
        desc.addCell(new Phrase("Local: " + a.getLocal(), fonteDesc)); //pega o local da atividade
        desc.addCell(new Phrase(10f, "Evento: " + a.getEvento().getNome(), fonteDesc));//pega o nome do evento
        desc.addCell(new Phrase("Vagas: " + a.getVagas(), fonteDesc)); //o número de vagas
        desc.addCell(new Phrase("Tipo: " + a.getTipo().getNome(), fonteDesc));//o tipo da atividade
        desc.addCell(new Phrase("Nome: " + a.getNome(), fonteDesc)); //o nome da atividade
        return desc;
    }

    public PdfPTable getRodaPeAtividade(Atividade a, Font fonteRodaPe) throws Exception {
        /* Tabela de descrição do relatório. */
        PdfPTable desc = new PdfPTable(1);
        float[] widths = new float[]{0.20f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Frequência da Atividade :" + a.getNome(), fonteRodaPe)); //o nome da atividade
        desc.addCell(new Phrase("Gerada em :" + UtilSeven.treatToString(new Date()), fonteRodaPe));
        return desc;
    }
}
