package com.example.appclass_hw19_use_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    Thread t;
    private static final int msgKey1 = 1;
    private static final int msgKey2 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.txtView);

        t = new Thread(runnable);
        t.start();

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
                try {

                    Message msg = new Message();
                    msg.what = msgKey2;
                    mHandler.sendMessage(msg);
                    Thread.sleep(2000);
                    mHandler.post(runnable2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    };
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            try {
                Message msg = new Message();
                Thread.sleep(2000);
                msg.what = msgKey1;
                mHandler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    txt.setText("Got it.");
                    break;
                case msgKey2:
                    txt.setText("Got message");
                    break;
                default:
                    break;
            }
        }
    };
}
