#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_jd_1s4nd_1b0x_mulyam_KeyProvider_getSecretKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("come_and_find_it");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_jd_1s4nd_1b0x_mulyam_KeyProvider_getIv(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("0226772910400059");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_jd_1s4nd_1b0x_mulyam_KeyProvider_getEncryptedCurrencyApiKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("SZMeQ/X7eAJOnQGARZNc+xuf8aduxjb+a2Ws3VP64Oyz1O/blWq/zEcP0NuaQ+VBM5AHAwHKQBuR0CCXKmpyKg==");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_jd_1s4nd_1b0x_mulyam_KeyProvider_getEncryptedYahooFinanceApiKey(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF("R/TYeslY2D2PS3LHUz5DoLEsI+ljBGs2reFdKlk3kNlIQ+thUsyXrsQ7wm0X1stZvyFwApp7zdkkPBHAc9lB4w==");
}