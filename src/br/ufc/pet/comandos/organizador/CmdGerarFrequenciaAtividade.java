package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Horario;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.services.UsuarioService;
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
public class CmdGerarFrequenciaAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        
        String caminhoImagem ="/SEVEN_ARQUIVOS/templates_certificados_upload/UFC.png";
           
        Long id = Long.parseLong(request.getParameter("idAtv"));
        Atividade at = new AtividadeService().getAtividadeById(id); //pega a atividade pelo id
        at.setEvento(new EventoService().getEventoById(at.getEvento().getId())); //seta o evento na atividade
        ParticipanteService partser = new ParticipanteService();
        ArrayList<Participante> parts = partser.getParticipanteByAtividadeId(id);        
        
        
        if (parts == null || parts.isEmpty()) {
            session.setAttribute("erro", "Sem participantes no Momento");
            return "/org/organ_listar_atividades_frequencia.jsp";
        }
        
        UsuarioService us = new UsuarioService();
        ArrayList<Horario> horarios = at.getHorarios();
        if (horarios == null || horarios.isEmpty()) {
            session.setAttribute("erro", "Horários não definidos!");
            return "/org/organ_listar_atividades_frequencia.jsp";
        }
        int nHorario = horarios.size();
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
//        caminho na minha mÃ¡quina
            Image jpg = Image.getInstance(caminhoImagem);
            jpg.setAlignment(Image.LEFT | Image.UNDERLYING); /* Ajusta o alinhamento da imagem. */

            /* Fontes */
            Font fonteCabecalho = new Font(Font.TIMES_ROMAN, 12, Font.BOLD); /* SerÃ¡ usada no cabeÃ§alho. */
            Font fonteDesc = new Font(Font.HELVETICA, 10, Font.BOLD); /* SerÃ¡ usada na descriÃ§Ã£o. */
            Font fonteConteudo = new Font(Font.HELVETICA, 10, Font.NORMAL); /* SerÃ¡ usada no corpo de relatÃ³rio. */
            Font fonteRodaPe = new Font(Font.ITALIC, 8, Font.NORMAL); /* SerÃ¡ usada no rodapÃ© de relatÃ³rio. */
            /* Tabela para o cabeÃ§alho. */
            PdfPTable cabecalho = new PdfPTable(2);
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /* Seta a largura da tabela com relaÃ§Ã£o a pÃ¡gina. */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Sistema de Eventos\n"
                    + "Lista de Frequência", fonteCabecalho));
            document.add(cabecalho); /* Adicionando ao documento. */
            try {
                /* Adicionando ao documento. */ 
                document.add(getCabecalhoAtividade(at, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_frequencia.jsp";
            }

            /* Corpo do pdf. */
            PdfPTable table = new PdfPTable(2 + nHorario);
            table.setSpacingBefore(5f); /* Coloca um espaÃ§o antes da tabela. */
            table.setWidthPercentage(100); /* Seta a largura da tabela com relaÃ§Ã£o a pÃ¡gina. */
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            widths = new float[2 + nHorario];
            for (int i = 0; i < 2 + nHorario; i++) {
                if (i == 0) {
                    widths[i] = 0.07f;
                } else if (i == 1) {
                    widths[i] = 0.50f;
                } else {
                    widths[i] = 0.12f;
                }
            }
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(0.5f);
            table.addCell(new Phrase("Nº", fonteDesc));
            table.addCell(new Phrase("Nome", fonteDesc));
            for (int i = 0; i < nHorario; i++) {
                String data = UtilSeven.treatToString(horarios.get(i).getDia());
                int hInicial = horarios.get(i).getHoraInicial();
                String horaInicial = "" + hInicial;
                if (hInicial <= 9 && hInicial >= 0) {
                    horaInicial = "0" + hInicial;
                }
                int hFinal = horarios.get(i).getHoraFinal();
                String horaFinal = "" + hFinal;
                if (hFinal <= 9 && hFinal >= 0) {
                    horaFinal = "0" + hFinal;
                }
                int mInicial = horarios.get(i).getMinutoInicial();
                String minInicial = "" + mInicial;
                if (mInicial <= 9 && mInicial >= 0) {
                    minInicial = "0" + mInicial;
                }
                int mFinal = horarios.get(i).getMinutoFinal();
                String minFinal = "" + mFinal;
                if (mFinal <= 9 && mFinal >= 0) {
                    minFinal = "0" + minFinal;
                }

                table.addCell(new Phrase("" + data + "\n" + horaInicial + ":" + minInicial + " às " + horaFinal + ":" + minFinal + "", fonteDesc));
            }
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
                for (int j = 0; j < nHorario; j++) {
                    table.addCell(new Phrase("", fonteConteudo));
                }
            }
            document.add(table);
            try {
                document.add(getRodaPeAtividade(at, fonteRodaPe));
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_frequencia.jsp";
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
                return "/org/organ_listar_atividades_frequencia.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/org/organ_listar_atividades_frequencia.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.setAttribute("erro", "Erro " + ex.getMessage());
            return "/org/organ_listar_atividades_frequencia.jsp";
        }
        return "/org/organ_listar_atividades_frequencia.jsp";
    }

    public PdfPTable getCabecalhoAtividade(Atividade a, Font fonteDesc) throws Exception {
        /* Tabela de descriÃ§Ã£o do relatÃ³rio. */
        PdfPTable desc = new PdfPTable(3);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relaÃ§Ã£o a pÃ¡gina. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Cod.: " + a.getId(), fonteDesc));//pega o id da atividade
        desc.addCell(new Phrase("Local: " + a.getLocal(), fonteDesc)); //pega o local da atividade
        desc.addCell(new Phrase(10f, "Evento: " + a.getEvento().getNome(), fonteDesc));//pega o nome do evento
        desc.addCell(new Phrase("Vagas: " + a.getVagas(), fonteDesc)); //o nÃºmero de vagas
        desc.addCell(new Phrase("Tipo: " + a.getTipo().getNome(), fonteDesc));//o tipo da atividade
        desc.addCell(new Phrase("Nome: " + a.getNome(), fonteDesc)); //o nome da atividade
        return desc;
    }

    public PdfPTable getRodaPeAtividade(Atividade a, Font fonteRodaPe) throws Exception {
        /* Tabela de descriÃ§Ã£o do relatÃ³rio. */
        PdfPTable desc = new PdfPTable(1);
        float[] widths = new float[]{0.20f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /* Seta a largura da tabela com relaÃ§Ã£o a pÃ¡gina. */
        desc.setWidths(widths);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Frequência da Atividade :" + a.getNome(), fonteRodaPe)); //o nome da atividade
        desc.addCell(new Phrase("Gerada em :" + UtilSeven.treatToString(new Date()), fonteRodaPe));
        return desc;
    }
}
