package com.ike.service.banbajio.cancellationService.service;

import com.ike.service.banbajio.cancellationService.dto.ResponseCancellationService;
import com.ike.service.banbajio.cancellationService.dto.cancellation;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceCancellationService {

	public ResponseCancellationService cancellationService(cancellation cancellation) throws BanBanjioException;
}
