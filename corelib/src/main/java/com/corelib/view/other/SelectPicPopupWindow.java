//package com.corelib.view.other;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Button;
//import android.widget.PopupWindow;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import com.wolearn.yuxiaomao.R;
//
//public class SelectPicPopupWindow extends PopupWindow {
//
//    public static final int CODE_CAPTURE = 0;
//    public static final int CODE_IMAGE = 1;
//    public static final int CODE_RESULT = 2;
//    private File mSaveDir;
//
//    private final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/zhaoshangbao/temp/");
//
//
//
//    private Button btn_take_photo, btn_pick_photo, btn_cancel;
//    private View mMenuView;
//
//    private Activity context;
//
//    public SelectPicPopupWindow(final Activity context) {
//        super(context);
//        this.context = context;
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.alert_dialog, null);
//        btn_take_photo = (Button) mMenuView.findViewById(R.id.btn_take_photo);
//        btn_pick_photo = (Button) mMenuView.findViewById(R.id.btn_select_photo);
//        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
//        //取消按钮
//        btn_cancel.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View v) {
//                //销毁弹出框
//                dismiss();
//            }
//        });
//        //设置按钮监听
//        btn_pick_photo.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pickPhoto();
//                dismiss();
//            }
//        });
//        btn_take_photo.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                takePhoto();
//                dismiss();
//            }
//
//        });
//        //设置SelectPicPopupWindow的View
//        this.setContentView(mMenuView);
//        //设置SelectPicPopupWindow弹出窗体的宽
//        this.setWidth(LayoutParams.FILL_PARENT);
//        //设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.PopupAnimation);
//        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        //设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        mMenuView.setOnTouchListener(new OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
//
//    }
//
//    private void takePhoto() {
//        File mCurrentPhotoFile;
//        PHOTO_DIR.mkdirs();
//        mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
//        mSaveDir = mCurrentPhotoFile;
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
//        context.startActivityForResult(intent, CODE_CAPTURE);
//    }
//
//    private void pickPhoto() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        context.startActivityForResult(intent, CODE_IMAGE);
//    }
//
//    /**
//     * 设置照片的名字
//     *
//     * @return 返回照片的名字
//     */
//    private String getPhotoFileName() {
//        return "IMG_" + System.currentTimeMillis() + ".JPEG";
//    }
//
//    public File getSaveDir() {
//        return mSaveDir;
//    }
//
//    public static void saveMyBitmap(String saved_temp_image_file, Bitmap mBitmap) {
//        File f = new File(saved_temp_image_file);
//        try {
//            f.createNewFile();
//        } catch (IOException e) {
//
//        }
//        FileOutputStream fOut = null;
//        try {
//            fOut = new FileOutputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//        try {
//            fOut.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fOut.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
