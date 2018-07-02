package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Participante;
import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.ParticipanteService;
import br.ufc.pet.services.UsuarioService;
import br.ufc.pet.util.UtilSeven;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdEditarParticipante implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Participante part = (Participante) session.getAttribute("user");
        
        UsuarioService us = new UsuarioService();
        //Recuperar dados do formulario.
        String nome = request.getParameter("nome");
        if(part.getUsuario().isCertificadoGerado()){
            Usuario tmp = us.getById(part.getUsuario().getId());
            nome = tmp.getNome();
        }
        String fone = request.getParameter("fone");
        String dataNascimentoS = request.getParameter("dt_nascimento");
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        String instituicao = request.getParameter("instituicao");
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        String numero = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
//        String senha = request.getParameter("senha");
//        String confSenha = request.getParameter("r-senha");
//        String tempoldsenha = request.getParameter("oldsenha");
        
        if (nome == null || nome.trim().equals("") || email == null || email.trim().equals("")){
//                || senha == null || senha.trim().equals("") || confSenha == null || confSenha.trim().equals("") || tempoldsenha.equals("")) {
            session.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/part/part_conta.jsp";
        }

//        if (!senha.trim().equals(confSenha)) {
//            session.setAttribute("erro", "A senha não confere com a sua confirmação.");
//            return "/part/part_conta.jsp";
//        }

        //Validar a insercao
        Usuario temp = us.getByEmail(email);
        if (temp != null) {
            if (!temp.getId().equals(part.getUsuario().getId())) {
                session.setAttribute("erro", "E-Mail já cadastrado.");
                return "/part/part_conta.jsp";
            }
//            if(!temp.getSenha().equals(senha)){
//                senha = UtilSeven.criptografar(senha);
//            }
//            String oldsenha = UtilSeven.criptografar(tempoldsenha);
//            if(!oldsenha.equals(temp.getSenha())){
//                session.setAttribute("erro", "Senha Antiga incorreta!");
//                return "/part/part_conta.jsp";
//            }
        }

        part.getUsuario().setBairro(bairro);
        part.getUsuario().setCidade(cidade);
        part.getUsuario().setEmail(email);
        part.getUsuario().setFone(fone);
        part.getUsuario().setInstituicao(instituicao);
        part.getUsuario().setNome(nome);
        part.getUsuario().setNumero(numero);
        part.getUsuario().setRua(rua);
//        part.getUsuario().setSenha(senha);
        part.getUsuario().setSexo(sexo);
        part.getUsuario().setUf(uf);
        if (dataNascimentoS != null && !dataNascimentoS.trim().isEmpty()) {
            Date dataNascimento = UtilSeven.treatToDate(dataNascimentoS);
            part.getUsuario().setDataNascimento(dataNascimento);
        }

        if (us.updateSemSenha(part.getUsuario())) {
            session.setAttribute("user", part);
            EventoService es = new EventoService();
            session.setAttribute("eventosAbertos", es.buscarEventosComInscricoesAbertas());
            session.setAttribute("sucesso", "Dados Editados com Sucesso.");
            return "/part/gerencia_conta.jsp";
        } else {
            session.setAttribute("erro", "Erro ao tentar cadastrar participante.");
            return "/part/part_conta.jsp";
        }
    }
}
