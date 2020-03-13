package com.okvpn.ssr.presenter

import android.content.*
import android.os.*
import com.edison.mvplib.*
import com.okvpn.ssr.*
import com.okvpn.ssr.aidl.*
import com.okvpn.ssr.database.*
import com.okvpn.ssr.job.*
import com.okvpn.ssr.presenter.constract.*
import io.reactivex.*
import io.reactivex.disposables.*

/**
created by edison 2020-03-10
 */
class HomePresenter : BasePresenterImpl<HomeConstract.View>(), HomeConstract.Presenter
{
	private val defaultProfileId = 100
	var mServiceBoundContext: ServiceBoundContext? = null

	var handler = Handler(Looper.getMainLooper())

	private val callback by lazy {
		object : IShadowsocksServiceCallback.Stub()
		{
			override fun stateChanged(s: Int, profileName: String?, m: String?)
			{
				handler.post {
					mView.onConnectStateChanged(s,m)
				}
			}

			override fun trafficUpdated(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)
			{
				handler.post {
					mView.onUpdateTraffic(txRate, rxRate, txTotal, rxTotal)
				}
			}
		}
	}

	override fun onAttachBaseContext(context: Context)
	{
		mServiceBoundContext = object : ServiceBoundContext(context)
		{
			override fun onServiceConnected()
			{
				mView.onServiceConnected()
			}

			override fun onServiceDisconnected()
			{
				mView.onServiceDisconnected()
			}

			override fun binderDied()
			{
				detachService()
				ShadowsocksApplication.app.crashRecovery()
				attachService()
			}
		}

		SSRSubUpdateJob.schedule()
		attachService()
	}

	private fun attachService()
	{
		mServiceBoundContext?.attachService(callback)
	}

	override fun onStart()
	{
		mServiceBoundContext?.registerCallback()
	}

	override fun stopService()
	{
		if (mServiceBoundContext?.bgService != null)
		{
			try
			{
				mServiceBoundContext?.bgService!!.use(-1)
			}
			catch (e: RemoteException)
			{
				e.printStackTrace()
			}
		}
	}

	/**
	 * Called when connect button is clicked.
	 */
	override fun startServiec()
	{
		try
		{
			mServiceBoundContext?.bgService!!.use(ShadowsocksApplication.app.profileId())
		}
		catch (e: RemoteException)
		{
			e.printStackTrace()
		}
	}

	override fun initDefaultProfile()
	{
        initProfile().compose(RxUtils::toSimpleSingle)
			.subscribe(object : Observer<Profile>{
				override fun onComplete()
				{}

				override fun onSubscribe(d: Disposable)
				{}

				override fun onNext(t: Profile)
				{
					mView?.obtainDefaultProfile(t)
				}

				override fun onError(e: Throwable)
				{}
			})
	}

	override fun detachView()
	{

	}

	private fun initProfile(): Observable<Profile>
	{
		return Observable.create { emmiter ->
            var defaultP = ProfileMgrDelegate.getProfileById(defaultProfileId)

			if (defaultP == null){
				defaultP = ProfileMgrDelegate.createDefaultProfile()
				ProfileMgrDelegate.saveProfile(defaultP)
			}
			emmiter.onNext(defaultP)
		}
	}

}