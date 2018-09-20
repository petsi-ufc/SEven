package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Inscricao;
import br.ufc.pet.entity.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
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
public class CmdRelatorioParticipantesQuites implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        
        String caminhoImagem ="/SEVEN_ARQUIVOS/templates_certificados_upload/UFC.png";            
                    
        
        Evento en = (Evento) session.getAttribute("evento");
        ParticipanteService partS = new ParticipanteService();
        ArrayList<Participante> parts = partS.getParticipantesQuitesByEventoId(en.getId());
        if (parts == null || parts.isEmpty()) {
            session.setAttribute("erro", "Atividade sem participantes no momento");
            return "/org/organ_relatorios.jsp";
        }
        try {
            // cria o pdf
            // configura o content type para a respota pdf
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", " attachment; filename=\"relatorio_" + en.getNome() + ".pdf\"");


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
                    + "Relatório de Participantes Quites", fonteCabecalho));
            document.add(cabecalho); /* Adicionando ao documento. */
            try {
                /* Adicionando ao documento. */ document.add(getCabecalhoAtividade(en, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_relatorios.jsp";
            }
            /* Corpo do pdf. */
            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(5f); /* Coloca um espaço antes da tabela. */
            table.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.05f, 0.50f, 0.20f, 0.25f, 0.10f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(0.5f);
            table.addCell(new Phrase("Nº", fonteDesc));
            table.addCell(new Phrase("\tNome", fonteDesc));
            table.addCell(new Phrase("Instituição", fonteDesc));
            table.addCell(new Phrase("E-mail", fonteDesc));
            table.addCell(new Phrase("valor", fonteDesc));
            // adiciona os compromissos no pdf
            double precoTotal = 0;
            for (int i = 0; parts != null && i < parts.size(); i++) {
                if (i % 2 == 0) {
                    table.getDefaultCell().setBackgroundColor(Color.WHITE);
                } else {
                    table.getDefaultCell().setGrayFill(0.80f);
                }

                Participante p = parts.get(i);
                double preco = getPrecoByParticipante(p, en);
                table.addCell(new Phrase("" + (i + 1), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getNome(), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getInstituicao(), fonteConteudo));
                table.addCell(new Phrase(p.getUsuario().getEmail(), fonteConteudo));
                table.addCell(new Phrase(String.format("R$ %.2f", preco), fonteConteudo));
                precoTotal += preco;
            }
            document.add(table);
            try {
                document.add(getPreco(precoTotal, fonteConteudo));
                document.add(getRodaPeAtividade(en, fonteRodaPe));

            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_relatorios.jsp";
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
                return "/org/organ_relatorios.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_relatorios.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.setAttribute("erro", "Erro " + ex.getMessage());
            return "/org/organ_relatorios.jsp";
        }
        return "/org/organ_relatorios.jsp";
    }

    public PdfPTable getCabecalhoAtividade(Evento en, Font fonteDesc) throws Exception {
        /* Tabela de descrição do relatório. */
        PdfPTable desc = new PdfPTable(2);
        float[] widths = new float[]{0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Cod.: " + en.getId(), fonteDesc));//pega o id do evento
        desc.addCell(new Phrase(10f, "Tema: " + en.getTema(), fonteDesc));//tema do evento
        desc.addCell(new Phrase("Sigla: " + en.getSigla(), fonteDesc)); //sigla do evento
        desc.addCell(new Phrase("Nome: " + en.getNome(), fonteDesc)); //o nome do evento
        return desc;
    }

    public PdfPTable getRodaPeAtividade(Evento en, Font fonteRodaPe) throws Exception {
        /* Tabela de descrição do relatório. */
        PdfPTable desc = new PdfPTable(1);
        float[] widths = new float[]{0.20f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Evento :" + en.getNome(), fonteRodaPe));
        desc.addCell(new Phrase("Gerada em :" + UtilSeven.treatToString(new Date()), fonteRodaPe));
        return desc;
    }

    public PdfPTable getPreco(double precoTotal, Font fonteRodaPe) throws Exception {
        /* Tabela de descrição do relatório. */
        PdfPTable desc = new PdfPTable(2);
        float[] widths = new float[]{0.85f, 0.15f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
        desc.setWidths(widths);
        desc.getDefaultCell().setGrayFill(30f);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Total  ", fonteRodaPe));
        desc.addCell(new Phrase(String.format("R$ %.2f",precoTotal), fonteRodaPe)); // valor total
        return desc;
    }

    private double getPrecoByParticipante(Participante p, Evento en) {

        InscricaoService is = new InscricaoService();
        ArrayList<Inscricao> inscricoes = is.getAllInscricaoByParticipanteId(p.getId());
        for (Inscricao i : inscricoes) {
            if (i.getEvento().getId().equals(en.getId())) {
                return is.getPrecoInscricao(i);

            }
        }
        return 0;
    }
}
