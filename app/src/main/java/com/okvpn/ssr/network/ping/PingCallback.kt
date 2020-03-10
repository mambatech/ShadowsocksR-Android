package com.okvpn.ssr.network.ping

import com.okvpn.ssr.database.*

open class PingCallback
{
	/**
	 * test result message
	 */
	open var resultMsg: String = ""

	/**
	 * ping success
	 *
	 * @param elapsed ping elapsed
	 */
	open fun onSuccess(profile: Profile, elapsed: Long)
	{
	}

	/**
	 * ping failed
	 */
	open fun onFailed(profile: Profile?)
	{
	}

	/**
	 * ping finished
	 */
	open fun onFinished(profile: Profile?)
	{
	}
}
