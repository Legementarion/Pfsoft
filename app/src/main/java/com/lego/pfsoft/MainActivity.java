package com.lego.pfsoft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lego.pfsoft.adapters.ColorAdapterRV;
import com.lego.pfsoft.model.Item;
import com.lego.pfsoft.utils.ColorXmlParser;
import android.provider.Settings.Secure;

import java.util.List;

import ly.count.android.sdk.Countly;
import ly.count.android.sdk.DeviceId;

public class MainActivity extends AppCompatActivity implements ColorAdapterRV.Callback {

    private List<Item> mItems;
    private ColorAdapterRV mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnalytics();
        init();
    }

    private void initAnalytics() {
        Countly.sharedInstance().init(this, "https://try.count.ly", "7851abb832411ac40d93cff97bf4e1766c7e8e5a", null, DeviceId.Type.OPEN_UDID);
        Countly.sharedInstance().setViewTracking(true);
        Countly.sharedInstance().enableCrashReporting();
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.color_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mItems = ColorXmlParser.getEventsFromXML(this);
        mAdapter = new ColorAdapterRV(this, mItems, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(int position) {
        mItems.get(position).toggle();
        mAdapter.notifyItemChanged(position);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Countly.sharedInstance().onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Countly.sharedInstance().onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
