package br.ufc.pet.servlets;

import br.ufc.pet.comandos.CmdCadastrarParticipante;
import br.ufc.pet.comandos.CmdCheckUserLogged;
import br.ufc.pet.comandos.CmdLogin;
import br.ufc.pet.comandos.CmdLogout;
import br.ufc.pet.comandos.CmdRecuperarSenha;
import br.ufc.pet.comandos.CmdValidarDocumento;
import br.ufc.pet.comandos.CmdVisualizarTodasProgramacoes;
import br.ufc.pet.comandos.administrador.CmdAddOrganizadorNovo;
import br.ufc.pet.comandos.administrador.CmdAdicionarEvento;
import br.ufc.pet.comandos.administrador.CmdAlterarEvento;
import br.ufc.pet.comandos.administrador.CmdAtivarEvento;
import br.ufc.pet.comandos.administrador.CmdBuscarEvento;
import br.ufc.pet.comandos.administrador.CmdBuscarUsuario;
import br.ufc.pet.comandos.administrador.CmdEditAdmin;
import br.ufc.pet.comandos.administrador.CmdEditarOrganizador;
import br.ufc.pet.comandos.administrador.CmdEncerrarEvento;
import br.ufc.pet.comandos.administrador.CmdExcluirEvento;
import br.ufc.pet.comandos.administrador.CmdExcluirOrganizador;
import br.ufc.pet.comandos.administrador.CmdExibirOrganizadorEditar;
import br.ufc.pet.comandos.administrador.CmdListarEventos;
import br.ufc.pet.comandos.administrador.CmdListarOrganizadorEventos;
import br.ufc.pet.comandos.administrador.CmdListarUsuarioEditar;
import br.ufc.pet.comandos.organizador.CmdAddMovimentacaoFinanceira;
import br.ufc.pet.comandos.organizador.CmdAdicionarAtividade;
import br.ufc.pet.comandos.organizador.CmdAdicionarHorario;
import br.ufc.pet.comandos.organizador.CmdAdicionarModalidade;
import br.ufc.pet.comandos.organizador.CmdAdicionarTipoAtividade;
import br.ufc.pet.comandos.organizador.CmdAlterarPeriodoInscricaoeEvento;
import br.ufc.pet.comandos.organizador.CmdAtualizarInscricao;
import br.ufc.pet.comandos.organizador.CmdAtualizarModalidade;
import br.ufc.pet.comandos.organizador.CmdBuscarInscricao;
import br.ufc.pet.comandos.organizador.CmdBuscarParticipantePorEmail;
import br.ufc.pet.comandos.organizador.CmdBuscarParticipantedeEvento;
import br.ufc.pet.comandos.organizador.CmdBuscarUsuarioResponsavel;
import br.ufc.pet.comandos.organizador.CmdCadastrarUsuarioResponsavel;
import br.ufc.pet.comandos.organizador.CmdConfirmarLiberacaoCertificado;

