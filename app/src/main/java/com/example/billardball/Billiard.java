package com.example.billardball;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class Billiard extends View {

    ShapeDrawable mBilliardBall;
    int cx=50;
    int cy=50;
    int radius = 50;
    int dir_x=1;
    int dir_y=1;

    final static int step_x = 5; // x 이동량
    final static int step_y = 15; // y 이동량

    int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels; // 화면 크기 가지고 오기
    int screen_hight = Resources.getSystem().getDisplayMetrics().heightPixels; // 화면 크기 가지고 오기
    IWantTen drawTen = new IWantTen(); // 스레드를 위하여


    public Billiard(Context context) {
        super(context);
        mBilliardBall = new ShapeDrawable(new OvalShape());
        mBilliardBall.getPaint().setColor(Color.RED);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        drawTen.setCanvas(canvas);
        drawTen.start();

      //  invalidate(); // 온드로우 재호출
      //  drawTen.interrupt();
    }


    public class IWantTen extends Thread {
        public Canvas canvas;
        int mcx;
        int mcy;
        int mradius;
        int mdir_x;
        int mdir_y;
        public void setCanvas(Canvas myCanvas){
            canvas = myCanvas;
            mcx=cx;
            mcy=cy;
            mradius=radius;
            mdir_x=dir_x;
            mdir_y=dir_y;

        }

        @Override
        public void run() {
            //이동 방향 결정
            if (mcx <= mradius){
                mdir_x =1; // down
            } else if (mcx >= screen_width - mradius) {
                mdir_x = -1; // up
            }

            if (mcy <=radius){
                mdir_y = 1;
            } else if (mcy>= screen_hight - mradius){
                mdir_y = -1;
            }
            mcx = mcx+step_x*mdir_x;
            mcy = mcy+step_y*mdir_y;

            mBilliardBall.setBounds(mcx-radius,mcy-radius,mcx+radius,mcy-radius);
            mBilliardBall.draw(canvas);
        }
    }

}
