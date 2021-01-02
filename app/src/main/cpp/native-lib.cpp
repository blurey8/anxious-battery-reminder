//
// Created by reyhan on 02/01/21.
//

#include <jni.h>

extern "C"
JNIEXPORT jboolean JNICALL
Java_id_ac_ui_cs_mobileprogramming_reyhan_ui_main_broadcastreceiver_BatteryChangedReceiver_isNumberDecrease(
        JNIEnv *env, jobject thiz, jint num_before, jint num_after) {
    return static_cast<jboolean>(num_before < num_after);
}
