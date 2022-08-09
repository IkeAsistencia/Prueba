package com.ike.service.banbajio.registerAttendanceUsage.service;

import com.ike.service.banbajio.registerAttendanceUsage.dto.ResponseRegisterAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.dto.registerAttendance;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceRegisterAttendanceUsage {

	public ResponseRegisterAttendance registerAttendanceUsage(registerAttendance registerAttendance) throws BanBanjioException;
}
