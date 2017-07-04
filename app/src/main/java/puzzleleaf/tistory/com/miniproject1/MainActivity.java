package puzzleleaf.tistory.com.miniproject1;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import puzzleleaf.tistory.com.miniproject1.adpater.BoardItem;
import puzzleleaf.tistory.com.miniproject1.objects.BoardObject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BoardItem linearAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewInit();
        swipeInit();
    }

    private void recyclerViewInit()
    {
        myRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearAdapter = new BoardItem(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(linearAdapter);
    }

    private void swipeInit()
    {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrash();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    //게시물 초기화
    private void refrash()
    {
        myData.obj.clear();
        linearAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"초기화 되었습니다.",Toast.LENGTH_LONG).show();
    }

    public void Message(View v)
    {
        Toast.makeText(this, "미 구현 기능입니다.", Toast.LENGTH_SHORT).show();
    }
}