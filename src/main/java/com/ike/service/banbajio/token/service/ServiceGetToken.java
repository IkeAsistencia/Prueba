package com.ike.service.banbajio.token.service;

import com.ike.service.banbajio.token.dto.DtoGetToken;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceGetToken {

	public DtoGetToken getToken() throws BanBanjioException;
}
