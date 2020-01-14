package com.samtou.ipnet_gl3_2019.Permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import com.samtou.ipnet_gl3_2019.R

class SMSActivity : AppCompatActivity() {

    @BindView(R.id.phoneNumberEdt) lateinit var phoneNumberEdt: EditText
    @BindView(R.id.messageEdt) lateinit var messageEdt: EditText

    lateinit var smsManager: SmsManager

    val PERMISSION_REQUEST_SMS: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        ButterKnife.bind(this)

        smsManager = SmsManager.getDefault()
    }

    fun sendMessage(view: View) {
        val number = phoneNumberEdt.text.toString()
        val message = messageEdt.text.toString()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            smsManager.sendTextMessage(number, null, message, null, null)
        }else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST_SMS)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_SMS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val number = phoneNumberEdt.text.toString()
                val message = messageEdt.text.toString()
                smsManager.sendTextMessage(number, null, message, null, null)
            }
        }
    }
}
