package com.example.veratenebraestudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.veratenebraestudio.Fragments.LoginFragment.LoginFragment
import com.example.veratenebraestudio.Fragments.RegistrationFragment.RegistrationSecondFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, LoginFragment())
            .commit()
    }

}
