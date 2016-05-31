package com.sjtu.charles.wallet.mvp;

import android.content.Context;

import com.corelib.log.LogUtil;
import com.corelib.utils.net.Network;
import com.sjtu.charles.wallet.http.WalletHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2016/5/18.
 */
public class WalletPresenter implements WalletContract.Presenter {

    private static final String TAG = "WalletPresenter";
    WalletContract.View mView;
    Context mContext;

    public WalletPresenter(Context context, WalletContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void rechargeWallet(long accout) {

        if (!Network.isAvailable(mContext)) {
            mView.onErrorNetWork();
            return;
        }

        WalletHttp.getInstance().rechargeWallet(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    //TODO 进行自己的业务处理，最终回调View


                    mView.rechargeSuccessful();
                } else {
                    mView.rechargeFailed();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                mView.rechargeFailed();
                LogUtil.fatal(TAG,"rechargeWallet onFailure",t);
            }
        }, accout);

    }

    @Override
    public void withdrawDeposit(long accout) {

        WalletHttp.getInstance().withdrawDeposit(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    //TODO 进行自己的业务处理，最终回调View

                    mView.withdrawSuccessful();
                } else {
                    mView.withdrawFailed();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                mView.withdrawFailed();
                LogUtil.fatal(TAG,"withdrawDeposit onFailure",t);
            }
        }, accout);

    }
}
