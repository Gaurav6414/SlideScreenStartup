package com.grv.dev.slidescreenstartup;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlidePager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button prevBtn;
    private Button nextBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlidePager = findViewById(R.id.slide);
        mDotLayout =findViewById(R.id.dots);

        prevBtn = findViewById(R.id.backbtn);
        nextBtn = findViewById(R.id.nextbtn);

        sliderAdapter = new SliderAdapter(this);
        mSlidePager.setAdapter(sliderAdapter);

        setmDotLayout(0);

        mSlidePager.addOnPageChangeListener(viewListener);

        //onClickListener

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidePager.setCurrentItem(mCurrentPage+1);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidePager.setCurrentItem(mCurrentPage-1 );
            }
        });


    }

    public void setmDotLayout(int position){

        mDots = new TextView[3];

        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);

        }

        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setmDotLayout(position);

            mCurrentPage = position;

            if (position==0){

                prevBtn.setEnabled(false);
                nextBtn.setEnabled(true);
                prevBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText("NEXT");
                prevBtn.setText("");
            }
            else if (position==mDots.length -1){

                prevBtn.setEnabled(true);
                nextBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);


                nextBtn.setText("FINISH");
                prevBtn.setText("BACK");


            }else {

                prevBtn.setEnabled(true);
                nextBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("NEXT");
                prevBtn.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
