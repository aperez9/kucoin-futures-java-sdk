/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.TransferAPI;
import com.kumex.core.rest.interfaces.retrofit.TransferAPIRetrofit;
import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.TransferHistory;
import com.kumex.core.rest.response.TransferResponse;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Transfer API adapter
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
public class TransferAPIAdapter extends AuthRetrofitAPIImpl<TransferAPIRetrofit> implements TransferAPI {

    public TransferAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public TransferResponse toKucoinMainAccount(String bizNo, BigDecimal amount) throws IOException {
        return super.executeSync(getAPIImpl().applyTransfer(bizNo, amount));
    }

    @Override
    public Pagination<TransferHistory> getTransferOutRecords(String status, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return super.executeSync(getAPIImpl().getTransferOutRecords(request.getCurrentPage(), request.getPageSize(), status,
                request.getStarAt(), request.getEndAt()));
    }

    @Override
    public void cancelTransferOutRequest(String applyId) throws IOException {
        super.executeSync(getAPIImpl().cancelTransfer(applyId));
    }
}