package com.app.homeworktest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.homeworktest.const.ApplicationConst
import com.app.homeworktest.view.listners.UiEventInterface
import com.app.homeworktest.view.transfer.TransferFoundFragment
import com.app.homeworktest.view.transactions.TransactionsFragment
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.homeworktest.R
import com.app.homeworktest.util.addFragment
import com.app.homeworktest.viewmodel.TransactionsViewModel

class TransactionActivity : AppCompatActivity(),UiEventInterface {

    private val transactionsViewModel: TransactionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val extras = intent.extras
        if (extras != null) {
            val token = extras.getString(ApplicationConst.TOKEN_STRING).toString()
            val name = extras.getString(ApplicationConst.HOLDER_NAME).toString()
            transactionsViewModel.setJwtToken(token)
            transactionsViewModel.setAccountHolderName(name)
        }
        addFragment(TransactionsFragment.newInstance())
        initLiveData()
    }

    override fun setUpUi(view: View) {}

    override fun initLiveData() {
        transactionsViewModel.transferFound.observe(this, Observer {
            addFragment(TransferFoundFragment.newInstance())
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