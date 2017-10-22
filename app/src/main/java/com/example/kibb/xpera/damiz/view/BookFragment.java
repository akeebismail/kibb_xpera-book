package com.example.kibb.xpera.damiz.view;

/**
 * Created by KIBB on 8/30/2017.
 */
import  com.example.kibb.xpera.R;
import com.example.kibb.xpera.damiz.adapter.BookAdapter;
import com.example.kibb.xpera.damiz.bean.Book;
import com.example.kibb.xpera.damiz.biz.BookBiz;
import com.example.kibb.xpera.damiz.supplier.BookSupplier;
import com.google.android.agera.BaseObservable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookFragment extends Fragment implements Updatable, AdapterView.OnItemClickListener {
    private FloatingActionButton mFabSearch;
    private ListView mLvBooks;
    private SwipeRefreshLayout mSrlRefresh;

    private Repository<Result<List<Book>>> mRepository;
    private ExecutorService mExecutor;
    private SearchObserver mObserver;
    private BookSupplier mSupplier;

    private BookAdapter mAdapter;
    private List<Book> mBooks = new ArrayList<>();

    private BookBiz mBookBIZ = new BookBiz();

    public static final String SEND_BOOK = "BOOK";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, null, false);
        mFabSearch = (FloatingActionButton) view.findViewById(R.id.id_fab_search);
        mLvBooks = (ListView) view.findViewById(R.id.id_lv_books);
        mSrlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_srl_refresh);
        mAdapter = new BookAdapter(getActivity(), mBooks);
        mLvBooks.setAdapter(mAdapter);
        mLvBooks.setOnItemClickListener(this);
        setupRefresh();
        setupSearch();
        setupRepository();
        return view;
    }

    /**
     *
     */
    private void setupRefresh() {
        mSrlRefresh.setColorSchemeColors(R.color.colorPrimary);
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mObserver.doSearch(mBookBIZ.switchKeyWord());
            }
        });
    }


    private void setupRepository() {
        mExecutor = Executors.newSingleThreadExecutor();
        mObserver = new SearchObserver();
        mSupplier = new BookSupplier(mBookBIZ.switchKeyWord());

        mRepository = Repositories
                .repositoryWithInitialValue(Result.<List<Book>>absent())
                .observe(mObserver)
                .onUpdatesPerLoop()
                .goTo(mExecutor)
                .thenGetFrom(mSupplier)
                .compile();

    }

    @Override
    public void onResume() {
        super.onResume();
        mRepository.addUpdatable(this);
        Log.v("LOG", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mRepository.removeUpdatable(this);
    }

    /**
     *
     */
    private void setupSearch() {
        mFabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View dialogView = inflater.inflate(R.layout.dialog_search_book, null, false);
                final EditText etKey = (EditText) dialogView.findViewById(R.id.id_et_dialog_key);
                final Button btnSearch = (Button) dialogView.findViewById(R.id.id_btn_dialog_search);
                //
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(etKey.getText())) {
//                            Log.v("LOG", "onclick");
                            doInSearch(etKey.getText().toString());
                        }
                        //
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }


    private void doInSearch(String key) {
//        Log.v("LOG", "Fragment doInSearch");
        mObserver.doSearch(key);
    }



    @Override
    public void update() {
//        Log.v("LOG", "Fragment update");
        if (mRepository.get().isPresent()) {
            if (mSrlRefresh.isRefreshing()) {
                mSrlRefresh.setRefreshing(false);
            }
            mBooks.clear();
            mBooks.addAll(mRepository.get().get());
//            Log.v("LOG", mBooks.toString());
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra(SEND_BOOK, mBooks.get(position));
        startActivity(intent);
    }

    /**
     * 从数据池中获取数据
     */
    private class SearchObserver extends BaseObservable {
        // 进行查找操作
        public void doSearch(String key) {
//            Log.v("LOG", "Observer doSearch");
            mSupplier.setKey(key);
            dispatchUpdate();
        }
    }

}
