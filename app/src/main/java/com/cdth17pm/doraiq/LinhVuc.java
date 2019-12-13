package com.cdth17pm.doraiq;

import android.os.Parcel;
import android.os.Parcelable;

public class LinhVuc implements Parcelable {
    private String tenLinhVuc;
    private int id;

    protected LinhVuc(Parcel in) {
        setTenLinhVuc(in.readString());
    }

    public static final Creator<LinhVuc> CREATOR = new Creator<LinhVuc>() {
        @Override
        public LinhVuc createFromParcel(Parcel in) {
            return new LinhVuc(in);
        }

        @Override
        public LinhVuc[] newArray(int size) {
            return new LinhVuc[size];
        }
    };

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }
    public LinhVuc(){
    this.setTenLinhVuc("");
    }


    public LinhVuc(String tenLinhVuc){
     this.setTenLinhVuc(tenLinhVuc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTenLinhVuc());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public LinhVuc(String tenLinhVuc,int id)
    {
        this.tenLinhVuc=tenLinhVuc;
        this.id=id;
    }
}
