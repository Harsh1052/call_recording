package com.example.call_recording;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class PhoneStateReceiver extends BroadcastReceiver {

    private MediaRecorder recorder = null;



    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Toast.makeText(context,"In OnRecevied",Toast.LENGTH_SHORT).show();
            Log.i("In OnRecived", "Working");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"Ringing State Number is -"+incomingNumber,Toast.LENGTH_SHORT).show();
                Log.i("In Ringing State", "Ringing State Number is -"+incomingNumber);
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                startRecording();
                Toast.makeText(context,"Received State",Toast.LENGTH_SHORT).show();
                Log.i("In Received State", "Phone Received");
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                stopRecording();
                Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
                Log.i("Idle State", "Idle State");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void startRecording(){
        String fileName = Environment.getExternalStorageDirectory() + File.separator
                + Environment.DIRECTORY_DCIM + File.separator + "FILE_NAME";

        Log.i("RecordingStart", "WORKING");
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName+".3gp");
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            Log.e("Recording Start", "prepare() failed");
        }


    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }
}
