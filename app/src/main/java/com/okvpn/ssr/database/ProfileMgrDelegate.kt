package com.okvpn.ssr.database

import com.okvpn.ssr.utils.*

/**
created by edison 2020-03-10
 */
object ProfileMgrDelegate
{

	var profileManager: ProfileManager? = null

	fun getProfileById(id: Int): Profile?{
		return profileManager?.getProfile(id)
	}

	fun saveProfile(p: Profile){
		profileManager?.createProfile(p)
	}

	fun createDefaultProfile(): Profile{
		val profile = Profile()
		profile.id = 100
		profile.name = "ShadowsocksR"
		profile.host = "198.13.40.211"
		profile.remotePort = 8390
		profile.password = "niupi123456"
		profile.protocol = "origin"
		profile.obfs = "plain"
		profile.method = "aes-128-cfb"
		profile.url_group = "Default Group"
		return profile
	}


}