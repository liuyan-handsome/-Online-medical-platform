package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class shimingrenzheng extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_botton_dialog1;
    private ImageView btn_botton_dialog2;
    Dialog mCameraDialog;
    private TextView next;

    //打开相机
    final int TAKE_PHOTO = 1;
    final int TAKE_PHOTO2 = 2;
    ImageView iv_photo;
    ImageView iv_photo2;
    final int CHOOSE_PHOTO = 3;
    final int CHOOSE_PHOTO2 = 4;
    Uri imageUri;
    Intent back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimingrenzheng);
        iv_photo = findViewById(R.id.shangchuan1);
        iv_photo2 = findViewById(R.id.shangchuan2);

        btn_botton_dialog1 = (ImageView) findViewById(R.id.shangchuan1);
        btn_botton_dialog2 = (ImageView) findViewById(R.id.shangchuan2);
        btn_botton_dialog1.setOnClickListener(this);
        btn_botton_dialog2.setOnClickListener(this);
        next = (TextView) findViewById(R.id.forword);
        Intent intent = new Intent(this,doctor_home.class);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(shimingrenzheng.this, "上传成功", Toast.LENGTH_SHORT).show();

                // 注意！！！此处需要返回页面
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangchuan2:
                setDialog2();
                break;
            case R.id.shangchuan1:
                setDialog();
                break;
           /* case R.id.btn_choose_img:
                Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();
                openAlbum();
                mCameraDialog.dismiss();
                break;*/
            case R.id.btn_open_camera:
                Toast.makeText(this, "使用相机", Toast.LENGTH_SHORT).show();

                File output = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (output.exists()) {
                        output.delete();
                    }
                    output.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
//图片的保存路径
                    imageUri = FileProvider.getUriForFile(shimingrenzheng.this, "com.example.myapplication.fileprovider", output);
                } else {
                    imageUri = Uri.fromFile(output);
                }
                //跳转界面到系统自带的拍照界面
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;
            /*case R.id.btn_choose_img2:
                Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();
                openAlbum2();
                mCameraDialog.dismiss();
                break;
*/
            case R.id.btn_open_camera2:
                Toast.makeText(this, "使用相机", Toast.LENGTH_SHORT).show();

                File output2 = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (output2.exists()) {
                        output2.delete();
                    }
                    output2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
//图片的保存路径
                    imageUri = FileProvider.getUriForFile(shimingrenzheng.this, "com.example.myapplication.fileprovider", output2);
                } else {
                    imageUri = Uri.fromFile(output2);
                }
                //跳转界面到系统自带的拍照界面
                Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent2, TAKE_PHOTO2);
                mCameraDialog.dismiss();
                break;

            case R.id.btn_cancel2:
                mCameraDialog.dismiss();
                break;
        }
    }

    private void setDialog() {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.botton_dialog, null);
        //初始化视图
        //root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.DialogAnimation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void setDialog2() {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.botton_dialog2, null);
        //初始化视图
        //root.findViewById(R.id.btn_choose_img2).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera2).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel2).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.DialogAnimation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 使用try让程序运行在内报错
                    try {
                        //将图片保存
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        iv_photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
            case TAKE_PHOTO2:
                if (resultCode == RESULT_OK) {
                    // 使用try让程序运行在内报错
                    try {
                        //将图片保存
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        iv_photo2.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /*case CHOOSE_PHOTO:







/*

                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data);
                }
                else {
                    // 4.4以下系统使用这个方法处理图片
                    handleImageBeforeKitKat(data);
                }
                break;
            case CHOOSE_PHOTO2:

                break;*/
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }
    private void openAlbum2() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO2); // 打开相册
    }
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);

        if (DocumentsContract.isDocumentUri(this,uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            iv_photo.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}