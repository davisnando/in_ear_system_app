package com.reactlibrary;


import java.net.DatagramSocket;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class UdpAudioBufferModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private DatagramSocket socket;
    private audioThread t1;
    public UdpAudioBufferModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "UdpAudioBuffer";
    }

    @ReactMethod
    public void PlayAudioBuffer(Promise promise) {
        try{
            this.socket = new DatagramSocket(4444);
            promise.resolve(true);
        }catch(Exception e){
            promise.reject("Thread error.", e);
        }

        this.t1 = new audioThread(this.socket);
    }

    @ReactMethod
    public void Stop(){
        this.socket.close();
    }
}
