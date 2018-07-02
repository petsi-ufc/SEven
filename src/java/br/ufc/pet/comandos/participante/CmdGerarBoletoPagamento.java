package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.util.UtilSeven;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author welligton
 */
public class CmdGerarBoletoPagamento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String caminhoImagem ="/SEVEN_ARQUIVOS/templates_certificados_upload/UFC.png";
        
        Long idInc = Long.parseLong(request.getParameter("id"));
        Inscricao inscricao = new InscricaoService().getInscricaoById(idInc); //pega a inscricao pelo id
        inscricao.setParticipante((Participante) session.getAttribute("user"));
        InscricaoService inscricaoService = new InscricaoService();

        try {
            // cria o pdf
            // configura o content type para a respota pdf
            response.setContentType("application/pdf");

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            /* Imagem */
            Image jpg = Image.getInstance(caminhoImagem);
            jpg.setAlignment(Image.LEFT | Image.UNDERLYING); /* Ajusta o alinhamento da imagem. */

            /* Fontes */
            Font fonteCabecalho = new Font(Font.HELVETICA, 12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteDesc = new Font(Font.HELVETICA, 10, Font.BOLD); /* Será usada na descrição. */
            Font fonteConteudo = new Font(Font.HELVETICA, 10, Font.NORMAL); /* Será usada no corpo de relatório. */

            /* Tabela para o cabeçalho. */
            PdfPTable cabecalho = new PdfPTable(2);
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Campus de Quixadá\n" + "Sistema de Eventos", fonteCabecalho));
            document.add(cabecalho); /* Adicionando ao documento. */
            try {
                /* Adicionando ao documento. */// document.add(getInscricaoParticipante(inscricao, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("men", "Erro " + ex.getMessage());
                return "/part/part_gerar_boleto_pagamento.jsp";
            }

            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            es.addCell(new Phrase("Boleto de Pagamento", fonteConteudo));
            document.add(es);

            /* Corpo do pdf. */
            PdfPTable table = new PdfPTable(2);//numero de colunas
            table.setSpacingBefore(5f); /* Coloca um espaço antes da tabela. */
            table.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(10f);
            table.addCell(new Phrase("Local de Pagamento\n\n" + "UFC-Campus Quixadá-PET", fonteDesc));
            table.addCell(new Phrase("\tVencimento\n\n" + UtilSeven.treatToString(inscricao.getDataPagamento()), fonteDesc));

            document.add(table);

            PdfPTable table2 = new PdfPTable(2);//numero de colunas
            table2.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table2.setWidths(widths);
            table2.getDefaultCell().setGrayFill(10f);
            table2.addCell(new Phrase("Data da Inscrição\n\n" + UtilSeven.treatToString(inscricao.getDataRealizada()), fonteDesc));
            table2.addCell(new Phrase("\tValor Cobrado\n\n" + inscricaoService.getPrecoInscricao(inscricao) , fonteDesc));
            document.add(table2);

            PdfPTable table3 = new PdfPTable(2);//numero de colunas
            table3.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table3.setWidths(widths);
            table3.getDefaultCell().setGrayFill(10f);
            table3.addCell(new Phrase("Nome do Participante\n\n" + inscricao.getParticipante().getUsuario().getNome(), fonteDesc));
            table3.addCell(new Phrase("Cod. Inscricão\n\n" + inscricao.getId() , fonteDesc));
            document.add(table3);


            PdfPTable table4 = new PdfPTable(1);//numero de colunas
            table4.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f};//espaço entre as colunas
            table4.setWidths(widths);
            table4.getDefaultCell().setGrayFill(10f);
            String conjNomes = "";
            for (Atividade ativs : inscricao.getAtividades()) {

                conjNomes += ativs.getTipo().getNome() + " :" + ativs.getNome() + "\n";

            }
            table4.addCell(new Phrase("Atividades\n\n" + conjNomes, fonteDesc));

            document.add(table4);

            PdfPTable table6 = new PdfPTable(1);//numero de colunas
            table6.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f};//espaço entre as colunas
            table6.setWidths(widths);
            table6.getDefaultCell().setGrayFill(10f);
            table6.addCell(new Phrase("Cedente\n\n" + "UFC - Universidade Federal do Ceará", fonteDesc));
            document.add(table6);
            //--------------SEGUNDA TELA-----------------------------------------------------------------
            PdfPTable table14 = new PdfPTable(1);
            table14.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            table14.setWidths(width);
            table14.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table14.addCell(new Phrase("\n\n\n\n", fonteConteudo));
            document.add(table14);

            PdfPTable table13 = new PdfPTable(1);
            table13.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            table13.setWidths(width);
            table13.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table13.addCell(new Phrase("__________________________________________________________________________________\n", fonteConteudo));
            document.add(table13);


            PdfPTable table12 = new PdfPTable(1);
            table12.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            table12.setWidths(width);
            table12.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table12.addCell(new Phrase("\n\n\n\n", fonteConteudo));
            document.add(table12);

            PdfPTable table5 = new PdfPTable(1);
            float[] width1 = {0.85f};
            table5.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            table5.setWidths(width1);
            table5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table5.addCell(new Phrase("Comprovante de Pagamento", fonteConteudo));
            document.add(table5);

            /* Corpo do pdf. */
            PdfPTable table8 = new PdfPTable(2);//numero de colunas
            table8.setSpacingBefore(5f); /* Coloca um espaço antes da tabela. */
            table8.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table8.setWidths(widths);
            table8.getDefaultCell().setGrayFill(10f);
            table8.addCell(new Phrase("Local de Pagamento\n\n" + "UFC-Campus Quixadá-PET", fonteDesc));
            table8.addCell(new Phrase("\tVencimento\n\n" + UtilSeven.treatToString(inscricao.getDataPagamento()), fonteDesc));

            document.add(table8);

            PdfPTable table7 = new PdfPTable(2);//numero de colunas
            table7.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table7.setWidths(widths);
            table7.getDefaultCell().setGrayFill(10f);
            table7.addCell(new Phrase("Data da Inscrição\n\n" + UtilSeven.treatToString(inscricao.getDataRealizada()), fonteDesc));
            table7.addCell(new Phrase("\tValor Cobrado\n\n" + inscricaoService.getPrecoInscricao(inscricao) , fonteDesc));
            document.add(table7);

            PdfPTable table9 = new PdfPTable(2);//numero de colunas
            table9.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f, 0.05f};//espaço entre as colunas
            table9.setWidths(widths);
            table9.getDefaultCell().setGrayFill(10f);
            table9.addCell(new Phrase("Nome do Participante\n\n" + inscricao.getParticipante().getUsuario().getNome(), fonteDesc));
            table9.addCell(new Phrase("Cod. Inscricão\n\n" + inscricao.getId() , fonteDesc));
            document.add(table9);


            PdfPTable table10 = new PdfPTable(1);//numero de colunas
            table10.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f};//espaço entre as colunas
            table10.setWidths(widths);
            table10.getDefaultCell().setGrayFill(10f);
            String conjNomes2 = "";
            for (Atividade ativs2 : inscricao.getAtividades()) {

                conjNomes2 += ativs2.getTipo().getNome() + " :" + ativs2.getNome() + "\n";

            }
            table10.addCell(new Phrase("Atividades\n\n" + conjNomes2, fonteDesc));

            document.add(table10);

            PdfPTable table11 = new PdfPTable(1);//numero de colunas
            table11.setWidthPercentage(100); /* Seta a largura da tabela com relação a página. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[]{0.25f};//espaço entre as colunas
            table11.setWidths(widths);
            table11.getDefaultCell().setGrayFill(10f);
            table11.addCell(new Phrase("Cedente\n\n" + "UFC - Universidade Federal do Ceará", fonteDesc));
            document.add(table11);

            //--------------------------------------------------------------------------------------

            document.close();
            // escreve o pdf no servlet
            response.setContentLength(baos.size());
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
                session.setAttribute("men", "Erro " + ex.getMessage());
                return "/part/part_gerar_boleto_pagamento.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("men", "Erro " + ex.getMessage());
                return "/part/part_gerar_boleto_pagamento.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.setAttribute("men", "Erro " + ex.getMessage());
            return "/part/part_gerar_boleto_pagamento.jsp";
        }
        return "/part/part_gerar_boleto_pagamento.jsp";
    }


}