import br.ufc.pet.comandos.organizador.CmdEditarAtividade;
import br.ufc.pet.comandos.organizador.CmdEditarHorario;
import br.ufc.pet.comandos.organizador.CmdEditarResponsavel;
import br.ufc.pet.comandos.organizador.CmdEditarTipoAtividade;
import br.ufc.pet.comandos.organizador.CmdExcluirAtividade;
import br.ufc.pet.comandos.organizador.CmdExcluirHorario;
import br.ufc.pet.comandos.organizador.CmdExcluirModalidade;
import br.ufc.pet.comandos.organizador.CmdExcluirMovimentacaoFinanceira;
import br.ufc.pet.comandos.organizador.CmdExcluirTipoAtividade;
import br.ufc.pet.comandos.organizador.CmdGerarCertificado;
import br.ufc.pet.comandos.organizador.CmdGerarFrequenciaAtividade;
import br.ufc.pet.comandos.organizador.CmdGerarFrequenciaAtividadeDownloadHtml;
import br.ufc.pet.comandos.organizador.CmdGerenciarEmissaoCertificados;
import br.ufc.pet.comandos.organizador.CmdGerenciarEvento;
import br.ufc.pet.comandos.organizador.CmdGerenciarInscricoes;
import br.ufc.pet.comandos.organizador.CmdGerenciarLiberacaoCertificadoAtividade;
import br.ufc.pet.comandos.organizador.CmdGerenciarUploadCertificados;
import br.ufc.pet.comandos.organizador.CmdIncluirResponsavel;
import br.ufc.pet.comandos.organizador.CmdListarAtividadeFrequencia;
import br.ufc.pet.comandos.organizador.CmdListarAtividades;
import br.ufc.pet.comandos.organizador.CmdListarAtividadesDownload;
import br.ufc.pet.comandos.organizador.CmdListarHorarios;
import br.ufc.pet.comandos.organizador.CmdListarInscritosEmAtividade;
import br.ufc.pet.comandos.organizador.CmdListarMovimentacaoFinanceira;
import br.ufc.pet.comandos.organizador.CmdListarParticipanteCertificado;
import br.ufc.pet.comandos.organizador.CmdListarParticipantes;
import br.ufc.pet.comandos.organizador.CmdListarTipoAtividade;
import br.ufc.pet.comandos.organizador.CmdListarTipoModalidade;
import br.ufc.pet.comandos.organizador.CmdMontarPaginaAdicaoAtividade;
import br.ufc.pet.comandos.organizador.CmdOrgRemoverAtividade;
import br.ufc.pet.comandos.organizador.CmdOrgSelecionarAtividade;
import br.ufc.pet.comandos.organizador.CmdOrgSubmeterInscricao;
import br.ufc.pet.comandos.organizador.CmdOrganExcluirInscricao;
import br.ufc.pet.comandos.organizador.CmdRecarregarEventosOrganizador;
import br.ufc.pet.comandos.organizador.CmdReceberPagamento;
import br.ufc.pet.comandos.organizador.CmdReceberPagamentoTodasInscricoes;
import br.ufc.pet.comandos.organizador.CmdRelatorioParticipanteAtividade;
import br.ufc.pet.comandos.organizador.CmdRelatorioParticipantesQuites;
import br.ufc.pet.comandos.organizador.CmdRemoverResponsavelAtividade;
import br.ufc.pet.comandos.organizador.CmdSelecionarResponsavelEdicao;
import br.ufc.pet.comandos.organizador.CmdUpdateMovimentacaoFinanceira;
import br.ufc.pet.comandos.organizador.CmdUploadModeloCertificado;
import br.ufc.pet.comandos.organizador.CmdVisualizarAtividade;
import br.ufc.pet.comandos.participante.CmdEditPart;
import br.ufc.pet.comandos.participante.CmdEditarInscricao;
import br.ufc.pet.comandos.participante.CmdEditarParticipante;
import br.ufc.pet.comandos.participante.CmdExcluirInscricao;
import br.ufc.pet.comandos.participante.CmdExcluirParticipante;
import br.ufc.pet.comandos.participante.CmdGerarBoletoPagamento;
import br.ufc.pet.comandos.participante.CmdListarEventosAbertos;
import br.ufc.pet.comandos.participante.CmdMontarInscricao;
import br.ufc.pet.comandos.participante.CmdRemoverAtividade;
import br.ufc.pet.comandos.participante.CmdSelecionarAtividade;
import br.ufc.pet.comandos.participante.CmdSelecionarEvento;
import br.ufc.pet.comandos.participante.CmdSubmeterInscricao;
import br.ufc.pet.comandos.participante.CmdVisualizarInscricao;
import br.ufc.pet.comandos.participante.CmdVisualizarInscricaoDownload;
import br.ufc.pet.comandos.participante.CmdVisualizarProgramacao;
import br.ufc.pet.comandos.participante.CmdGerarCertificadoParticipante;

import br.ufc.pet.interfaces.Comando;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Escritorio projetos
 */
public class ServletCentral extends HttpServlet {

    private Hashtable comandos;
    private boolean debug = true;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("comando");
        Comando comando = (Comando) comandos.get(cmd);

