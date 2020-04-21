package com.reactlibrary;

import java.net.DatagramSocket;
import java.net.InetAddress;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import android.util.Log;

public class UdpAudioModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private DatagramSocket socket;
    private audioThread t1;
    public UdpAudioModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "UdpAudio";
    }

    @ReactMethod
    public void PlayAudioBuffer(Promise promise) {
        try{
            this.socket = new DatagramSocket(4141);
            this.socket.setBroadcast(true);
            Thread.sleep(5000);
            Log.e("audio", String.valueOf(this.socket.getLocalPort()));

            this.t1 = new audioThread(this.socket);
            Log.e("audio", this.socket.getLocalAddress().toString());
            promise.resolve(true);
        }catch(Exception e){
            Log.e("audio", e.toString());

            promise.reject("Thread error.", e);
        }

    }

    @ReactMethod
    public void Stop(){
        this.socket.close();
    }
}
