package ke.co.zeno.bukuapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.ui.base.BaseActivity;
import ke.co.zeno.bukuapp.ui.main.adapter.StreamHelperAdapter;
/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public class SignIn extends  BaseActivity implements StreamHelperAdapter.ClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);


    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(this, SignIn.class);
        this.startActivity(intent);

    }
}
