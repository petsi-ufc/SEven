package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.ModalidadeInscricao;
import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ModalidadeInscricaoService;
import br.ufc.pet.services.TipoAtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdEditarInscricao implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //preparando os argumentos necessários para o redirecionamento até a pagina de nova inscrição.
        Inscricao i = (Inscricao) session.getAttribute("inscricao");
        session.setAttribute("eventoSelecionado", i.getEvento());

        ModalidadeInscricaoService ms = new ModalidadeInscricaoService();
        ArrayList<ModalidadeInscricao> modalidades = ms.getModalidadesInscricaoByEventoId(i.getEvento().getId());
        session.setAttribute("modalidades", modalidades);

        TipoAtividadeService ts = new TipoAtividadeService();
        ArrayList<TipoAtividade> arrayDeTipos= ts.getTiposDeAtividadesByEventoId(i.getEvento().getId());
        session.setAttribute("arrayDeTipos", arrayDeTipos);

        return "/part/part_fazer_inscricao.jsp";
        //a edição, na verdade, criará uma nova inscrição após deletar a antiga,
        //uma vez que é preciso excluir todos os relacionamentos na tabela 'inscricao_atividade' da antiga
        //e checar as vagas dos novos relacionamentos.
        //desta forma, será usada a mesma pagina de nova inscrição normalmente, com alguns atributos ja setados.
        //sendo que os comandos detectam caso se trate de uma edição,
        //devido ao fato de que as novas inscricoes ainda nao tem ID setado, mas as edições sim.
        //o CmdSubmeterInscricao é responsável por substituir a antiga pela nova.
    }

}
