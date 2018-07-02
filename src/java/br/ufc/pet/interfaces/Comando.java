package br.ufc.pet.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Escritorio projetos
 */
public interface Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response);

}
