package com.okvpn.ssr.presenter.constract

import com.edison.mvplib.impl.*
import com.j256.ormlite.stmt.query.*
import com.okvpn.ssr.database.*

/**
created by edison 2020-03-10
 */
interface HomeConstract
{

	interface Presenter : IPresenter
	{
        fun initDefaultProfile()


	}

	interface View : IView
	{
        fun obtainDefaultProfile(profile: Profile)

		fun connectStateChange(state: Int)

		fun onUpdateTraffic(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)
	}


}