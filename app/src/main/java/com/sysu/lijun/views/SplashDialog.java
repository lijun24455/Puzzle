package com.sysu.lijun.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sysu.lijun.Context.PlayerInfo;
import com.sysu.lijun.Context.PreferencesItems;
import com.sysu.lijun.puzzle.R;
import com.sysu.lijun.utils.ImageUtil;


import java.io.IOException;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

/**
 * Created by lijun on 15/5/10.
 */
public class SplashDialog extends BlurDialogFragment{

    private Bitmap mCurrentImage;
    private ImageView mImageView;
    private TextView mTextView;
    private final int START_COUNT_TIME = 100;

    private int mTimeLast = 3;

    private SplashListener mSplashListener;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case START_COUNT_TIME:
                    if (mTimeLast >= 0){
                        mTextView.setText((mTimeLast--) + "");
                        mHandler.sendEmptyMessageDelayed(START_COUNT_TIME, 1000);
                    } else {
                        dismiss();
                    }
                    break;
            }
        }
    };

    public boolean isDismiss() {
        return isDismiss;
    }

    private boolean isDismiss;

    private boolean isPause;

    @Override
    public void onPause() {
        Log.i("splash", "splash is pause---------->");
        super.onPause();
        if (!isDismiss){
            isPause = true;
            mHandler.removeMessages(START_COUNT_TIME);
        }
    }

    @Override
    public void onResume() {
        Log.i("splash", "splash is resume-------->");
        super.onResume();
        if (isPause && !isDismiss){
            mHandler.sendEmptyMessageDelayed(START_COUNT_TIME,1000);
        }
    }


    public interface SplashListener{
        void onShowing();
        void onDismiss();
    }

    public void setSplashListener(SplashListener mListener){
        this.mSplashListener = mListener;
    }

    /**
     * Bundle key used to start the blur dialog with a given scale factor (float).
     */
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

    /**
     * Bundle key used to start the blur dialog with a given blur radius (int).
     */
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

    /**
     * Bundle key used to start the blur dialog with a given dimming effect policy.
     */
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";

    /**
     * Bundle key used to start the blur dialog with a given debug policy.
     */
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;

    /**
     * Retrieve a new instance of the sample fragment.
     *
     * @param radius          blur radius.
     * @param downScaleFactor down scale factor.
     * @param dimming         dimming effect.
     * @param debug           debug policy.
     * @return well instantiated fragment.
     */
    public static SplashDialog newInstance(int radius,
                                                   float downScaleFactor,
                                                   boolean dimming,
                                                   boolean debug) {
        SplashDialog fragment = new SplashDialog();
        Bundle args = new Bundle();
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                radius
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                downScaleFactor
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                dimming
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                debug
        );

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
        mHandler.sendEmptyMessageDelayed(START_COUNT_TIME, 2000);
        reSet();
//        mSplashListener.onShowing();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private SharedPreferences preferences;
    private AssetManager manager;
    private Bitmap mBitmap;


    private void initBitmap() {
//        PlayerInfo info = PlayerInfo.getInfo();
//        String currentImageIndex = info.getCurrentImageName();
//        mCurrentImage = BitmapFactory.decodeFile(currentImageIndex);
//        mCurrentImage = ImageUtil.getSquareBitmap(mCurrentImage);
        Log.i("bitmap", "-splash---------init Bitmap--->");
        if (preferences == null){
            preferences = getActivity().getSharedPreferences(PreferencesItems.PREFERENCES_TITLE, Context.MODE_PRIVATE);
        }
        String currentImgPath = preferences.getString(PreferencesItems.CURRENT_MAP_PATH, PreferencesItems.MAP_PATH_DEFAULT);
        manager = getResources().getAssets();
        try {
            mBitmap = BitmapFactory.decodeStream(manager.open(currentImgPath));

        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (mBitmap == null) {
//            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.puzzle_img_default);
//        }


        mBitmap = ImageUtil.getSquareBitmap(mBitmap);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initBitmap();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.splash_layout, null);
        mImageView = (ImageView) view.findViewById(R.id.iv_currentImage);
        mTextView = (TextView) view.findViewById(R.id.tv_time);

        isPause = false;
//        mImageView.setImageBitmap(mCurrentImage);
        mTextView.setText("倒计时");
        mImageView.setImageBitmap(mBitmap);


        builder.setView(view);
        setCancelable(false);
        return builder.create();
    }


    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.i("splash", "-------->onDismiss");
        isDismiss = true;
        mHandler.removeMessages(START_COUNT_TIME);
        mSplashListener.onDismiss();
    }

    private void reSet() {
        mTimeLast = 3;
        isPause = false;
        isDismiss = false;
    }
}
