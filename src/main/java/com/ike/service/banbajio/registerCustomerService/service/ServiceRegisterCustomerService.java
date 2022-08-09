package com.ike.service.banbajio.registerCustomerService.service;

import com.ike.service.banbajio.registerCustomerService.dto.ResponseRegisterCustomer;
import com.ike.service.banbajio.registerCustomerService.dto.registerCustomer;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceRegisterCustomerService {

	public ResponseRegisterCustomer registerCustomerService(registerCustomer registerCustomer) throws BanBanjioException;
}
