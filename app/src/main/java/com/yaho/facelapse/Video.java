package com.yaho.facelapse;

import android.content.ContextWrapper;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
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
        File savePoint = Environment.getExternalStorageDirectory();

        final int FRAMES = 10;
        SeekableByteChannel out = null;
        AndroidSequenceEncoder encoder;
        try{
            Log.e(TAG, "Entry");
            File dirFile = new File (sdCard.getAbsolutePath() + "/camtest");
            File[] fileList = dirFile.listFiles();

            out = NIOUtils.writableFileChannel(savePoint+"/Test.mp4");
            Log.e(TAG, "Saving Point: "+savePoint+"/Test.mp4");
            encoder = new AndroidSequenceEncoder(out, Rational.R(25, 1));

            for (File tempFile : fileList) {
                String tempPath = tempFile.getParent();
                String tempFileName = tempFile.getName();
                Log.e(TAG, "File Found: " + tempPath + "/" + tempFileName);

                for(int i = 1; i < FRAMES; i++){
                    Bitmap image = BitmapFactory.decodeFile(tempPath+"/"+tempFileName);
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
