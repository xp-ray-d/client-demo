package com.xp.client.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XpEncodeUtil {

    public XpEncodeUtil() {
    }

    public static String encodeHex(final byte[] data) {
        return HexUtil.encodeHexStr(data);
    }

    public static String encodeHex(final String data) {
        return encodeHex(data, StandardCharsets.UTF_8);
    }

    public static String encodeHex(final String data, final Charset charset) {
        return HexUtil.encodeHexStr(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static byte[] decodeHex(final String hex) {
        return HexUtil.decodeHex(hex);
    }

    public static String decodeHex(final String hex, final Charset charset) {
        return HexUtil.decodeHexStr(hex, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encodeBase64(final byte[] data) {
        return Base64.encode(data);
    }

    public static String encodeBase64(final String data) {
        return encodeBase64(data, StandardCharsets.UTF_8);
    }

    public static String encodeBase64(final String data, final Charset charset) {
        return Base64.encode(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static byte[] decodeBase64(final String base64) {
        return Base64.decode(base64);
    }

    public static String decodeBase64(final String base64, final Charset charset) {
        return Base64.decodeStr(base64, charset == null ? StandardCharsets.UTF_8 : charset);
    }
}
