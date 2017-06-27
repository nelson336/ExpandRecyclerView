package com.example.expandrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.expandrecyclerview.adapter.SimpleExpandItemAdapter;
import com.example.expandrecyclerview.adapter.SimpleExpandableHeaderItem;
import com.example.expandrecyclerview.adapter.SimpleItemAdapter;
import com.example.expandrecyclerview.epdlib.adapters.FastItemAdapter;
import com.example.expandrecyclerview.model.ItemModel;
import com.example.expandrecyclerview.model.ItemSubModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemModel> mItems;
    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mItems = new ArrayList<>(Arrays.asList(new Gson().fromJson(Utils.loadJSONFromAsset(MainActivity.this, "mock.json"), ItemModel[].class)));
        setupAdapter();
    }

    private void setupAdapter() {
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FastItemAdapter adapter = new FastItemAdapter();
        rv.setAdapter(adapter);
        for (int i = 0; i < mItems.size(); i++) {
            ItemModel model = mItems.get(i);
            Log.e("Init", "" + i);
            if (model.getSubs() != null) {
                SimpleExpandableHeaderItem expandHeaderItem = new SimpleExpandableHeaderItem();
                expandHeaderItem.withHeader(model)
                        .withVisibleLineTop(i > 0)
                        .withVisibleLineBottom(i < (mItems.size() - 1))
                        .withExpand(true)
                        .withListener(new SimpleExpandableHeaderItem.listener() {
                            @Override
                            public void expandableListener(boolean expand) {
                                Log.e("Expand", String.valueOf(expand));
                            }
                        });

                List<SimpleExpandItemAdapter> items = new ArrayList<>();
                for (int ii = 0; ii < model.getSubs().size(); ii++) {
                    Log.e("II", "" + ii);
                    SimpleExpandItemAdapter item = new SimpleExpandItemAdapter();
                    item.withItem(model.getSubs().get(ii));
                    item.withListener(new SimpleExpandItemAdapter.Listener() {
                        @Override
                        public void clickButton(int pos, ItemSubModel item) {
                            Toast.makeText(MainActivity.this, "Teste", Toast.LENGTH_SHORT).show();
                        }
                    });
                    items.add(item);
                }
                expandHeaderItem.withSubItems(items);
                adapter.add(expandHeaderItem);
            }else {
                SimpleItemAdapter itemAdapter = new SimpleItemAdapter();
                itemAdapter.withItem(model);
                adapter.add(itemAdapter);
            }
        }


    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rvSimpleExpand);
    }
}
