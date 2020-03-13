package com.okvpn.ssr.view

import android.app.*
import android.content.*
import android.net.*
import android.os.*
import com.edison.mvplib.*
import com.okvpn.ssr.*
import com.okvpn.ssr.R
import com.okvpn.ssr.database.*
import com.okvpn.ssr.presenter.*
import com.okvpn.ssr.presenter.constract.*
import com.okvpn.ssr.utils.*
import kotlinx.android.synthetic.main.activity_home.*

/**
created by edison 2020-03-10
 */
class HomeActivity : BaseActivity<HomeConstract.Presenter>(),HomeConstract.View {

	private val TAG = HomeActivity::class.java.simpleName

	private var connectProfile: Profile? = null
	private val REQUEST_CONNECT = 1


	override fun initInjector(): HomeConstract.Presenter
	{
		return HomePresenter()
	}

	override fun onCreateActivity()
	{
		setContentView(R.layout.activity_home)
		mPresenter.onAttachBaseContext(this)

		lv_loading_eat.setOnClickListener {

		}
	}

	override fun onStart()
	{
		super.onStart()
		mPresenter.onStart()
	}

	override fun initData()
	{

	}

	override fun obtainDefaultProfile(profile: Profile)
	{
	    connectProfile = profile
	}

	override fun onUpdateTraffic(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)
	{

	}

	override fun onConnectStateChanged(state: Int, msg: String?)
	{
		when (state)
		{
			Constants.State.CONNECTING ->
			{

			}

			Constants.State.CONNECTED ->
			{

			}

			Constants.State.STOPPED ->
			{

				if (!msg.isNullOrEmpty())
				{
					//TODO 连接失败
				}

			}
			Constants.State.STOPPING ->
			{

			}
			else ->
			{

			}
		}

	}

	override fun onDestroy()
	{
		super.onDestroy()

	}

	private fun prepareStartService()
	{
		val intent = VpnService.prepare((mPresenter as HomePresenter).mServiceBoundContext)
		if (intent != null)
		{
			startActivityForResult(intent, REQUEST_CONNECT)
		}
		else
		{
			onActivityResult(REQUEST_CONNECT, Activity.RESULT_OK, null)
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK)
		{
			mPresenter.startServiec()
		}
		else
		{
			VayLog.e(TAG, "Failed to start VpnService")
		}
	}

	override fun onServiceConnected()
	{

	}

	override fun onServiceDisconnected()
	{

	}

	override fun updateState(resetConnectionTest: Boolean)
	{

	}

}