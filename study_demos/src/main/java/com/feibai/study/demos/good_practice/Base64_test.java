package com.feibai.study.demos.good_practice;

import java.util.Base64;


/**
 * 与sun.mis c套件和Apache Commons Codec所提供的Base64编解码器来比较的话，Java 8提供的Base64拥有更好的效能。实际测试编码与解码速度的话，
 * Java 8提供的Base64，要比sun.mis c套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍。因此在Java上若要使用Base64，
 * 这个Java 8底下的java .util套件所提供的Base64类别绝对是首选！
 */
public class Base64_test {

  public static void main(String[] args) throws Exception {
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();
    final String text = "字串文字";
    final byte[] textByte = text.getBytes("UTF-8");
//编码
    final String encodedText = encoder.encodeToString(textByte);
    System.out.println(encodedText);
//解码
    System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

    final Base64.Decoder decoder2 = Base64.getDecoder();
    final Base64.Encoder encoder2 = Base64.getEncoder();
    final String text2 = "字串文字";
    final byte[] textByte2 = text.getBytes("UTF-8");
//编码
    final String encodedText2 = encoder2.encodeToString(textByte);
    System.out.println(encodedText);
//解码
    System.out.println(new String(decoder2.decode(encodedText), "UTF-8"));
  }

}




