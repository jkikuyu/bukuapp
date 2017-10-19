package ke.co.zeno.bukuapp.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.EntypoIcons;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.local.StreamDataHelper;
import ke.co.zeno.bukuapp.model.Stream;
import ke.co.zeno.bukuapp.ui.main.adapter.StreamHelperAdapter;


/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private String strURL;
    private String user;
    private String pass;
    private ProgressDialog pDialog;
    private List<String> classList =null;
    private Spinner spnClasses = null;
    private Toolbar toolbar;
    private static final String TAG = MainActivity.class.getSimpleName();

    private StreamHelperAdapter mStreamHelperAdapter;

/*
    @BindView(R.id.streamsRecycler)
    public RecyclerView streamsRecycler;
*/

    public MainActivity() {
        strURL = "jdbc:postgresql://10.0.3.2:5432/buku";
                //jdbc:postgresql://127.0.0.1:5432/testdb
        user = "postgres";
        pass = "postgres";
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUpRecyclerView();

    }
    /**
     *
     * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
     * @author amitshekhar
     *  dat.e: 13/01/17.
     *  modified by
     *  @author Jude Kikuyu
     *  date: 10/10/2017
     */
    private void setUpRecyclerView() {

        LinearLayoutManager layoutManagerCenter
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView streamsRecycler = (RecyclerView)findViewById(R.id.streamsRecycler);
        streamsRecycler.setLayoutManager(layoutManagerCenter);
        mStreamHelperAdapter = new StreamHelperAdapter(this);
        List<Stream> streamList = new StreamDataHelper().getStreamList();
        mStreamHelperAdapter.updateList(streamList);
        streamsRecycler.setAdapter(mStreamHelperAdapter);
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(streamsRecycler);

/*
        LinearLayoutManager layoutManagerStart
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        startSnapRecyclerView.setLayoutManager(layoutManagerStart);
        appListStartAdapter = new AppListAdapter(this);
        startSnapRecyclerView.setAdapter(appListStartAdapter);

        SnapHelper snapHelperStart = new StartSnapHelper();
        snapHelperStart.attachToRecyclerView(startSnapRecyclerView);
*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.nav_access:
                Intent intent = new Intent(this, SignIn.class);
                this.startActivity(intent);
                break;
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

    public class PopulateList extends AsyncTask<String,Void, String> {
        private Connection dbConn;
        private Statement statement;
        private ResultSet rsSchools , rsClasses;
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching classes ...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateSpinner();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
/*
                URL url = new URL(strURL);

                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
*/
                // Load the postgreSQL driver
                    Class.forName("org.postgresql.Driver");
                    // Setup the connection with the DB
                    dbConn = DriverManager.getConnection(strURL, user,pass);

                    // Statements allow to issue SQL queries to the database
                    statement = dbConn.createStatement();
                    // Result set get the result of the SQL query
                    rsClasses = statement
                        .executeQuery("select * from Parameter where groupId=1");
                    classList = new ArrayList<String>();
                    while (rsClasses.next()){
                        String classItem = rsClasses.getString("value");
                        classList.add(classItem);

                    }

            }

            catch(ClassNotFoundException e){
                    System.out.println("unable to locate database driver");
             }
            catch(Exception e) {
                System.out.println("unable to connect to server");
            }
            return null;

        }

    }
    private void populateSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, classList);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnClasses.setAdapter(spinnerAdapter);
    }

}

