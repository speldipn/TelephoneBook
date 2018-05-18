package org.androidtown.telephonebook.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidtown.telephonebook.MainActivity;
import org.androidtown.telephonebook.R;
import org.androidtown.telephonebook.domain.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.Holder> {
  List<Phone> phoneList = null;

  public PhoneAdapter() {
  }

  public void setDataAndRefresh(List<Phone> data) {
    this.phoneList = data;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder( LayoutInflater.from(parent.getContext())
      .inflate(R.layout.list_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    Phone phone = phoneList.get(position);
    holder.setId(phone.id);
    holder.setName(phone.name);
    holder.setPhoneNumber(phone.phone);
    holder.phone = phone;
  }

  @Override
  public int getItemCount() {
    return phoneList.size();
  }

  public class Holder extends RecyclerView.ViewHolder {
    private TextView id;
    private TextView name;
    private TextView number;
    Button btnCall;
    LinearLayout item;
    Phone phone;

    public Holder(View itemView) {
      super(itemView);
      id = itemView.findViewById(R.id.id);
      name = itemView.findViewById(R.id.name);
      number = itemView.findViewById(R.id.phone);
      item = itemView.findViewById(R.id.item);
      btnCall = itemView.findViewById(R.id.btnCall);
      btnCall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Uri uri = Uri.parse("tel:" + phone.phone);
//          Intent intent = new Intent(Intent.ACTION_CALL, uri);
          Intent intent = new Intent(Intent.ACTION_DIAL, uri);
          v.getContext().startActivity(intent);
        }
      });
    }

    public void setPhoneNumber(String number) {
      String result = "";
      this.number.setText(number);
    }

    public void setId(String id) {
      this.id.setText(id);
    }

    public void setName(String name) {
      this.name.setText(name);
    }
  }

}
