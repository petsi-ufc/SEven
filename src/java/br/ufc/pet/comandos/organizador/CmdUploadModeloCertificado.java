package br.ufc.pet.comandos.organizador;

import br.ufc.pet.interfaces.Comando;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/*
 * @author salomao
 */
public class CmdUploadModeloCertificado implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession(true);
        
        String idEvento = request.getParameter("id_evento");

        
        String path = "/SEVEN_ARQUIVOS/templates_certificados_upload/"+idEvento;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        if (!isMultipart) {
             session.setAttribute("erro", "Nenhum arquivo selecionado!");
            return "/org/organ_gerenciar_upload_certificados.jsp";
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(request);

            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (!item.isFormField()) {

                    File uploadedFile = new File(path);
                    item.write(uploadedFile);
                }
            }
           
        } catch (Exception e) {
            session.setAttribute("erro", "Erro ao fazer upload do arquivo!");
            return "/org/organ_gerenciar_upload_certificados.jsp";
        }
        session.setAttribute("sucesso", "Upload feito com sucesso!");
        return "/org/organ_gerenciar_atividades.jsp";
    }

        
}
