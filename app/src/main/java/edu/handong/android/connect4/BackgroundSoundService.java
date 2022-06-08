package edu.handong.android.connect4;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class BackgroundSoundService extends Service implements MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnDrmPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener
        {
    public static final String PREF = "PlayerPref";
    public static final String MUSIC = "music";
    private MediaPlayer mediaPlayer;


    public BackgroundSoundService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
         throw new UnsupportedOperationException("Unsupported Exception");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnDrmPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(this,R.raw.happy_lullaby);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(70, 70);
            mediaPlayer.start();}
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            if(mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if(mediaPlayer.isPlaying())   mediaPlayer.stop();
        stopSelf();
    }

    @Override
    public void onDrmPrepared(MediaPlayer mediaPlayer, int i) {
        if(!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
    }

    /*protected void onHandleIntent(Intent intent) {
        SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
        boolean music = preferences.getBoolean(MUSIC, false);
        if (intent != null) {
            if ((music==true) && (this.mediaPlayer.isPlaying()==false)){
                Toast.makeText(BackgroundSoundService.this,"music ON and player was OFF",Toast.LENGTH_SHORT).show();
                this.mediaPlayer.setLooping(true); // Set looping
                this.mediaPlayer.setVolume(100, 100);
                this.mediaPlayer.start();
            }
            else if ((music==false) && (this.mediaPlayer.isPlaying()==true)){
                Toast.makeText(BackgroundSoundService.this,"music OFF and player was ON",Toast.LENGTH_SHORT).show();
                this.mediaPlayer.stop();
            }
            else if ((music==false)&& (this.mediaPlayer.isPlaying()==false)){
                if (!music)Toast.makeText(BackgroundSoundService.this,"music OFF and mediaPlayer was OFF",Toast.LENGTH_SHORT).show();
                this.mediaPlayer.stop();
            }
        }

    }*/

}