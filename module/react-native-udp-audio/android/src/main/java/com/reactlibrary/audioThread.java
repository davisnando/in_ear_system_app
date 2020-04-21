package com.reactlibrary;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.AudioAttributes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import android.util.Log;

public class audioThread implements Runnable {
    DatagramSocket socket;
    AudioTrack at;
    Thread t;

    audioThread(DatagramSocket s) {
        this.socket = s;
        this.t = new Thread(this);
        this.at = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
        .setAudioFormat(new AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setSampleRate(44100)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build())
        .setBufferSizeInBytes(512)
        .setPerformanceMode(AudioTrack.PERFORMANCE_MODE_LOW_LATENCY)
        .build();
        Log.e("audio", String.valueOf(AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT)));
        this.at.setVolume(0.5f);
        Log.e("audio", "Thread started");
        this.at.play();
        this.t.start();
    }


    public void run() {
        Log.e("audio","Loop started");
        byte[] buf = new byte[512];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        while (true) {
            try {
                this.socket.receive(p);
                this.at.write(p.getData(), 0,512);
                p.setLength(512);
            } catch (IOException e) {
                Log.e("audio", e.toString());
            }
        }
    }
}

