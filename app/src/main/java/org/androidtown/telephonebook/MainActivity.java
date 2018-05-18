package org.androidtown.telephonebook;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.preference.Preference;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.androidtown.telephonebook.adapter.PhoneAdapter;
import org.androidtown.telephonebook.domain.Phone;
import org.androidtown.telephonebook.domain.PhoneDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

  RecyclerView recycler;
  PhoneAdapter adapter;

  static String[] permissions = {
    Manifest.permission.READ_CONTACTS,
    Manifest.permission.WRITE_CONTACTS,
    Manifest.permission.CALL_PHONE
  };

  public MainActivity() {
    super(permissions);
  }

  @Override
  public void init() {
    setContentView(R.layout.activity_main);
    List<Phone> list = getData();
    setRecyler(list);
  }

  private List<Phone> getData() {
    PhoneDAO phoneDao = new PhoneDAO();
    return phoneDao.getPhoneList(this);
  }

  private void setRecyler(List<Phone> list) {
    recycler = findViewById(R.id.recyclerView);

    adapter = new PhoneAdapter();
    recycler.setAdapter(adapter);
    recycler.setLayoutManager(new LinearLayoutManager(this));
    adapter.setDataAndRefresh(list);
  }

}
