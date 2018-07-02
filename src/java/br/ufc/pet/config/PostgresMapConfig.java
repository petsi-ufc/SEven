package br.ufc.pet.config;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.IOException;
import java.io.Reader;

/*
 * @author Escritorio projetos
 */
public class PostgresMapConfig {

    private static final SqlMapClient sqlMapClient;

    static {
        //Definindo o caminho para o SqlMapConfig e criando o reader

        try {
            String res = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(res);
            //inicializando o sqlMapClient com o client configurado no SqlMapConfig
            //Aqui e estabelecida a conexao com o banco
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }  
}
