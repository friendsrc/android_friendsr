package com.example.friendsearch;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector mGestureDetector;

    private float mSwipeDistanceThreshold = 0;
    private float mSwipeVelocityThreshold = 0;

    private float mPrevX;
    private float mPrevY;

    private float mDx;
    private float mDy;

    public OnSwipeTouchListener(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureListener());
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float dx = e2.getRawX() - e1.getRawX();
            float dy = e2.getRawY() - e1.getRawY();

//            if (dx > 0) {
//                onSwipeRight(dx);
//                return true;
//            } else if (dx < 0) {
//                onSwipeLeft(-dx);
//                return true;
//            }

            if ((Math.abs(dx) > Math.abs(dy)) &&
                    (Math.abs(dx) > mSwipeDistanceThreshold) &&
                    (Math.abs(velocityX) > mSwipeVelocityThreshold)) {
                if (dx > 0) {
                    onSwipeRight(dx);
                } else {
                    onSwipeLeft(-dx);
                }

                return true;
            }

            return false;
        }
    }

    public void onSwipeRight(float dx) {
    }

    public void onSwipeLeft(float dx) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        boolean gesture = false;

        if (v.getId() == R.id.poeple_image) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPrevX = event.getRawX();
                    mPrevY = event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float currX = event.getRawX();
                    float currY = event.getRawY();

                    mDx = currX - mPrevX;
                    mDy = currY - mPrevY;
                    break;

                case MotionEvent.ACTION_UP:
                    if ((Math.abs(mDx) > Math.abs(mDy)) &&
                            (Math.abs(mDx) > mSwipeDistanceThreshold)) {
//                    if (Math.abs(mDx) > 0) {
                        if (mDx > 0) {
                            onSwipeRight(mDx);
                        } else {
                            onSwipeLeft(-mDx);
                        }

                        return true;
                    }
            }

            gesture = mGestureDetector.onTouchEvent(event);
        }

        return gesture;
    }
}