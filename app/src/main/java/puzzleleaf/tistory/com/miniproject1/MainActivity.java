package puzzleleaf.tistory.com.miniproject1;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import puzzleleaf.tistory.com.miniproject1.adpater.BoardItem;
import puzzleleaf.tistory.com.miniproject1.object.BoardObject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BoardItem linearAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarInit();
        recyclerViewInit();
        swipeInit();
    }

    private void toolbarInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void recyclerViewInit() {
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearAdapter = new BoardItem(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(linearAdapter);
    }

    private void swipeInit() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrash();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //게시물 업데이트
    private void refrash() {
        MyData.obj.add(0, new BoardObject("새로 추가된 게시물 입니다."));
        Toast.makeText(this, "타임라인 갱신", Toast.LENGTH_SHORT).show();
        linearAdapter.notifyDataSetChanged();
    }

    public void myMessage(View v) {
        Toast.makeText(this, "미 구현 기능입니다.", Toast.LENGTH_SHORT).show();
    }
}
