package com.yaho.facelapse;

import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Video extends FileHandler implements FileOperation {

    File targetStorage = null;
    final String TAG = "Video";
    final int FRAMESPERPIC = 10;
    int progress = 0;

    String savePoint;
    SeekableByteChannel output = null;
    AndroidSequenceEncoder encoder = null;

    public Video(Context context) {
        this.context = context;
        this.internalStorage = this.context.getFilesDir();
        settargetDir();
    }

    public int getProgress(){
        return this.progress;
    }

    public void genVid() {
        int count = this.getNumberofFiles();

        this.targetStorage = new File(Environment.getExternalStorageDirectory() + "/Facelapse");
        if(!this.targetStorage.exists()){
            this.targetStorage.mkdir();
            Log.e(TAG, "Facelapse folder created");
        }

        try{
            Log.e(TAG, "Entry");

            savePoint = new String(this.targetStorage.toString()+"/"+System.currentTimeMillis()+".mp4");
            output = NIOUtils.writableFileChannel(savePoint);
            Log.e(TAG, "Saving Point: "+savePoint);

            encoder = new AndroidSequenceEncoder(output, Rational.R(25, 1));

            int current = 0;
            for (File tempFile : fileList) {
                this.progress = (current / count) * 100;
                String curFile = getFullPath(tempFile);

                Log.e(TAG, "Current File: " + curFile);
                Bitmap image = BitmapFactory.decodeFile(curFile);

                for(int i = 1; i < FRAMESPERPIC; i++){
                    encoder.encodeImage(image);
                    Log.e(TAG, "Encoded: " + curFile);
                }
            }
            encoder.finish();
            Log.e(TAG, "Finished Generating Vid: " + savePoint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(output);
        }
    }
}
