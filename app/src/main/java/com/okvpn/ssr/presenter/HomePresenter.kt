package com.okvpn.ssr.presenter

import android.os.*
import com.edison.mvplib.*
import com.okvpn.ssr.aidl.*
import com.okvpn.ssr.database.*
import com.okvpn.ssr.presenter.constract.*
import com.okvpn.ssr.utils.*
import io.reactivex.*
import io.reactivex.disposables.*

/**
created by edison 2020-03-10
 */
class HomePresenter : BasePresenterImpl<HomeConstract.View>(), HomeConstract.Presenter
{
	private val defaultProfileId = 100
	var handler = Handler(Looper.getMainLooper())

	private val callback by lazy {
		object : IShadowsocksServiceCallback.Stub()
		{
			override fun stateChanged(s: Int, profileName: String?, m: String?)
			{
				handler.post {
					when (s)
					{
						Constants.State.CONNECTING ->
						{

						}

						Constants.State.CONNECTED ->
						{

						}

						Constants.State.STOPPED ->
						{

							if (!m.isNullOrEmpty())
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
			}

			override fun trafficUpdated(txRate: Long, rxRate: Long, txTotal: Long, rxTotal: Long)
			{
				handler.post {
					mView.onUpdateTraffic(txRate, rxRate, txTotal, rxTotal)
				}
			}
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