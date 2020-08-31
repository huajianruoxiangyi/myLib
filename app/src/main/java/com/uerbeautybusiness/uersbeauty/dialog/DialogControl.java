package com.uerbeautybusiness.uersbeauty.dialog;

import android.app.ProgressDialog;

public interface DialogControl {

    void hideWaitDialog();

    ProgressDialog showWaitDialog();

    ProgressDialog showWaitDialog(int resid);

    ProgressDialog showWaitDialog(String text);
}
