package com.example.billardball;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class Billiard extends View {

    ShapeDrawable mBilliardBall;
    int[] cx = {50, 50, 50};
    int[] cy = {50, 50, 50};
    int radius = 50;
    int dir_x = 1;
    int dir_y = 1;

    final static int step_x = 5; // x 이동량
    final static int step_y = 15; // y 이동량

    int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels; // 화면 크기 가지고 오기
    int screen_hight = Resources.getSystem().getDisplayMetrics().heightPixels; // 화면 크기 가지고 오기


    public Billiard(Context context) {
        super(context);
        mBilliardBall = new ShapeDrawable(new OvalShape());
        mBilliardBall.getPaint().setColor(Color.RED);
        IWantTen one = new IWantTen(1);
        IWantTen two = new IWantTen(2);
        IWantTen three = new IWantTen(3);

        one.start();
        two.start();
        three.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            mBilliardBall.setBounds(cx[i] - radius, cy[i] - radius, cx[i] + radius, cy[i] + radius);
            mBilliardBall.draw(canvas);
        }


        invalidate(); // 온드로우 재호출
    }


    public class IWantTen extends Thread {
        double mcx;
        double mcy;
        double mradius;
        double mdir_x;
        double mdir_y;
        int number;

        public IWantTen(int setnumber) {
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 50;
            mcx = randomNumber;
            mcy = randomNumber;
            mradius = radius;
            mdir_x = dir_x;
            mdir_y = dir_y;
            number = setnumber-1;
        }

        @Override
        public void run() {
            while (true) {
                Random random = new Random();
                //이동 방향 결정
                if (mcx <= mradius) {
                    mdir_x = random.nextDouble()*1.1; // down 1.1은 속도 배수
                } else if (mcx >= screen_width - mradius) {
                    mdir_x = -random.nextDouble()*1.1; // up
                }

                if (mcy <= radius) {
                    mdir_y = random.nextDouble()*1.1;
                } else if (mcy >= screen_hight - mradius) {
                    mdir_y = -random.nextDouble()*1.1;
                }
                mcx = mcx + (step_x) * mdir_x;
                mcy = mcy + (step_y) * mdir_y;
                cx[number] = (int)mcx;
                cy[number] = (int)mcy;
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

