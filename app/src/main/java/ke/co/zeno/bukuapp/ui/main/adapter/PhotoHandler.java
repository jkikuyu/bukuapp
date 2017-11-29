package ke.co.zeno.bukuapp.ui.main.adapter;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Jude Kikuyu on 18/11/17.
 */

public class PhotoHandler implements PictureCallback {

    private final Context context;
    private final static String DEBUG_TAG = "CameraActivity";
    private String dir = "buku";

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        File pictureFileDir = context.getDir(dir,MODE_PRIVATE );

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

            Log.d(this.DEBUG_TAG, "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.",
                    Toast.LENGTH_LONG).show();
            return;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "P   ``````````````````````````````````````````````````````````````````````````````  1   11111111111111111ic_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;
        Toast.makeText(context, "filename path:" + filename,
                Toast.LENGTH_LONG).show();
        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Toast.makeText(context, "New Image saved:" + photoFile,
                    Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d(this.DEBUG_TAG, "File" + filename + "not saved: "
                    + error.getMessage());
            Toast.makeText(context, "Image could not be saved.",
                    Toast.LENGTH_LONG).show();
        }
    }


}

