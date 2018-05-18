package org.androidtown.telephonebook.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {

  public List<Phone> getPhoneList(Context context) {
    List<Phone> phoneList = new ArrayList<>();

    // 0. 컨텐트 리졸버
    ContentResolver resolver = context.getContentResolver();

    // 1. 데이터 주소
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//    Uri uri = ContactsContract.Contacts.CONTENT_URI;
    // 2. 가져올 데이터 컬럼을 정의
    String[] projection = {
      ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
      ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
      ContactsContract.CommonDataKinds.Phone.NUMBER
    };
    // 3. 쿼리
    String order = projection[1] + " ASC";
    Cursor cursor = resolver.query(uri, projection, null, null, order);
//    Cursor cursor = resolver.query(uri, projection, null, null, null);

    // 4. 결과값 반복문으로 처리
    if (cursor != null) {
      while (cursor.moveToNext()) {
        Phone phone = new Phone();
        phone.id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
        phone.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        phone.phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        phoneList.add(phone);
      }
      cursor.close(); // 파일, 데이터, 네트웍을 다루는 경우 꼭 호출.
    }

    return phoneList;
  }

}
