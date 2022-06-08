package com.app.homeworktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.homeworktest.domain.listners.UiEventInterface
import com.app.homeworktest.viewmodel.LoginViewModel
import android.content.Intent
import com.app.homeworktest.const.ApplicationConst
import com.app.homeworktest.util.addFragment
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.homeworktest.view.LoginFragment
import com.app.homeworktest.view.SignUpFragment

class LoginActivity : AppCompatActivity(),UiEventInterface {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //add Login fragment
        initLiveData()
        addFragment(LoginFragment.newInstance())
    }

    override fun setUpUi(view : View) {}

    override fun initLiveData() {
        loginViewModel.isRegister.observe(this, Observer {
            addFragment(SignUpFragment.newInstance())
        })
        loginViewModel.isLoginSuccess.observe(this , Observer {

            val i = Intent(this, TransactionActivity::class.java)
            i.putExtra(ApplicationConst.TOKEN_STRING, it.token)
            i.putExtra(ApplicationConst.HOLDER_NAME, it.username)
            startActivity(i)
            finish()
        })
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        val fragments = fragmentManager.backStackEntryCount
        if (fragments > 1) {
            super.onBackPressed()
        }
        else
            finish()
    }
}