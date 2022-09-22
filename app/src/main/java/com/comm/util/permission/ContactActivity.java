package com.comm.util.permission;

import java.util.List;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.comm.util.R;
import com.comm.util.base.BaseActivity;

/**
 * 获取通讯录
 * https://developer.android.com/training/contacts-provider/retrieve-names
 * https://www.jianshu.com/p/baadc3f790dd
 * https://blog.csdn.net/lb377463323/article/details/71480423
 */
public class ContactActivity extends BaseActivity {

    public static final int CONTACT_REQUEST_CODE = 3;

    @BindView(R.id.lv_contacts)
    ListView lvContact;

    //@Override
    //protected int setLayoutId() {
    //    return R.layout.activity_contact;
    //}

    @Override
    protected void initView() {
        super.initView();
    }

    @OnClick(R.id.bt_contact)
    public void btContact() {
        checkPermm();
    }


    private String[] getContacts() {
        //联系人的Uri，也就是content://com.android.contacts/contacts
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //指定获取_id和display_name两列数据，display_name即为姓名
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        String[] arr = new String[cursor.getCount()];
        int i = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                //获取姓名
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                //指定获取NUMBER这一列数据
                String[] phoneProjection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                arr[i] = id + ",phone：" + name;
                //根据联系人的ID获取此人的电话号码
                Cursor phonesCursor = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        phoneProjection
                        , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id
                        , null
                        , null);
                //因为每个联系人可能有多个电话号码，所以需要遍历
                if (phonesCursor != null && phonesCursor.moveToFirst()) {
                    do {
                        String num = phonesCursor.getString(0);
                        arr[i] += ",num: " + num;
                    } while (phonesCursor.moveToNext());
                }
                i++;
            } while (cursor.moveToNext());
        }
        return arr;
    }


    private void checkPermm() {
        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
//                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时，shouldShowRequestPermissionRationale ()方法返回的是true
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_REQUEST_CODE);
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_REQUEST_CODE);
//                }
            } else {
                getContacts();
            }
        } else {
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CONTACT_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getContacts();

                } else {
                }
                return;
            }
        }
    }

    public  static  class  ContactsBean{
        private final String name;
        private final List<String> numList;

        public ContactsBean(String name, List<String> numList) {
            this.name = name;
            this.numList = numList;
        }
    }

}
