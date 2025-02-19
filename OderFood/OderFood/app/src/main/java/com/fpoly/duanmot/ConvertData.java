package com.fpoly.duanmot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ConvertData {

    public static byte [] ConvertImages (Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        return stream.toByteArray();
    }

    public static Bitmap ConvertBitmap (byte [] arr){
        return BitmapFactory.decodeByteArray(arr,0,arr.length);
    }
}
