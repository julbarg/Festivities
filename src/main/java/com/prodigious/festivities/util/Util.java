package com.prodigious.festivities.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

   public static Date getDate(String dateString) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      Date parseDate = null;
      try {
         parseDate = dateFormat.parse(dateString);
         return parseDate;
      } catch (ParseException e) {
         return null;
      }
   }

}
