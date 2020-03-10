package com.okvpn.ssr.utils

import android.widget.*
import com.okvpn.ssr.*

object ToastUtils
{
	fun showLong(strId: Int)
	{
		val context = ShadowsocksApplication.app.applicationContext
		Toast.makeText(context, strId, Toast.LENGTH_LONG)
			.show()
	}

	fun showShort(str: String)
	{
		val context = ShadowsocksApplication.app.applicationContext
		Toast.makeText(context, str, Toast.LENGTH_SHORT)
			.show()
	}

	fun showShort(strId: Int)
	{
		val context = ShadowsocksApplication.app.applicationContext
		Toast.makeText(context, strId, Toast.LENGTH_SHORT)
			.show()
	}
}