package com.dbtablemonitor.model.dao;

import java.util.Date;

public class DAOUtil {

	public static long dateToUnixEpoch(Date dt) {
		return dt.getTime() / 1000L;
	}
	
	public static Date unixEpochtoDate(long unixTimeStamp) {
		return new Date(unixTimeStamp * 1000L);
	}

}