        try {
            String tela = comando.executa(request, response);
            if (tela != null && !tela.trim().equals("")) {
                if (debug) {

                    System.out.print("Tela:" + tela + " - ");
                    System.out.print("IP Máquima" + request.getRemoteAddr() + " - ");
//                    System.out.println("Hora" + new Date());
                }
                response.sendRedirect(request.getContextPath() + tela);
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        }
    }

    public void init() {
        Comando cmdo;
        comandos = new Hashtable();
        cmdo = new CmdCheckUserLogged();
        comandos.put("CmdCheckLogged", cmdo);
        cmdo = new CmdLogin();
        comandos.put("CmdLogin", cmdo);
        cmdo = new CmdAdicionarEvento();
        comandos.put("CmdAdicionarEvento", cmdo);
        cmdo = new CmdAlterarEvento();
        comandos.put("CmdAlterarEvento", cmdo);
        cmdo = new CmdExcluirEvento();
        comandos.put("CmdExcluirEvento", cmdo);
        cmdo = new CmdListarOrganizadorEventos();
        comandos.put("CmdListarOrganizadorEventos", cmdo);
        cmdo = new CmdBuscarUsuario();
        comandos.put("CmdBuscarUsuario", cmdo);
        cmdo = new CmdListarUsuarioEditar();
        comandos.put("CmdListarUsuarioEditar", cmdo);
        cmdo = new CmdAddOrganizadorNovo();
        comandos.put("CmdAddOrganizadorNovo", cmdo);
        cmdo = new CmdEditarOrganizador();
        comandos.put("CmdEditarOrganizador", cmdo);
        cmdo = new CmdExcluirOrganizador();
        comandos.put("CmdExcluirOrganizador", cmdo);
        cmdo = new CmdExibirOrganizadorEditar();
        comandos.put("CmdExibirOrganizadorEditar", cmdo);
        cmdo = new CmdListarEventosAbertos();
        comandos.put("CmdListarEventosAbertos", cmdo);
        cmdo = new CmdSelecionarEvento();
        comandos.put("CmdSelecionarEvento", cmdo);
        cmdo = new CmdMontarInscricao();
        comandos.put("CmdMontarInscricao", cmdo);
        cmdo = new CmdSubmeterInscricao();
        comandos.put("CmdSubmeterInscricao", cmdo);
        cmdo = new CmdListarMovimentacaoFinanceira();
        comandos.put("CmdListarMovimentacaoFinanceira", cmdo);
        cmdo = new CmdAddMovimentacaoFinanceira();
        comandos.put("CmdAddMovimentacaoFinanceira", cmdo);
        cmdo = new CmdExcluirMovimentacaoFinanceira();
        comandos.put("CmdExcluirMovimentacaoFinanceira", cmdo);
        cmdo = new CmdUpdateMovimentacaoFinanceira();
        comandos.put("CmdUpdateMovimentacaoFinanceira", cmdo);
        cmdo = new CmdSelecionarAtividade();
        comandos.put("CmdSelecionarAtividade", cmdo);
        cmdo = new CmdCadastrarParticipante();
        comandos.put("CmdCadastrarParticipante", cmdo);
        cmdo = new CmdListarAtividadesDownload();
        comandos.put("CmdListarAtividadesDownload", cmdo);
        cmdo = new CmdListarAtividadeFrequencia();
        comandos.put("CmdListarAtividadeFrequencia", cmdo);
        
        cmdo = new CmdRelatorioParticipanteAtividade();
        comandos.put("CmdRelatorioParticipanteAtividade", cmdo);
        
        cmdo = new CmdGerarFrequenciaAtividade();
        comandos.put("CmdGerarFrequenciaAtividade", cmdo);
        
        cmdo = new CmdSelecionarAtividade();
        comandos.put("CmdSelecionarAtividade", cmdo);
        cmdo = new CmdRemoverAtividade();
        comandos.put("CmdRemoverAtividade", cmdo);
        cmdo = new CmdListarAtividades();
        comandos.put("CmdListarAtividades", cmdo);
        cmdo = new CmdAdicionarAtividade();
        comandos.put("CmdAdicionarAtividade", cmdo);
        cmdo = new CmdEditarAtividade();
        comandos.put("CmdEditarAtividade", cmdo);
        cmdo = new CmdExcluirAtividade();
        comandos.put("CmdExcluirAtividade", cmdo);
        cmdo = new CmdLogout();
        comandos.put("CmdLogout", cmdo);
        cmdo = new CmdGerenciarEvento();
        comandos.put("CmdGerenciarEvento", cmdo);
        cmdo = new CmdBuscarUsuarioResponsavel();
        comandos.put("CmdBuscarUsuarioResponsavel", cmdo);
        cmdo = new CmdIncluirResponsavel();
        comandos.put("CmdIncluirResponsavel", cmdo);
        cmdo = new CmdMontarPaginaAdicaoAtividade();
        comandos.put("CmdMontarPaginaAdicaoAtividade", cmdo);
        cmdo = new CmdListarEventos();
        comandos.put("CmdListarEventos", cmdo);
        cmdo = new CmdVisualizarInscricaoDownload();
        comandos.put("CmdVisualizarInscricaoDownload", cmdo);
        cmdo = new CmdGerarBoletoPagamento();
        comandos.put("CmdGerarBoletoPagamento", cmdo);
        cmdo = new CmdRecarregarEventosOrganizador();
        comandos.put("CmdRecarregarEventosOrganizador", cmdo);
        cmdo = new CmdEditarInscricao();
        comandos.put("CmdEditarInscricao", cmdo);
        cmdo = new CmdVisualizarInscricao();
        comandos.put("CmdVisualizarInscricao", cmdo);
        cmdo = new CmdRemoverResponsavelAtividade();
        comandos.put("CmdRemoverResponsavelAtividade", cmdo);
        cmdo = new CmdCadastrarUsuarioResponsavel();
        comandos.put("CmdCadastrarUsuarioResponsavel", cmdo);
        cmdo = new CmdRelatorioParticipantesQuites();
        comandos.put("CmdRelatorioParticipantesQuites", cmdo);
        cmdo = new CmdVisualizarAtividade();
        comandos.put("CmdVisualizarAtividade", cmdo);
        cmdo = new CmdBuscarParticipantedeEvento();
        comandos.put("CmdBuscarParticipantedeEvento", cmdo);
        cmdo = new CmdReceberPagamento();
        comandos.put("CmdReceberPagamento", cmdo);
        cmdo = new CmdAdicionarHorario();
        comandos.put("CmdAdicionarHorario", cmdo);
        cmdo = new CmdListarHorarios();
        comandos.put("CmdListarHorarios", cmdo);
        cmdo = new CmdExcluirHorario();
        comandos.put("CmdExcluirHorario", cmdo);
        cmdo = new CmdEditarHorario();
        comandos.put("CmdEditarHorario", cmdo);
        cmdo = new CmdRecuperarSenha();
        comandos.put("CmdRecuperarSenha", cmdo);
        cmdo = new CmdEncerrarEvento();
        comandos.put("CmdEncerrarEvento", cmdo);
        cmdo = new CmdAtivarEvento();
        comandos.put("CmdAtivarEvento", cmdo);
        cmdo = new CmdListarTipoAtividade();
        comandos.put("CmdListarTipoAtividade", cmdo);
        cmdo = new CmdAdicionarTipoAtividade();
        comandos.put("CmdAdicionarTipoAtividade", cmdo);
        cmdo = new CmdEditarTipoAtividade();
        comandos.put("CmdEditarTipoAtividade", cmdo);
        cmdo = new CmdExcluirTipoAtividade();
        comandos.put("CmdExcluirTipoAtividade", cmdo);
        cmdo = new CmdVisualizarProgramacao();
        comandos.put("CmdVisualizarProgramacao", cmdo);
        cmdo = new CmdAdicionarModalidade();
        comandos.put("CmdAdicionarModalidade", cmdo);
        cmdo = new CmdListarTipoModalidade();
        comandos.put("CmdListarTipoModalidade", cmdo);
        cmdo = new CmdExcluirModalidade();
        comandos.put("CmdExcluirModalidade", cmdo);
        cmdo = new CmdAtualizarModalidade();
        comandos.put("CmdAtualizarModalidade", cmdo);
        cmdo = new CmdReceberPagamentoTodasInscricoes();
        comandos.put("CmdReceberPagamentoTodasInscricoes", cmdo);

        cmdo = new CmdGerarFrequenciaAtividadeDownloadHtml();
        comandos.put("CmdGerarFrequenciaAtividadeDownloadHtml", cmdo);
        cmdo = new CmdVisualizarTodasProgramacoes();
        comandos.put("CmdVisualizarTodasProgramacoes", cmdo);
        cmdo = new CmdListarParticipanteCertificado();
        comandos.put("CmdListarParticipanteCertificado", cmdo);

        cmdo = new CmdEditarParticipante();
        comandos.put("CmdEditarParticipante", cmdo);
        cmdo = new CmdExcluirParticipante();
        comandos.put("CmdExcluirParticipante", cmdo);
        cmdo = new CmdExcluirInscricao();
        comandos.put("CmdExcluirInscricao", cmdo);
        cmdo = new CmdGerenciarInscricoes();
        comandos.put("CmdGerenciarInscricoes", cmdo);
        cmdo = new CmdListarInscritosEmAtividade();
        comandos.put("CmdListarInscritosEmAtividade", cmdo);
        cmdo = new CmdOrganExcluirInscricao();
        comandos.put("CmdOrganExcluirInscricao", cmdo);
        cmdo = new CmdAlterarPeriodoInscricaoeEvento();
        comandos.put("CmdAlterarPeriodoInscricaoeEvento", cmdo);
        cmdo = new CmdListarParticipantes();
        comandos.put("CmdListarParticipantes", cmdo);
        cmdo = new CmdEditarResponsavel();
        comandos.put("CmdEditarResponsavel", cmdo);
        cmdo = new CmdSelecionarResponsavelEdicao();
        comandos.put("CmdSelecionarResponsavelEdicao", cmdo);

        cmdo = new CmdBuscarEvento();
        comandos.put("CmdBuscarEvento", cmdo);
        cmdo = new CmdEditAdmin();
        comandos.put("CmdEditAdmin",cmdo);
        cmdo = new CmdEditPart();
        comandos.put("CmdEditPart",cmdo);
        cmdo = new CmdBuscarInscricao();
        comandos.put("CmdBuscarInscricao", cmdo);
        cmdo = new CmdAtualizarInscricao();
        comandos.put("CmdAtualizarInscricao", cmdo);
        cmdo = new CmdOrgRemoverAtividade();
        comandos.put("CmdOrgRemoverAtividade", cmdo);
        cmdo = new CmdOrgSelecionarAtividade();
        comandos.put("CmdOrgSelecionarAtividade", cmdo);
        cmdo = new CmdOrgSubmeterInscricao();
        comandos.put("CmdOrgSubmeterInscricao", cmdo);
        cmdo = new CmdBuscarParticipantePorEmail();
        comandos.put("CmdBuscarParticipantePorEmail", cmdo);
        
        
        cmdo = new CmdGerarCertificado();
        comandos.put("CmdGerarCertificado", cmdo);
        cmdo = new CmdGerarCertificadoParticipante();
        comandos.put("CmdGerarCertificadoParticipante", cmdo);
        cmdo = new CmdGerenciarUploadCertificados();
        comandos.put("CmdGerenciarUploadCertificados", cmdo);
        cmdo = new CmdGerenciarEmissaoCertificados();
        comandos.put("CmdGerenciarEmissaoCertificados", cmdo);
        cmdo = new CmdGerenciarLiberacaoCertificadoAtividade();
        comandos.put("CmdGerenciarLiberacaoCertificadoAtividade", cmdo);
        cmdo = new CmdConfirmarLiberacaoCertificado();
        comandos.put("CmdConfirmarLiberacaoCertificado", cmdo);
        
        cmdo = new CmdUploadModeloCertificado();
        comandos.put("CmdUploadModeloCertificado", cmdo);
        
        cmdo = new CmdValidarDocumento();
        comandos.put("CmdValidarDocumento", cmdo);
        
    }
// <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}