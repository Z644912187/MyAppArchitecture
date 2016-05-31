package com.corelib.utils.others;

import android.text.TextUtils;


public class StringUtil {

  public static boolean isEmpty(String str) {
      if (null == str)
          return true;
      if (str.length() == 0)
          return true;
      if (str.trim().length() == 0)
          return true;
      return false;
  }

  public static boolean isNotEmpty(String str) {
    return (str != null && str.trim().length() > 0);
  }

  /*
   * To Safe set String. if input == null, it will return "";
   */
  public static String safeSetString(String input) {
    if (input == null)
      return "";
    else
      return input;
  }


  public static boolean contains(String str, String searchStr) {
    if (str == null || searchStr == null) {
      return false;
    }
    return str.indexOf(searchStr) >= 0;
  }

  // 检测是否为手机号
  public static boolean isMobile(String number) {
    if (TextUtils.isEmpty(number)) {
      return false;
    }
    String regex = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
    return number.matches(regex);
  }



  public static String format(String str, Object... args) {
    return String.format(str, args);
  }
}
