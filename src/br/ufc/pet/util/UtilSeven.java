package br.ufc.pet.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.ModalidadeInscricao;
import br.ufc.pet.entity.PrecoAtividade;
import br.ufc.pet.entity.TipoAtividade;
import br.ufc.pet.services.ModalidadeInscricaoService;

/*
 * @author Caio
 */
public class UtilSeven {

    public static Date treatToDate(String param) {
        if (param != null && param.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date result = sdf.parse(param);
                return result;
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    public static String treatToString(Date param) {
        if (param != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String result = sdf.format(param);
            return result;
        }
        return "";
    }

    public static String treatToLongString(Date param) {
        if (param != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");
            String data = formatter.format(param);
            return data;
        }
        return "";
    }

    public static String formtStringDate(Date param) {
        if (param != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String data = formatter.format(param);
            return data;
        }
        return "";
    }

    public static boolean validaData(String data) {

        @SuppressWarnings("unused")
        Date dataConvertida = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            dataConvertida = format.parse(data);
            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String precoFormater(double preco) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("R$ ###,###,##0.00");
        return df.format(preco);
    }

    public static double getPrecoAtividades(ArrayList<Atividade> array, Long idModalidade) {
        ModalidadeInscricaoService mis = new ModalidadeInscricaoService();
        ModalidadeInscricao m = mis.getModalidadeInscricaoById(idModalidade);

        if (m == null) {
            return 0;
        }

        double preco = 0;
        for (PrecoAtividade p : m.getPrecoAtividades()) {
            for (Atividade a : array) {
                if (a.getTipo().getId().equals(p.getTipoAtividadeId())) {
                    preco += p.getValor();
                }
            }
        }
        return preco;
    }

    public static double getPrecoTipo(TipoAtividade t, ModalidadeInscricao m) {
        if (m == null || t == null) {
            return 0;
        }

        double preco = 0;
        for (PrecoAtividade p : m.getPrecoAtividades()) {
            if (t.getId().equals(p.getTipoAtividadeId())) {
                preco += p.getValor();
                break;
            }
        }
        return preco;
    }

    public static String criptografar(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            return new String(java.util.Base64.getMimeEncoder().encode(digest.digest()), UTF_8);
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
        
    }
}
