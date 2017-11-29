package ke.co.zeno.bukuapp.ui.main;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.base.BaseActivity;
import ke.co.zeno.bukuapp.ui.main.adapter.PhotoHandler;

//https://stackoverflow.com/questions/10913682/how-to-capture-and-save-an-image-using-custom-camera-in-android
public class CameraActivity extends BaseActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private final static String DEBUG_TAG = "CameraActivity";
    private Camera mCamera;
    private CameraPreview mCameraPreview;
    private int cameraId = 0;
    private String dir = "buku";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_camera);
        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);

        if (!this.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                    .show();
        } else {
            cameraId = findBackFacingCamera();
            if (cameraId < 0) {
                Toast.makeText(this, "No Back facing camera found.",
                        Toast.LENGTH_LONG).show();
            } else {
                try {
                    mCamera = Camera.open(cameraId);
                }
                catch(Exception e){
                    Log.e(getString(R.string.app_name), "failed to open Camera");
                    e.printStackTrace();
                }
            }
        }

    }

   public void onClick(View v) {
       mCamera.startPreview();
       PhotoHandler pHandler = new PhotoHandler(getApplicationContext());
       mCamera.takePicture(null, null, pHandler);

    }
    private int findBackFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
                Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }
    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }

/*
    PictureCallback mPicture = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
            }
        }
    }


    public void onPictureTaken(byte[] data, Camera camera) {
        File pictureFileDir = getDir(dir, MODE_PRIVATE);

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

            Log.d(this.DEBUG_TAG, "Can't create directory to save image.");
            Toast.makeText(this, "Can't create directory to save image.",
                    Toast.LENGTH_LONG).show();
            return;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Toast.makeText(this, "New Image saved:" + photoFile,
                    Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d(this.DEBUG_TAG, "File" + filename + "not saved: "
                    + error.getMessage());
            Toast.makeText(this, "Image could not be saved.",
                    Toast.LENGTH_LONG).show();
        }
    }
    */
    @Override
    protected void onPause() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        super.onPause();
    }

}

