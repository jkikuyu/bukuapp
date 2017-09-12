package ke.co.zeno.bukuapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {
    private String strURL;
    private String user;
    private String pass;
    private ProgressDialog pDialog;
    private List<String> classList =null;
    private Spinner spnClasses = null;
    private Toolbar toolbar;
    private static final String TAG = TeacherActivity.class.getSimpleName();

    public TeacherActivity() {
        strURL = "jdbc:postgresql://10.0.3.2:5432/buku";
                //jdbc:postgresql://127.0.0.1:5432/testdb
        user = "postgres";
        pass = "postgres";


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherscreen);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //spnClasses = (Spinner) findViewById(R.id.spnClassList);
        //new PopulateList().execute();

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class PopulateList extends AsyncTask<String,Void, String> {
        private Connection dbConn;
        private Statement statement;
        private ResultSet rsSchools , rsClasses;
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(TeacherActivity.this);
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

