package com.reactlibrary;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class audioThread implements Runnable {
    DatagramSocket socket;
    AudioTrack at;
    Thread t;

    audioThread(DatagramSocket s) {
        this.socket = s;
        this.t = new Thread(this);
        this.at = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                44100,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                512,
                AudioTrack.MODE_STREAM
        );
        this.t.start();
    }


    public void run() {
        byte[] buf = new byte[512];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        while (true) {
            try {
                this.socket.receive(p);
                this.at.write(p.getData(), 0, buf.length);

                p.setLength(p.getLength());
            } catch (IOException e) {

            }
        }
    }
}

