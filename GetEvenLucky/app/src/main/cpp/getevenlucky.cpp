#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
Java_com_jd_1s4nd_1b0x_getevenlucky_JNI_1Check_JNIII_compareString(JNIEnv *env, jobject thiz,
                                                                   jstring input) {
    const char* inputCStr = env->GetStringUTFChars(input, nullptr);
    std::string inputStr(inputCStr);
    env->ReleaseStringUTFChars(input, inputCStr);
    const std::string comparisonStr = "p3nt35t{4ll0w5_Ch3ck_Th3_(.50)_F!l35}";
    return inputStr == comparisonStr ? 1 : 0;
}