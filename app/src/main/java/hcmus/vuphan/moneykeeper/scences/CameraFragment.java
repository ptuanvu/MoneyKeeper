package hcmus.vuphan.moneykeeper.scences;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.dialogs.CUChiTieuThang;

/**
 * Created by monster on 15/06/2016.
 */
public class CameraFragment extends Fragment implements View.OnClickListener {
    private static final int SELECT_FILE_ACTIVITY_REQUEST_CODE = 101;
    static FragmentManager fragmentManager;
    Context context;

    private Uri fileUri;

    // Cac thanh phan trong form
    Button btnCamera, btnGallery;
    TextView tvURL;

    // Cac hang so
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public void setContext(Context context) {
        this.context = context;
    }

    public static CameraFragment createInstance(Context context) {
        CameraFragment cf = new CameraFragment();
        cf.setContext(context);
        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.camera_layout, container, false);
        btnCamera = (Button) view.findViewById(R.id.btnCamera);
        btnGallery = (Button) view.findViewById(R.id.btnGallery);
        tvURL = (TextView) view.findViewById(R.id.tvURL);
        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCamera:
                //OnCameraButtonClick();
                CUChiTieuThang cuChiTieuThang = CUChiTieuThang.concreateInstance(null, context);
                cuChiTieuThang.show(fragmentManager, "dialog");
                break;
            case R.id.btnGallery:
                OnGalleryButtonClick();
                break;
        }
    }

    private void OnGalleryButtonClick() {
        fileUri =  getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select File"), SELECT_FILE_ACTIVITY_REQUEST_CODE);
    }

    private void OnCameraButtonClick() {

        Intent callCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        //start activity for result
        startActivityForResult(callCameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MoneyKeeperImage");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MoneyKeeperImage", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            tvURL.setText(fileUri.toString());
        }

        if (requestCode == SELECT_FILE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap bm=null;
            if (data != null) {
                try {
                    bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bm != null) {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(fileUri.getPath());
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            tvURL.setText(fileUri.toString());
        }
    }


}
