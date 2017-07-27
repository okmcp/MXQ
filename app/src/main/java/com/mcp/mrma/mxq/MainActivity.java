package com.mcp.mrma.mxq;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mcp.mrma.mxq.fragment.Fragment_contacts;
import com.mcp.mrma.mxq.fragment.Fragment_dynamic;
import com.mcp.mrma.mxq.fragment.Fragment_message;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;

    private BottomNavigationBar bottomNavigationBar;
    private TextView tv;
    private ImageView iv;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //给toolbar设置新的
//        toolbar.setNavigationIcon(R.drawable.ic_menu_camera);
        setSupportActionBar(toolbar);
        //将toolbar上原来的标题屏蔽掉
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tv = (TextView) findViewById(R.id.toolbar_tv);

        iv = (ImageView) findViewById(R.id.toolbar_iv);


        View popupview = getLayoutInflater().inflate(R.layout.popupwindow_fragment_message_more, null);
        popupWindow = new PopupWindow(popupview, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //点击空白处消失
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //将侧面三个横杠唤出侧滑的图标给屏蔽掉
        toggle.setDrawerIndicatorEnabled(false);
        //设置新的图标
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_camera);
        //给新图标设置点击事件，点击图标是唤出抽屉菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationBar = new BottomNavigationBar(MainActivity.this);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_barr);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_share, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu_camera, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu_manage, "动态")).setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化;
        initFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, tv.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);
                popupWindow.showAsDropDown(view);
            }
        });

    }

    //侧滑菜单处理的几个方法
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //设置右上角三个点的“设置菜单”
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
//        if(id == android.R.id.home){
//            toggle.onOptionsItemSelected(item);
//
//            return true;
//        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //实现bottomnavigationbar的三个方法
    @Override
    public void onTabSelected(int position) {
        FragmentManager fr = getFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();

        Fragment_message fragment_message = new Fragment_message();
        Fragment_contacts fragment_contacts = new Fragment_contacts();
        Fragment_dynamic fragment_dynamic = new Fragment_dynamic();

        switch (position) {
            case 0:
                ft.replace(R.id.fragmen_container, fragment_message);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.fragmen_container, fragment_contacts);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.fragmen_container, fragment_dynamic);
                ft.commit();
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public void initFragment() {
        Fragment_message fragmentmessage = new Fragment_message();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmen_container, fragmentmessage);
        fragmentTransaction.commit();

    }
}
