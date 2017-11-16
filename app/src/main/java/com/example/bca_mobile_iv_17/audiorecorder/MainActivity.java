package com.example.bca_mobile_iv_17.audiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
MediaRecorder recorder;
    MediaPlayer player;
    String file=null;
    Button btnstrt,btnstop,btnplay,btnpause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnpause=(Button)findViewById(R.id.button3);
        btnstop=(Button)findViewById(R.id.button);
        btnstrt=(Button)findViewById(R.id.button2);
        btnplay=(Button)findViewById(R.id.button4);
    }



    public void start(View view){
        try{
            try {
                File dir=new File( Environment.getExternalStorageDirectory()+"/Recordings");
                if(dir.exists()){}
                else {
                    dir.mkdir();
                }
                file =dir.getAbsolutePath() + "/" +System.currentTimeMillis()+  "myrecord.3gpp";
                //file=Environment.getExternalStorageDirectory().getPath();
                //File file1=new File(file,"AudioRecorder");
                //if(!file1.exists()){file1.mkdirs();}
                //file1.getAbsolutePath()+"/"+System.currentTimeMillis()+".3gpp";
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                recorder.setOutputFile(file);

            }catch (Exception e)
            {
                Toast.makeText(this,"ERROR :"+e,Toast.LENGTH_LONG).show();
            }
            recorder.prepare();
            recorder.start();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        btnstrt.setEnabled(false);
        btnstop.setEnabled(true);
        Toast.makeText(getApplicationContext(),"Recording Start...!!",Toast.LENGTH_LONG).show();
    }

    public void stop(View view){
try{
    recorder.stop();
    recorder.release();
    recorder=null;
    btnstrt.setEnabled(true);
    btnstop.setEnabled(false);
    Toast.makeText(getApplicationContext(),"Recording stop...!!",Toast.LENGTH_LONG).show();
    Toast.makeText(getApplicationContext(),"Saved Location : \n "+file,Toast.LENGTH_LONG).show();

}
catch (IllegalStateException e){e.printStackTrace();}
        catch (RuntimeException e){e.printStackTrace();}
    }


    public  void play(View view)
    {
        try{
            player=new MediaPlayer();
            player.setDataSource(file);
            player.prepare();
            player.start();
            btnplay.setEnabled(false);
            btnpause.setEnabled(true);
            Toast.makeText(getApplicationContext(),"Audio play.."+file,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause(View view){
        try{
            if(player!=null)
            {
                player.stop();
                player.release();
                player=null;
                btnplay.setEnabled(true);
                btnpause.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Audio stop.."+file,Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
