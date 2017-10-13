package ke.co.zeno.bukuapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ke.co.zeno.bukuapp.R;
/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }
    public void loadLogin(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
