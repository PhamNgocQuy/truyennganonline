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

public class SQliteFavorite extends SQLiteDataController {

    public SQliteFavorite(Context con) {
        super(con);
    }

    public ArrayList<Profile> getListStory() {
        ArrayList<Profile> lsData1, lsData2;
        lsData1 = new ArrayList<>();
        lsData2 = new ArrayList<>();


        try {
            openDataBase();
            Cursor cs = database.rawQuery("select id,imgurl,contenturl,title,theloai,favorite,content " +
                    "from storyfavorite", null);
            while (cs.moveToNext()) {

                int id = cs.getInt(0);
                String imgurl = cs.getString(1);
                String contenturl = cs.getString(2);
                String title = cs.getString(3);
                String theloai = cs.getString(4);

                Boolean favorite = (cs.getInt(5) == 1);

                String content = cs.getString(6);


                Profile profile = new Profile();

                profile.setIdf(id);
                profile.setTheloai(theloai);
                profile.setTitle(title);
                profile.setUrl(contenturl);
                profile.setUrlImg(imgurl);
                profile.setFavoite(favorite);
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
            values.put("favorite",profile.isFavoite());
            values.put("content",profile.getContent());

            long id = database.insert("storyfavorite", null, values);
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
            values.put("favorite",profile.isFavoite());
            values.put("content",profile.getContent());

            long id = database.update("storyfavorite", values, "id=" + profile.getIdf(), null);
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
            int rs_ = database.delete("storyfavorite", "id=" + id, null);
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
