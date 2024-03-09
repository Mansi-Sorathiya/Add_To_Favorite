package com.example.log_in;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.getResources;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

public class Image_Adapter extends BaseAdapter {
    private GridView_Activity gridViewActivity;
    private List<byte[]> imageList;
    Fav_Database db_fav;
    int[] img;
    public static File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private File downloadFile;

    public Image_Adapter(GridView_Activity gridViewActivity, int[] img, Fav_Database db_fav) {
        this.gridViewActivity = gridViewActivity;
        this.img = img;
        this.db_fav=db_fav;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(gridViewActivity).inflate(R.layout.grid_item, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.grid_item_image);
        Button fav = view.findViewById(R.id.fav);
        Button save = view.findViewById(R.id.save);

        imageView.setImageResource(img[i]);
        Drawable drawable = gridViewActivity.getResources().getDrawable(img[i]);

        // Convert the drawable to a Bitmap
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(gridViewActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ContentResolver resolver = gridViewActivity.getContentResolver();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, String.valueOf(downloadFile));
                        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

                        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                        try (OutputStream fos = resolver.openOutputStream(Objects.requireNonNull(imageUri))) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            Toast.makeText(gridViewActivity, "Image saved to internal storage", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(gridViewActivity, "Failed to save image", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Use the existing saveImageToInternalStorage method for versions below Android 10
//                        saveImageToInternalStorage(bitmap, String.valueOf("im"+downloadFile));
                        saveImageToInternalStorage(bitmap, "image_" + System.currentTimeMillis() + ".jpg");
                    }


                } else {
                    gridViewActivity.askpermission();
                }
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(gridViewActivity, "Fav Images", Toast.LENGTH_SHORT).show();

                // Get the drawable from the resource ID
                Drawable drawable = gridViewActivity.getResources().getDrawable(img[i]);

                // Convert the drawable to a Bitmap
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                // Convert the Bitmap to a byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] imageByteArray = stream.toByteArray();

                // Add the image to the database
                if (db_fav != null) {
                    db_fav.addImages(imageByteArray);
                } else {
                    Toast.makeText(gridViewActivity, "Fav Database is null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void saveImageToInternalStorage(Bitmap bitmap, String downloadFile) {
        ContextWrapper cw = new ContextWrapper(gridViewActivity.getApplicationContext());

        // Path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // Create imageDir
        File myPath = new File(directory, downloadFile);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(gridViewActivity, "Image saved to internal storage", Toast.LENGTH_SHORT).show();

            MediaScannerConnection.scanFile(
                    gridViewActivity,
                    new String[]{myPath.getAbsolutePath()},
                    null,
                    (file, uri) -> {
                        Log.i("MediaScanner", "Scanned " + file);
                        Log.i("MediaScanner", "URI: " + uri);
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(gridViewActivity, "Failed to save image", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
