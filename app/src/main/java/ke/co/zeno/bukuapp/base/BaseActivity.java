package ke.co.zeno.bukuapp.base;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.EntypoIcons;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.ui.main.CameraActivity;
import ke.co.zeno.bukuapp.ui.main.HomeActivity;
import ke.co.zeno.bukuapp.ui.main.SignIn;


/**
 * @author Jude Kikuyu
 * date: 21/10/17.
 * ref: https://stackoverflow.com/questions/19451715/same-navigation-drawer-in-different-activities
 */

public class BaseActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    Intent intent;


    public BaseActivity() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }
    protected void onCreateDrawer(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav = mNavigationView.getMenu();
        menuNav.findItem(R.id.nav_home).setIcon(
                new IconDrawable(this, FontAwesomeIcons.fa_home)
                        .colorRes(R.color.ab_icon)
                        .actionBarSize());
        menuNav.findItem(R.id.nav_settings).setIcon(
                new IconDrawable(this, MaterialIcons.md_settings_applications)
                        .colorRes(R.color.ab_icon)
                        .actionBarSize());
        menuNav.findItem(R.id.nav_books).setIcon(
                new IconDrawable(this, FontAwesomeIcons.fa_book)
                        .colorRes(R.color.ab_icon)
                        .actionBarSize());
        menuNav.findItem(R.id.nav_access).setIcon(
                new IconDrawable(this, EntypoIcons.entypo_login)
                        .colorRes(R.color.ab_icon)
                        .actionBarSize());

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.nav_access:
                intent = new Intent(this, SignIn.class);
                this.startActivity(intent);
                break;
            case R.id.nav_books:
                intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                finish();// finishes the current activity
                break;
            default:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();// finishes the current activity


        }


/*
        if (id == R.id.nav_camera1) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

    }
    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        onCreateDrawer();
    }
}
