package com.SQliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.AdapterStoryExpand.Profile;

import java.util.ArrayList;

/**
 * Created by QuyPN on 3/29/2017.
 */

public class SQliteHistory extends SQLiteDataController {

    public SQliteHistory(Context con) {
        super(con);
    }

    public ArrayList<Profile> getListStory() {
        ArrayList<Profile> lsData1, lsData2;
        lsData1 = new ArrayList<>();
        lsData2 = new ArrayList<>();


        try {
            openDataBase();
            Cursor cs = database.rawQuery("select id,imgurl,contenturl,title,theloai,content " +
                    "from story", null);
            while (cs.moveToNext()) {
                int id = cs.getInt(0);
                String imgurl = cs.getString(1);
                String contenturl = cs.getString(2);
                String title = cs.getString(3);
                String theloai = cs.getString(4);
                String content = cs.getString(5);

                Profile profile = new Profile();
                profile.setId(id);
                profile.setTheloai(theloai);
                profile.setTitle(title);
                profile.setUrl(contenturl);
                profile.setUrlImg(imgurl);
                profile.setContent(content);

                lsData1.add(profile);
            }
            for (int i = lsData1.size() - 1; i >= 0; i--) {
                lsData2.add(lsData1.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lsData2;
    }

    public boolean insertStory(Profile profile) {
        boolean rs = false;

        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("imgurl", profile.getUrlImg());
            values.put("contenturl", profile.getUrl());
            values.put("title", profile.getTitle());
            values.put("theloai", profile.getTheloai());
            values.put("content",profile.getContent());

            long id = database.insert("story", null, values);
            if (id > 0) {
                rs = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rs;
    }

    public boolean updateStory(Profile profile) {
        boolean rs = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("imgurl", profile.getUrlImg());
            values.put("contenturl", profile.getUrl());
            values.put("title", profile.getTitle());
            values.put("theloai", profile.getTheloai());
            values.put("content",profile.getContent());

            long id = database.update("story", values, "id=" + profile.getId(), null);
            if (id > 0) {
                rs = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rs;
    }

    public boolean deleteStory(int id) {
        boolean rs = false;
        try {

            openDataBase();
            int rs_ = database.delete("story", "id=" + id, null);
            if (rs_ > 0) {
                rs = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rs;
    }
}
