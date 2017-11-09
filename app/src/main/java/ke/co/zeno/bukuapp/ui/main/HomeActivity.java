package ke.co.zeno.bukuapp.ui.main;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.base.BaseActivity;
import ke.co.zeno.bukuapp.data.DatabaseHelper;


/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public class HomeActivity extends BaseActivity{
    private String strURL;
    private String user;
    private String pass;
    private ProgressDialog pDialog;
    private List<String> classList =null;
    private Spinner spnClasses = null;
    private DatabaseHelper dbHelper;

    private static final String TAG = HomeActivity.class.getSimpleName();


/*
    @BindView(R.id.streamsRecycler)
    public RecyclerView streamsRecycler;
*/

    public HomeActivity() {
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

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.setContentView(R.layout.activity_home);
        //ButterKnife.bind(this);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        StreamFragment streamFragment = new StreamFragment();
        fragmentTransaction.add(R.id.fragment_container, streamFragment, "streams");
        fragmentTransaction.commit();


    }
    /**
     *
     *  @author Jude Kikuyu
     *  date: 10/10/2017
     */
  /*  private void setUpRecyclerView() {

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


        LinearLayoutManager layoutManagerStart
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        startSnapRecyclerView.setLayoutManager(layoutManagerStart);
        appListStartAdapter = new AppListAdapter(this);
        startSnapRecyclerView.setAdapter(appListStartAdapter);

        SnapHelper snapHelperStart = new StartSnapHelper();
        snapHelperStart.attachToRecyclerView(startSnapRecyclerView);

    }
*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int fragmentCount = 0;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
            if (fragmentCount > 1) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }

        }
    }





    public class PopulateList extends AsyncTask<String,Void, String> {
        private Connection dbConn;
        private Statement statement;
        private ResultSet rsSchools , rsClasses;
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(HomeActivity.this);
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
    public DatabaseHelper getDbHelper(){
        return dbHelper;
    }
}

