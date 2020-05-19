package com.tifenbao.base.view.webview;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;

/**
 * 商品详情js回调
 * <p>
 * mar
 * 2018/12/13
 */
public class SetHeightInterface {


    public static final String APP = "App";
    private Activity mactivity;

    private WebView webView;

    public SetHeightInterface(Activity mactivity) {
        this.mactivity = mactivity;
    }

    public void add(WebView webView) {
        this.webView = webView;
        webView.addJavascriptInterface(this, APP);
    }

    /**
     * 重新设置高度
     */
    @JavascriptInterface
    public void resize(final float height) {

        mactivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getActivity(), height + "", Toast.LENGTH_LONG).show();

                float mHeight = height;

                if (mHeight < 300) {
                    mHeight = 300;
                }

                webView.setLayoutParams(new FrameLayout.LayoutParams(mactivity.getResources().getDisplayMetrics().widthPixels, (int) (mHeight * mactivity.getResources().getDisplayMetrics().density)));
            }

        });


    }
}