package funflee.ddnith.com.newsbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DEEPAK on 11/06/2015.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> newsList){
        super(context,0,newsList);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        News news = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_news,parent,false);
        }
        TextView tvHeadLine = (TextView) convertView.findViewById(R.id.tvHeadline);
        TextView tvByLine = (TextView) convertView.findViewById(R.id.tvByline);
        TextView tvDateLine = (TextView)convertView.findViewById(R.id.tvDateLine);
        ImageView ivPosterImage = (ImageView)convertView.findViewById(R.id.ivPosterImage);

        tvHeadLine.setText(news.getHeadLine());
        tvByLine.setText(news.getByLine());
        tvDateLine.setText(news.getDateLine());
        Picasso.with(getContext()).load(news.getThumbUrl()).into(ivPosterImage);
       return  convertView;
    }

}
