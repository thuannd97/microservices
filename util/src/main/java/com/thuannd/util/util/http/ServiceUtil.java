package com.thuannd.util.util.http;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {

    public static final Logger LOG = LoggerFactory.getLogger(ServiceUtil.class);

    private String port;
    private String serviceAddress = null;

    public ServiceUtil(){
        
    }

    public ServiceUtil(String port){
        this.port = port;
    }

    public String getServiceAddress(){
        if(serviceAddress == null){
            serviceAddress = findMyHostName() + "/" + findMyIpAddress() + ":" + port;
        }
        return serviceAddress;
    }

    private String findMyHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown host name";
        }
    }

    private String findMyIpAddress(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "unkown IP address";
        }
    }

}