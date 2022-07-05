package com.isconseil.test_api.utils;

public class ExceptionPrompter {
  
  public static Exception NotFondMessage(String title, Long value) {
    return new Exception("No '"+title+"' wtih id "+value);
  }

  public static Exception NotFondMessage(String title, String value) {
    return new Exception("No '"+title+"' wtih "+value);
  }

  public static Exception message(String message) {
    return new Exception(message);
  }
}
