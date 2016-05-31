package com.corelib.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.corelib.log.LogUtil;

/**
 * Created by charles
 * Data: 2016/3/30.
 */
public class TFragment extends Fragment {
    Toast mToast;
    protected String TAG;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = getClass().getName();
        LogUtil.info(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = getClass().getName();
        super.onCreate(savedInstanceState);
        LogUtil.info(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.info(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.info(TAG, "onHiddenChanged");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtil.info(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        LogUtil.info(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        LogUtil.info(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.info(TAG, "onDestroy");
    }

    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity(), text,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void showToast(final int resId) {
        if (resId != 0
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity(), resId,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(resId);
                    }
                    mToast.show();
                }
            });
        }

    }

    /**
     * 隐藏软键盘输入
     */
    public void hideSoftInputView() {
        if (getActivity() != null
                && !getActivity().isFinishing()) {
            InputMethodManager manager = ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                if (getActivity().getCurrentFocus() != null && manager != null) {
                    manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public void showSoftInputView(View view) {
        if (view != null
                && getActivity() != null
                && !getActivity().isFinishing()) {
            InputMethodManager manager = ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
                view.requestFocus();
                if (manager != null) {
                    manager.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
                }
            }
        }
    }
}
