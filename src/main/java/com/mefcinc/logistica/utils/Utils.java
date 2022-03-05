package com.mefcinc.logistica.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(YYYYMMDD);

    public static final String VALIDAR_PLACA = "^[a-zA-Z]{3}[0-9]{3}$";
    public static final String VALIDAR_NUMERO_FLOTA = "^[a-zA-Z]{3}[0-9]{4}[a-zA-Z]{1}$";
    public static final String VALIDAR_NUMERO_GUIA = "^[a-zA-Z0-9]{10}$";

    public static Date getToDay(){
        return new Date();
    }

    public static Date dateToString(String dateStr){
        try{
             return DATE_FORMAT.parse(dateStr);
        }catch (Exception e){
            logger.error("Error al convertir String a fecha: ",e);
            return null;
        }
    }

    public static boolean validarPatter(String patter, String valor){
        Pattern pattern = Pattern.compile(patter);
        Matcher matcher = pattern.matcher(valor);
        return matcher.matches();
    }

    public static  Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
