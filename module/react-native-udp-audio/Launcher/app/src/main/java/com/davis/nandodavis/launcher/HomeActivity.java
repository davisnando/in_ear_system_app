package com.davis.nandodavis.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    ViewPager mViewPager;
    int cellHeight;

    int NUMBER_OF_ROWS = 5;
    int DRAWER_PEEK_HEIGHT = 100;
    String PREFS_NAME = "NovaPrefs";

    int numRow = 0, numColumn = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LinearLayout mTopDrawerLayout = findViewById(R.id.topDrawerLayout);
        mTopDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                DRAWER_PEEK_HEIGHT = mTopDrawerLayout.getHeight();
                initializeHome();
                initializeDrawer();
            }
        });
    }

    public void showApps(View v){
        Intent i = new Intent(this, AppsListActivity.class);
        startActivity(i);
    }

    ViewPagerAdapter mViewPagerAdapter;
    private void initializeHome() {
        ArrayList<PagerObject> pagerAppList = new ArrayList<>();

        ArrayList<AppDetail> appList1 = new ArrayList<>();
        ArrayList<AppDetail> appList2 = new ArrayList<>();
        ArrayList<AppDetail> appList3 = new ArrayList<>();
        for (int i = 0; i < numColumn*numRow ;i++)
            appList1.add(new AppDetail("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));
        for (int i = 0; i < numColumn*numRow ;i++)
            appList2.add(new AppDetail("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));
        for (int i = 0; i < numColumn*numRow ;i++)
            appList3.add(new AppDetail("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));

        pagerAppList.add(new PagerObject(appList1));
        pagerAppList.add(new PagerObject(appList2));
        pagerAppList.add(new PagerObject(appList3));

        cellHeight = (getDisplayContentHeight() - DRAWER_PEEK_HEIGHT) / numRow ;

        mViewPager = findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(this, pagerAppList, cellHeight, numColumn);
        mViewPager.setAdapter(mViewPagerAdapter);
    }



    List<AppObject> installedAppList = new ArrayList<>();
    GridView mDrawerGridView;
    BottomSheetBehavior mBottomSheetBehavior;
    private void initializeDrawer() {
        View mBottomSheet =findViewById(R.id.bottomSheet);
        mDrawerGridView = findViewById(R.id.drawerGrid);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setPeekHeight(DRAWER_PEEK_HEIGHT);

        installedAppList = getInstalledAppList();

        mDrawerGridView.setAdapter(new AppAdapter(this, installedAppList, cellHeight));

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(mAppDrag != null)
                    return;

                if(newState == BottomSheetBehavior.STATE_COLLAPSED && mDrawerGridView.getChildAt(0).getY() != 0)
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if(newState == BottomSheetBehavior.STATE_DRAGGING && mDrawerGridView.getChildAt(0).getY() != 0)
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

    }

}