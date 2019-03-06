package com.example.admin.myapplication;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ShowPass implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT=2;
        EditText editView=(EditText)v;

        if(event.getAction()==MotionEvent.ACTION_UP){
            if(event.getRawX()>=(editView.getRight()-editView.getCompoundDrawables()
                    [DRAWABLE_RIGHT].getBounds().width())){
                editView.requestFocus();
                if(editView.getTransformationMethod()instanceof PasswordTransformationMethod){
                    editView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editView.setSelection(editView.getText().length());
                    editView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.showpass,0);
                }else{
                    editView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editView.setSelection(editView.getText().length());
                    editView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.hintpass,0);
                }
                return true;
            }
        }
        return false;
    }
}
