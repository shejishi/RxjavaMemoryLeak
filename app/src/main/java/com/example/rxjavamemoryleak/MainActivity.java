package com.example.rxjavamemoryleak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ObservableEmitter<String> mEmitter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView recyclerView;
    private CurrentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrentListAdapter(null);
        recyclerView.setAdapter(adapter);

        // 在oncreate() 方法中创建方法
        compositeDisposable.add(
                Observable
                        .create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                if (!emitter.isDisposed()) {
                                    mEmitter = emitter;
                                }
                            }
                        })
                        .buffer(1_000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> strings) throws Exception {
                                // TODO
                                Log.d(TAG, "接受数据");
                                adapter.addDatas(strings);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );


    }

    int index = 0;

    /**
     * 添加数据
     *
     * @param view
     */
    public void addData(View view) {
        for (int i = index; i < index + 5; i++) {
            mEmitter.onNext(i + "");
            Log.d(TAG, "发送数据");
        }
        index += 5;
    }
}
