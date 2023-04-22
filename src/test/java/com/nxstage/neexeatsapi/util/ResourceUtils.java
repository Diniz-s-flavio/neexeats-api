package com.nxstage.neexeatsapi.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ResourceUtils {
    public static String getContentFromResource(String resorcePath){
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream(resorcePath);
            return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
