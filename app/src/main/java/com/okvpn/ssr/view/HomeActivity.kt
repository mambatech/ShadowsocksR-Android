package com.okvpn.ssr.view

import android.os.*
import com.edison.mvplib.*
import com.okvpn.ssr.*
import com.okvpn.ssr.R
import com.okvpn.ssr.database.*
import com.okvpn.ssr.presenter.*
import com.okvpn.ssr.presenter.constract.*

/**
created by edison 2020-03-10
 */
class HomeActivity : BaseActivity<HomeConstract.Presenter>(),HomeConstract.View {


	private var connectProfile: Profile? = null

	override fun initInjector(): HomeConstract.Presenter
	{
		return HomePresenter()
	}

	override fun onCreateActivity()
	{
		setContentView(R.layout.activity_home)
	}

	override fun initData()
	{

	}

	override fun obtainDefaultProfile(profile: Profile)
	{
	    connectProfile = profile
	}

	override fun connectStateChange(state: Int)
	{

	}

	override fun onUpdateTraffic(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)
	{

	}

}