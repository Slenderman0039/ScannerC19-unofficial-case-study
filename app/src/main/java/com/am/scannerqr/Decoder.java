package com.am.scannerqr;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.ZipException;

import se.digg.dgc.signatures.cose.CoseSign1_Object;

public class Decoder {

    public JSONObject decode(String qrcode) throws IncorrectQRCodeException, ZipException, JSONException {
        if(qrcode.length()>601) {
            throw new IncorrectQRCodeException("QRCode must contains 601 character!");
        }else {
            //Decodifica base45
            byte[] dcod45 = se.digg.dgc.encoding.Base45.Decoder.DECODER.decode(qrcode);
            //Decompressione con Zlib
            byte[] decompressed = se.digg.dgc.encoding.Zlib.decompress(dcod45, true);
            //Decrittazione
            CoseSign1_Object decrypt = se.digg.dgc.signatures.cose.CoseSign1_Object.decode(decompressed);
            //Trasformazione in JSON
            String js = decrypt.getCwt().toString();
            js = js.substring(0, 1) + '"'+js.substring(1, 2) + '"'+ js.substring(2, 10) + '"' + js.substring(10, 11) + '"' +js.substring(11, 24) + '"' + js.substring(25, 26) + '"' +  js.substring(26, 39) + '"' + js.substring(40, 44) + '"' + js.substring(44, 47) + '"'+js.substring(47, 48)+'"' + js.substring(48, js.length());
            JSONObject jObject = new JSONObject(js);
            return jObject;
        }
    }






}
