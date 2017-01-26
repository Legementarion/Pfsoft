package com.lego.pfsoft.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.lego.pfsoft.R;
import com.lego.pfsoft.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColorXmlParser {

    private static final String COLOR = "color";

    private ColorXmlParser() {
    }

    @NonNull
    public static List<Item> getEventsFromXML(Context context) {
        List<Item> mItems = new ArrayList<>();
        try {
            XmlResourceParser xmlResourceParser = context.getResources().getXml(R.xml.colors);
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && COLOR.equals(xmlResourceParser.getName())) {
                    mItems.add(new Item(xmlResourceParser.getAttributeValue(0), Color.parseColor(xmlResourceParser.getAttributeValue(1))));
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return mItems;
    }
}
