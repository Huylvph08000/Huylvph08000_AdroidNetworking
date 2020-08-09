package com.example.huylvph08000_adroidnetworking;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class ImageSaveHelper implements Target {  //sử dụng interface Target của Picasso đẻ lưu ảnh về storage
    Context context;  //cần để gọi activity mới sau khi tải xong
    WeakReference<AlertDialog> alertDialogWeakReference;  //ánh xạ tới dialog ở Main2Activity
    WeakReference<ContentResolver> contentResolverWeakReference;  //ánh xạ tới contentResolver ở Main2Activity, tham chiếu yếu giúp quản lí bộ nhơ tốt hơn
    String name;
    String desc;

    public ImageSaveHelper(Context context, AlertDialog alertDialog, ContentResolver contentResolver, String name, String desc) {
        this.context = context;
        this.alertDialogWeakReference = new WeakReference<AlertDialog>(alertDialog);
        this.contentResolverWeakReference = new WeakReference<ContentResolver>(contentResolver);
        this.name = name;
        this.desc = desc;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        // Đây là nơi viết code xử lý sau khi tải thành công image
        ContentResolver resolver = contentResolverWeakReference.get(); // get ContentResolver từ ánh xạ
        AlertDialog dialog = alertDialogWeakReference.get();  // get AlertDialog từ ánh xạ
        if (resolver != null){
            MediaStore.Images.Media.insertImage(resolver, bitmap, name, desc); // Truyền tham số vào
            dialog.dismiss();  // Dismiss dialog sau khi save xong
            Toast.makeText(context, "Đã tải xong!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        // Đây là nơi viết code xử lý sau khi không tải được image
        Toast.makeText(context, "Tải thất bại!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        // Đây là nơi viết code xử lý khi đang tải image
        Toast.makeText(context, "Đang tải...", Toast.LENGTH_LONG).show();
    }
}
