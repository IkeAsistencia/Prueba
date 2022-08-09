package com.ike.service.common.config;

import com.ike.service.common.exceptions.BanBanjioException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

//@Component
public class TimerPingBB {

    // Se ejecuta cada 3 minutos
   // @Scheduled(fixedRate = 180000)
    public void token() throws IOException {
        System.out.println("Ping Ejecutandose cada  -  3 Minutos");
        InetAddress inetAddress = InetAddress.getByName("192.168.111.19");
        if (inetAddress.isReachable(2000)){
            System.out.println("Dirección accesible.");
        } else {
            System.out.println("Dirección inaccesible.");
        }
    }
}
