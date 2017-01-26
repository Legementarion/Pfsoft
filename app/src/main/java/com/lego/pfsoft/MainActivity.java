package com.lego.pfsoft;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lego.pfsoft.adapters.ColorAdapterRV;
import com.lego.pfsoft.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    List<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getEventsFromXML(this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView = (RecyclerView) findViewById(R.id.color_rv);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(new ColorAdapterRV(mItems));
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getEventsFromXML(Activity activity) throws XmlPullParserException, IOException {
        XmlResourceParser xmlResourceParser = activity.getResources().getXml(R.xml.colors);
        xmlResourceParser.next();
        int eventType = xmlResourceParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && "color".equals(xmlResourceParser.getName())) {
                mItems.add(new Item(xmlResourceParser.getAttributeValue(0), xmlResourceParser.getAttributeValue(1)));
            }
            eventType = xmlResourceParser.next();
        }
    }

}
