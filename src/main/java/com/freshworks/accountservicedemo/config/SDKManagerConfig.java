package com.freshworks.accountservicedemo.config;

import javax.net.ssl.SSLException;

import com.freshworks.sdk.Configuration;
import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.exception.ClientCredentialsNotConfiguredException;
import com.freshworks.sdk.exception.RegionNotConfiguredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SDKManagerConfig {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Bean
    public SDKManager createSDKManager() throws SSLException, ClientCredentialsNotConfiguredException, RegionNotConfiguredException {
        return new SDKManager(Configuration.builder()
                .forRegion(Configuration.FreshworksRegion.SANDBOX) //region you want to connect to (QA, INT, SANDBOX)
                .setClientCredentials(clientId, clientSecret)
                .build());
    }
}