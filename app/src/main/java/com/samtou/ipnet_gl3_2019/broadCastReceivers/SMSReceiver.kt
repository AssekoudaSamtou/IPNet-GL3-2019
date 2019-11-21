package com.samtou.ipnet_gl3_2019.broadCastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import kotlin.collections.ArrayList

class SMSReceiver : BroadcastReceiver() {
    val SMS_INTENT: String = "android.provider.Telephony.SMS_RECEIVED"
    var messages = ArrayList<SmsMessage>()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == SMS_INTENT) {
            var bundle = intent.extras
            var objects = bundle!!.get("pdus") as Array<Object>

            for (i in 0..objects.size) {
                messages.add(SmsMessage.createFromPdu(objects[i] as ByteArray))
            }
        }
        var message = messages.get(0)
//        Log.e("SAMTOU", "samtou")
        Toast.makeText(context, message.messageBody, Toast.LENGTH_LONG)
    }
}