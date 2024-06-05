package com.example.week14

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val msg = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        val msgSender = msg[0].originatingAddress
        val msgBody = msg[0].messageBody
        val newIntent = Intent(context, MainActivity::class.java)
        newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        newIntent.putExtra("msgSender", msgSender)
        newIntent.putExtra("msgBody", msgBody)
        val pendingIntent = PendingIntent.getActivity(context, 100,
            newIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val application = context.applicationContext as Myapplication
        if (msgSender != null) {
            if (application.isForeground) {
                context.startActivity(newIntent)
            } else {
                makeNotification(context, msgSender, pendingIntent)
            }
        }
    }
}
