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

    final String TAG = "Video";
    Context context;
    static File[] pictures;

    public Video(Context context) {
        this.context = context;
    }

    public void genVid() {
        File sdCard = context.getFilesDir();
        File targetStorage = new File(Environment.getExternalStorageDirectory() + "/Facelapse");

        if(!targetStorage.exists()){
            targetStorage.mkdir();
            Log.e(TAG, "Facelapse folder created");
        }

        final int FRAMES = 10;
        SeekableByteChannel out = null;
        AndroidSequenceEncoder encoder;
        try{
            Log.e(TAG, "Entry");
            File dirFile = new File (sdCard.getAbsolutePath() + "/camtest");
            File[] fileList = dirFile.listFiles();

            String savePoint = new String(targetStorage.toString()+"/"+System.currentTimeMillis()+".mp4");
            out = NIOUtils.writableFileChannel(savePoint);
            Log.e(TAG, "Saving Point: "+savePoint);
            encoder = new AndroidSequenceEncoder(out, Rational.R(25, 1));

            for (File tempFile : fileList) {
                String tempPath = tempFile.getParent();
                String tempFileName = tempFile.getName();

                Log.e(TAG, "File Found: " + tempPath + "/" + tempFileName);
                Bitmap image = BitmapFactory.decodeFile(tempPath+"/"+tempFileName);

                for(int i = 1; i < FRAMES; i++){
                    encoder.encodeImage(image);
                    Log.e(TAG, "Encoded: " + tempPath + "/" + tempFileName);
                }
            }
            encoder.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(out);
        }
    }
}
