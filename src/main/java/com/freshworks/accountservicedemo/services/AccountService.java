package com.freshworks.accountservicedemo.services;

import com.freshworks.account.v2.AccountServiceGrpc;
import com.freshworks.account.v2.GetAccountByProductIdRequest;
import com.freshworks.accountservicedemo.dto.AccountResponse;
import com.freshworks.accountservicedemo.mapper.AccountMapper;
import com.freshworks.commons.v2.Account;
import com.freshworks.commons.v2.Organisation;
import com.freshworks.commons.v2.User;
import com.freshworks.organisation.v2.GetOrganisationRequest;
import com.freshworks.organisation.v2.OrganisationServiceGrpc;
import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.authorization.Token;
import com.freshworks.user.v2.GetUserByProductIdRequest;
import com.freshworks.user.v2.UserServiceGrpc;
import com.google.protobuf.StringValue;
import io.grpc.Channel;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final SDKManager sdkManager;

    public AccountService(SDKManager sdkManager) {
        this.sdkManager = sdkManager;
    }

    public AccountResponse getAccount(String id) {
        Account account = getAccountByProductAccountId(id);
        return AccountMapper.toAccountResponse(account);
    }

    public Account getAccountByProductAccountId(String productAccountId) {
        GetAccountByProductIdRequest request = GetAccountByProductIdRequest.newBuilder()
                .setProductAccountId(StringValue.of(productAccountId))
                .build();
        Account account = null;
        try {
            Token token = sdkManager.generateClientAccessToken(); //get token using sdkManager bean
            io.grpc.CallCredentials callCredentials = sdkManager.generateCallCredentialsWithToken(token.getAccessToken());

            Channel channel = sdkManager.getChannel();

            AccountServiceGrpc.AccountServiceBlockingStub stub = AccountServiceGrpc.newBlockingStub(channel)
                    .withCallCredentials(callCredentials); //create service stub

            account = stub.getAccountByProductAccountId(request); //make service call

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    public User getUserByProductUserId(GetUserByProductIdRequest request) {
        User user = null;
        try {
            Token token = sdkManager.generateClientAccessToken(); //get token using sdkManager bean
            io.grpc.CallCredentials callCredentials = sdkManager.generateCallCredentialsWithToken(token.getAccessToken());

            Channel channel = sdkManager.getChannel();

            UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel)
                    .withCallCredentials(callCredentials); //create service stub

            user = stub.getUserByProductUserId(request); //make service call

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public Organisation getOrganisation(GetOrganisationRequest request) {
        Organisation org = null;
        try {
            Token token = sdkManager.generateClientAccessToken(); //get token using sdkManager bean
            io.grpc.CallCredentials callCredentials = sdkManager.generateCallCredentialsWithToken(token.getAccessToken());

            Channel channel = sdkManager.getChannel();

            OrganisationServiceGrpc.OrganisationServiceBlockingStub stub = OrganisationServiceGrpc.newBlockingStub(channel)
                    .withCallCredentials(callCredentials); //create service stub

            org = stub.getOrganisation(request); //make service call

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return org;
    }
}
