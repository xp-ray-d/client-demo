package com.unionpay.uppayplugin.demo;

import android.app.Activity;
import android.widget.TextView;
import com.unionpay.UPPayAssistEx;

public class JARActivity extends BaseActivity {

    @Override
    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
        UPPayAssistEx.startPay(activity, null, null, tn, mode);
    }

    @Override
    public void updateTextView(TextView tv) {
        String txt = "请输入银联app返回的号即可，即系统返回的rc_result字段";
        tv.setText(txt);
    }
}
