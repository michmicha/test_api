package com.isconseil.test_api.utils;

public interface EncryptionService {
  
  String encrypt(String strToEncrypt, String secret, String salt);
  String decrypt(String strToDecrypt, String secret, String salt);
}
