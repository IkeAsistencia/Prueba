package com.ike.service.common.config;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ike.service.banbajio.token.dto.DtoGetToken;
import com.ike.service.banbajio.token.service.ServiceGetToken;
import com.ike.service.common.exceptions.BanBanjioException;

//@Component
public class TimerGetTokenBB {

	@Autowired
	ServiceGetToken getToken;

	// Se ejecuta cada 30 minutos
    //@Scheduled(fixedRate = 1800000)
    public void token() {
        System.out.println("Get Token BB, cada 30 minutos - ");
        try {
			GetTokeBB.setAccess_token(getToken.getToken().getAccess_token());
		} catch (BanBanjioException e) {
			e.getCause();
			e.getMessage();
			e.getLocalizedMessage();
		}
    }
}
