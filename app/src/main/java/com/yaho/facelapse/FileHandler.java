package com.yaho.facelapse;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileHandler {
    protected Context context = null;
    protected File internalStorage = null;

    protected File targetDir = null;
    protected File[] fileList = null;

    FileHandler(){
    }

    FileHandler(Context context){
        this.context = context;
        this.internalStorage = this.context.getFilesDir();
        settargetDir();

    }
    String getFullPath(File inputfile){
        String temp;
        temp = new String(inputfile.getParent()+"/"+inputfile.getName());

        return temp;
    }

    void settargetDir(){
        this.targetDir = new File(this.internalStorage.getAbsolutePath() + "/camtest");
        this.fileList = this.targetDir.listFiles();
    }

    int getNumberofFiles(){
        this.fileList = this.targetDir.listFiles();

        if(this.fileList == null) {
            return 0;
        } else {
            int temp = 0;
            for (File tempFile : this.fileList){
                temp++;
            }
            return temp;
        }
    }

    void deleteFiles(){
        this.fileList = this.targetDir.listFiles();
        for (File tempFile : this.fileList) {
            Log.e("FileHandler", "Delete: " + tempFile.toString());
            tempFile.delete();
        }
    }
}