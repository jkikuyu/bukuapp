package ke.co.zeno.bukuapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.joanzapata.iconify.widget.IconTextView;

import java.io.File;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.base.BaseActivity;

public class CameraActivity extends BaseActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_camera);
        IconTextView bookImage = (IconTextView) findViewById(R.id.bookImage);
        bookImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/MyFolder", "myImage" + ".jpg");
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(photo);
        }
    }
}
