package com.lego.pfsoft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lego.pfsoft.adapters.ColorAdapterRV;
import com.lego.pfsoft.model.Item;
import com.lego.pfsoft.utils.ColorXmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ColorAdapterRV.Callback{

    private List<Item> mItems;
    private ColorAdapterRV mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mItems = ColorXmlParser.getEventsFromXML(this);

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.color_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new ColorAdapterRV(this, mItems, this);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setHasFixedSize(true);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        mItems.get(position).toggle();
        mAdapter.notifyItemChanged(position);
    }
}
