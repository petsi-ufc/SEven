package br.ufc.pet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Encoder;

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
    
    public static String criptografar(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
        
    }
}
