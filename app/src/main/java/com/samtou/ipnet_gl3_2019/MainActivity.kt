package com.samtou.ipnet_gl3_2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.samtou.ipnet_gl3_2019.fragments.FragmentActivity
import com.samtou.ipnet_gl3_2019.intents.User

class MainActivity : AppCompatActivity() {

    @BindView(R.id.edtUsername) lateinit var usernameEdt: EditText
    @BindView(R.id.edtPassword) lateinit var passwordEdt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        usernameEdt =findViewById(R.id.edtUsername)
        passwordEdt = findViewById(R.id.edtPassword)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "on resume", Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.btn_submit)
    fun login(view: View) {
        var username = usernameEdt.text.toString()
        var password = passwordEdt.text.toString()

        Toast.makeText(this, password + username, Toast.LENGTH_LONG).show()

        if(username.equals("admin") && password.equals("admin")) {
            val intent = Intent(this, FragmentActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            intent.putExtra("boolean", true)
            intent.putExtra("decimal", 10.2)
            intent.putExtra("user", User(10, "samtou", "Asse12", "admin"))
            startActivity(intent)
        }
    }
}
