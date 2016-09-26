package com.androidsrc.server;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by allan on 26/09/16.
 */
public class GenerateBase64 {
    static String generar(String dato){
        byte[] value=Base64.encodeBase64(dato.getBytes());
        String texto=new String(value);
        return texto;
    }

}
