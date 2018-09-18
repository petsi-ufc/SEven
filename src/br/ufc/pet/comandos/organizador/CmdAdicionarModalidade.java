package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.ModalidadeInscricao;
import br.ufc.pet.entity.PrecoAtividade;
import br.ufc.pet.entity.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ModalidadeInscricaoService;
import br.ufc.pet.util.UtilSeven;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Franklin
 */
public class CmdAdicionarModalidade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String nomeModalidade = request.getParameter("nomeModalidade").trim();
        Evento evento = (Evento) session.getAttribute("evento");
        ModalidadeInscricao modalidade = new ModalidadeInscricao();
        ModalidadeInscricaoService ms = new ModalidadeInscricaoService();

        
        if(nomeModalidade == null || nomeModalidade.equals("") || request.getParameter("nomeModalidade").equals("")){
            session.setAttribute("erro", "Preencha a modalidade com um nome!");
            return "/org/organ_add_modalidade.jsp";
        }
        
        ArrayList<TipoAtividade> tipos = UtilSeven.getTiposDeAtividadeByEventoId(evento.getId());
        String id = request.getParameter("id_atualizar");
        
        
        if (id != null && id.isEmpty() == false) {
            modalidade = ms.getModalidadeInscricaoById(Long.parseLong(id));
            modalidade.setTipo(nomeModalidade);
            modalidade.setEventoId(evento.getId());
            ArrayList<PrecoAtividade> precos = new ArrayList<PrecoAtividade>();

            for (TipoAtividade t : tipos) {
                PrecoAtividade preco = new PrecoAtividade();
                preco.setModalidadeId(modalidade.getId());
                preco.setTipoAtividadeId(t.getId());
                try{
                    preco.setValor(Double.parseDouble( (request.getParameter("preco_" + t.getId().toString())).replace(",", ".")));
                } catch(NumberFormatException e){
                    session.setAttribute("erro", "Valor inválido!");
                    return "/org/organ_add_modalidade.jsp";
                }
                precos.add(preco);
            }
            modalidade.setPrecoAtividades(precos);
            ms.atualizar(modalidade);
            session.setAttribute("sucesso", "Modalidade atualizada com sucesso!");

        } else {
            boolean naoInseriri=false;            
            ArrayList<ModalidadeInscricao> mods= ms.getModalidadesInscricaoByEventoId(evento.getId());
            for(ModalidadeInscricao m: mods){
            if(m.getTipo().equalsIgnoreCase(nomeModalidade)){
            naoInseriri=true;
            }
            }
            if (naoInseriri) {
                session.setAttribute("erro", "Já existe uma modalidade cadastrada com esse nome!");
                return "/org/organ_add_modalidade.jsp";


            }
            modalidade.setTipo(nomeModalidade);
            modalidade.setEventoId(evento.getId());
            ArrayList<PrecoAtividade> precos = new ArrayList<PrecoAtividade>();
            for (TipoAtividade t : tipos) {
                PrecoAtividade precoAtiv = new PrecoAtividade();
                precoAtiv.setTipoAtividadeId(t.getId());
                try{
                    if(evento.isGratuito()){
                        precoAtiv.setValor(0.0);
                    }else{
                        precoAtiv.setValor(Double.parseDouble( (request.getParameter("preco_" + t.getId().toString())).replace(",", ".")));

                    }
                } catch(NumberFormatException e){
                    session.setAttribute("erro", "Valor inválido!");
                    return "/ServletCentral?comando=CmdListarTipoModalidade";
                }
                precos.add(precoAtiv);
            }
            modalidade.setPrecoAtividades(precos);
            ms.adicionar(modalidade);
            session.setAttribute("sucesso", "Modalidade adicionada com sucesso!");
        }
        return "/ServletCentral?comando=CmdListarTipoModalidade";
    }
}
