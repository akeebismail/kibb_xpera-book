package com.example.kibb.xpera.damiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KIBB on 8/30/2017.
 */
import com.bumptech.glide.Glide;
import com.example.kibb.xpera.R;
import com.example.kibb.xpera.damiz.bean.Book;
import com.example.kibb.xpera.damiz.util.FormatUtil;

import java.util.List;


public class BookAdapter extends BaseAdapter {
    private List<Book> dBooks;
    private Context dContext;

    public BookAdapter(Context context, List<Book> books){
        this.dContext= context;
        this.dBooks= books;
    }
    @Override
    public int getCount(){
        return dBooks.size();
    }
    @Override
    public Object getItem(int pos){
        return dBooks.get(pos);
    }
    @Override
    public long getItemId(int pos){
        return pos;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            holder= new ViewHolder();
            LayoutInflater inflater= LayoutInflater.from(dContext);
            convertView= inflater.inflate(R.layout.item_each_book,null,false);
            holder.mtxTitle= (TextView) convertView.findViewById(R.id.id_iv_book_title);
            holder.mtxSubtitle= (TextView) convertView.findViewById(R.id.id_tv_book_subtitle);
            holder.mtxPages = (TextView) convertView.findViewById(R.id.id_tv_book_pages);
            holder.mtxPubdate = (TextView) convertView.findViewById(R.id.id_tv_book_publish_date);
            holder.mtxPrices = (TextView) convertView.findViewById(R.id.id_tv_book_prize);
            holder.mtxImg = (ImageView) convertView.findViewById(R.id.id_iv_book_img);
            convertView.setTag(holder);


        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mtxTitle.setText(FormatUtil.subText(dBooks.get(pos).getTitle(),20));
        holder.mtxSubtitle.setText("SubTile : "+FormatUtil.subText(dBooks.get(pos).getAlt_title(),10));
        holder.mtxPrices.setText("Book Price :  "+ dBooks.get(pos).getPrice());
        holder.mtxPages.setText("Pages :  " + dBooks.get(pos));
        holder.mtxPubdate.setText("Publish On : " + dBooks.get(pos).getPubdate());
        Glide.with(dContext).load(dBooks.get(pos).getImage()).fitCenter()
                .into(holder.mtxImg);
        return convertView;
    }

    private static class ViewHolder{
        private TextView mtxTitle;
        private TextView mtxSubtitle;
        private TextView mtxPubdate;
        private TextView mtxPages;
        private TextView mtxPrices;
        private ImageView mtxImg;
    }
}
