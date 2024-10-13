package com.jd_s4nd_b0x.mulyam;

public class KeyProvider {
    static {
        System.loadLibrary("KeyProvider");
    }

    public native String getSecretKey();
    public native String getIv();
    public native String getEncryptedCurrencyApiKey();
    public native String getEncryptedYahooFinanceApiKey();
}
