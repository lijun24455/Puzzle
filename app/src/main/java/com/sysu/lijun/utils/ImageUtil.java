package com.sysu.lijun.utils;

import android.graphics.Bitmap;

/**
 * Created by lijun on 15/5/10.
 */
public class ImageUtil {

    public static Bitmap getSquareBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int squareWidth = Math.min(width, height);

        Bitmap squareBitmap = Bitmap.createBitmap(bitmap, 0, 0, squareWidth, squareWidth);

        return squareBitmap;
    }
}
