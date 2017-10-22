package com.example.kibb.xpera.damiz.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kibb.xpera.R;
import com.example.kibb.xpera.damiz.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KIBB on 8/30/2017.
 */

public class BookDetailActivity extends AppCompatActivity {
    private CollapsingToolbarLayout mCtlLayout;
    private ViewPager mVpContent;
    private TabLayout mTvTabs;
    private Toolbar mToolbar;
    private ImageView mImage;

    private String[] mTitles= new String[] {"abstract","about author","table of content"};

    private List<Fragment> dFragment= new ArrayList<>();
    private Book dBook;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initData();
        initView();
    }
    private void initData(){
        dBook = (Book) getIntent().getSerializableExtra(BookFragment.SEND_BOOK);
        dFragment.add(BookDetailFragment.getInstance(dBook.getSummary()));
        dFragment.add(BookDetailFragment.getInstance(dBook.getAuthor_intro()));
        dFragment.add(BookDetailFragment.getInstance(dBook.getCatalog()));
    }
    private void initView(){
        mImage= (ImageView) findViewById(R.id.id_iv_detail_img);
        mCtlLayout= (CollapsingToolbarLayout) findViewById(R.id.id_ct_detail_coll);
        mVpContent= (ViewPager) findViewById(R.id.id_vp_detail_pager);
        mTvTabs= (TabLayout) findViewById(R.id.id_tl_detail_tab);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar_detail);
        mCtlLayout.setTitle(dBook.getTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        Glide.with(this).load(dBook.getImages().getLarge())
                .fitCenter().into(mImage);
         mVpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
             @Override
             public Fragment getItem(int position) {
                 return dFragment.get(position);
             }

             @Override
             public int getCount() {
                 return mTitles.length;
             }
             @Override
             public CharSequence getPageTitle(int pos){
                 return mTitles[pos];
             }
         });
        mTvTabs.setupWithViewPager(mVpContent);
    }
}
