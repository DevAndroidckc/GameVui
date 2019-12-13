package com.cdth17pm.doraiq;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    private int id;

    public CauHoiLoader(@NonNull Context context, int idLinhVuc) {
        super(context);
        id=idLinhVuc;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hoi?linh_vuc="+id,"GET");
    }
}
