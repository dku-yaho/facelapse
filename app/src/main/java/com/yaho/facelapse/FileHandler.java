package com.yaho.facelapse;

import android.content.Context;

import java.io.File;

public class FileHandler {
    protected Context context = null;
    protected File internalStorage = null;
    protected File targetStorage = null;

    String getFullPath(File inputfile){
        String temp;
        temp = new String(inputfile.getParent()+"/"+inputfile.getName());

        return temp;
    }

}
