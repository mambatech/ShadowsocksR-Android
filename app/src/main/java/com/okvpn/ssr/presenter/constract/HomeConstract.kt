package com.okvpn.ssr.presenter.constract

import android.content.*
import com.edison.mvplib.impl.*
import com.j256.ormlite.stmt.query.*
import com.okvpn.ssr.database.*
import com.okvpn.ssr.utils.*

/**
created by edison 2020-03-10
 */
interface HomeConstract
{

	interface Presenter : IPresenter
	{
        fun initDefaultProfile()

		fun onAttachBaseContext(context: Context)

		fun stopService()

		fun startServiec()

		fun onStart()
	}

	interface View : IView
	{
        fun obtainDefaultProfile(profile: Profile)

		fun onUpdateTraffic(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)

		fun updateState(resetConnectionTest: Boolean = true)

		fun onConnectStateChanged(state: Int,msg: String?)

		fun onServiceConnected()

		fun onServiceDisconnected()
	}


}