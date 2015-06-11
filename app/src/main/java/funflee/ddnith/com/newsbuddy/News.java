package funflee.ddnith.com.newsbuddy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DEEPAK on 6/11/2015.
 */
public class News {

    private int newsItemId;
    private String headLine;
    private String agency;
    private Date dateLine;
    private String byLine;
    private String photoUrl;
    private String thumbUrl;
    private String caption;

    public int getNewsItemId() {
        return newsItemId;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getAgency() {
        return agency;
    }

    public String getDateLine() {
        // not able to complete due to time constraint
//        Date currentDate = new Date();
//
//        long diff = currentDate.getTime() - dateLine.getTime();
//
//        long diffSeconds = diff / 1000 % 60;
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//
//        if(diffHours >= 1)
//          return " ";
//        else
//          return diffMinutes + " minutes ago";
        return "";
    }

    public String getByLine() {
        return byLine;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getCaption() {
        return caption;
    }

    public static News fromJson(JSONObject jsonObject){
        News b = new News();
        try{
            b.newsItemId = jsonObject.getInt("NewsItemId");
            b.headLine = jsonObject.getString("HeadLine");
            b.agency = jsonObject.getString("Agency");
            // not able to complete due to time constraint
//            if(jsonObject.has("DateLine")){
//              //b.dateLine = jsonObject.getString("dateLine");
//                String date = jsonObject.getString("dateLine");
//                DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
//                try{
//                    b.dateLine = df.parse(date);
//                }
//                catch (ParseException ex){
//                   Log.e("NewsBuddy", "ParseException" + ex);
//                }
//
//            }
            if(jsonObject.has("ByLine"))
              b.byLine = jsonObject.getString("ByLine");
            if(jsonObject.has("Image")){
                JSONObject imageJson = jsonObject.getJSONObject("Image");
                if(imageJson.has("Photo"))
                    b.photoUrl = jsonObject.getJSONObject("Image").getString("Photo");
                if(imageJson.has("Thumb"))
                    b.thumbUrl = jsonObject.getJSONObject("Image").getString("Thumb");
                if(imageJson.has("PhotoCaption"))
                    b.caption = jsonObject.getJSONObject("Image").getString("PhotoCaption");
            }
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return  b;
    }

    public static ArrayList<News> fromJson(JSONArray jsonArray){

        ArrayList<News> newsList = new ArrayList<News>(jsonArray.length());

        for(int i=0;i<jsonArray.length();i++){
            JSONObject newsJson = null;
            try {
                newsJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            News news = News.fromJson(newsJson);
            if (news != null) {
                newsList.add(news);
            }
        }

        return newsList;
    }

}
