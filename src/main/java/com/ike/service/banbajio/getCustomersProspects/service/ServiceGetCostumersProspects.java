package com.ike.service.banbajio.getCustomersProspects.service;

import com.ike.service.banbajio.getCustomersProspects.dto.ResponseProspects;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceGetCostumersProspects {

	public ResponseProspects getCostumersProspects() throws BanBanjioException;
}
