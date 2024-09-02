package com.jd_s4nd_b0x.getevenlucky.Logee;

public class a {
    private static final String ORIGINAL_FLAG = "c3ag35g{Synt_Jnf_va_Gur_Ybt}";

    public static String getEncryptedFlag() {
        return applyCipher();
    }

    private static String applyCipher() {
        StringBuilder result = new StringBuilder();
        for (char c : a.ORIGINAL_FLAG.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append((char) ('a' + (c - 'a' + 13) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                result.append((char) ('A' + (c - 'A' + 13) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}