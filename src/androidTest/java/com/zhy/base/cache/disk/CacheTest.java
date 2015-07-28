package com.zhy.base.cache.disk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.test.AndroidTestCase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by zhy on 15/7/28.
 */
public class CacheTest extends AndroidTestCase
{
    DiskLruCacheHelper helper;
    private static final String TAG = "CacheTest";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        Log.e(TAG, "setUp");
        helper = new DiskLruCacheHelper(getContext());
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        Log.e(TAG, "tearDown");
        helper.close();
    }

    public void testString() throws IOException
    {
        helper.put("testString", "张鸿洋");
        assertEquals("张鸿洋", helper.getAsString("testString"));
    }

    public void testGetStringWithoutVal() throws IOException
    {
        assertEquals(null, helper.getAsString("zhy------zzzzz"));
    }

    public void testJson()
    {
        try
        {
            JSONObject jsonObject = new JSONObject("{\"name\":\"zhy\"}");
            helper.put("testJson", jsonObject);
            assertEquals(jsonObject.toString(), helper.getAsJson("testJson").toString());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void testSerializable()
    {
        User u = new User();
        u.name = "张鸿洋";
        helper.put("testSerializable", u);
        User u2 = helper.getAsSerializable("testSerializable");
        assertEquals(u.name, u2.name);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void testBitmap()
    {
        Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_launcher);
        helper.put("testBitmap", bm);
        Bitmap bm2 = helper.getAsBitmap("testBitmap");

        assertEquals(bm.getByteCount(), bm2.getByteCount());
    }

    public void testDrawable()
    {
        Drawable d = getContext().getResources().getDrawable(R.mipmap.ic_launcher);
        helper.put("testDrawable", d);
        Drawable d2 = helper.getAsDrawable("testDrawable");
        assertNotNull(d2);
    }


    private static class User implements Serializable
    {
        String name;
    }




}
