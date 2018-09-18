package br.ufc.pet.comandos;

import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.UsuarioService;
import br.ufc.pet.util.SendMail;
import br.ufc.pet.util.UtilSeven;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdRecuperarSenha implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        UsuarioService us = new UsuarioService();
        Usuario usuario = us.getByEmail(email.trim());
        HttpSession session= request.getSession(true);
        String mensagem;
        if (usuario == null) {
            mensagem = "Caro Usuário o seu e-mail não está cadastrado em nossa base de dados! Caso deseje acessar nosso sistema efetue seu cadastro! Obrigado";
        } else {
            //Gera uma nova senha
            String senha = gerarSenha();
            usuario.setSenha(UtilSeven.criptografar(senha));

            //Salvar o cara
            us.update(usuario);
            mensagem = "Prezado(a) " + usuario.getNome() + ",\n "
                    + "A sua senha temporária é: " + senha + "\n "
                    + "Por favor, altere sua senha ao acessar o sistema.";
        }
        try {
            SendMail.sendMail(email, "SENHA DO SEVEN!", mensagem);
            session.setAttribute("sucesso","Sua senha foi enviada com sucesso para o e-mail: "+email);

        } catch (Exception ex) {
            System.err.println("Erro ao enviar o seu email!");
        }
        return "/index.jsp";
    }

    public String gerarSenha(){

        String senha = "";

        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";

        Random random = new Random();

        for( int i = 0; i < 8; i++ ) {
            int index = random.nextInt( letras.length() );
            senha += letras.substring( index, index + 1 );
        }

        return senha;
    }

}
