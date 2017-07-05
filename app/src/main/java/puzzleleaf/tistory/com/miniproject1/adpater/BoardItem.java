package puzzleleaf.tistory.com.miniproject1.adpater;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import puzzleleaf.tistory.com.miniproject1.R;
import puzzleleaf.tistory.com.miniproject1.myData;
import puzzleleaf.tistory.com.miniproject1.objects.BoardObject;
import puzzleleaf.tistory.com.miniproject1.objects.ReplyObject;

public class BoardItem extends RecyclerView.Adapter<BoardItem.ViewHolder> {

    private RecyclerView.Adapter adapter;
    private boolean headerFlag = false; // 헤더인지 아닌지 구분
    private LayoutInflater mInflater;

    //랜덤 이미지 출력을 위한 변수
    private int imgRes[] = {R.drawable.sample1,R.drawable.sample2,R.drawable.sample3,R.drawable.sample4,R.drawable.sample5,R.drawable.sample6,R.drawable.sample7};
    private Random random = new Random();


    public BoardItem(Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        adapter = this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if(viewType == 0) {
            headerFlag = true;
            view = mInflater.inflate(R.layout.activity_header_item, parent, false);
        }
        else {
            headerFlag = false;
            view = mInflater.inflate(R.layout.activity_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!holder.isHeader && myData.obj.size()>0)
            bodyBindInit(holder,position-1);
        else
            headerBindInit(holder);
    }

    //헤더 바인드
    private void headerBindInit(final ViewHolder holder)
    {
        //UI 업데이트
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.header.setVisibility(View.GONE);
                holder.headerItemWrite.setVisibility(View.VISIBLE);

            }
        });
        holder.headerItemWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.header.setVisibility(View.VISIBLE);
                holder.headerItemWrite.setVisibility(View.GONE);
            }
        });

        holder.boardItemCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myData.obj.add(0,new BoardObject(holder.boardItemContents.getText().toString()));
                holder.boardItemContents.setText("");
                holder.header.setVisibility(View.VISIBLE);
                holder.headerItemWrite.setVisibility(View.GONE);
                Toast.makeText(mInflater.getContext(),"게시물이 등록되었습니다.",Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();

         
            }
        });
    }

    //헤더가 아닌경우 게시물들을 바인드
    private void bodyBindInit(final ViewHolder holder, final int position)
    {
        replyAdapterInit(holder,position);
        holder.itemObject.setText(myData.obj.get(position).getContents());
        holder.leftImg.setImageResource(imgRes[random.nextInt(imgRes.length)]);
        holder.rightImg.setImageResource(imgRes[random.nextInt(imgRes.length)]);

        //댓글 입력시 등록 버튼이 보이도록 UI 업데이트
        holder.itemReply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                    holder.itemCommit.setImageResource(R.drawable.commit);
                else
                    holder.itemCommit.setImageResource(R.drawable.camera);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //댓글 추가
        holder.itemCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemReply.length()>0) {
                    myData.obj.get(position).getReplyObjects().add(new ReplyObject(holder.itemReply.getText().toString()));
                    holder.itemReply.setText("");
                    holder.linearAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(mInflater.getContext(),"이미지 등록은 지원하지 않습니다.",Toast.LENGTH_LONG).show();
            }
        });
    }

    //각 게시물에 댓글 정보를 저장하는 방식
    public void replyAdapterInit(ViewHolder holder, int position)
    {
        holder.linearAdapter = new ReplyItem(mInflater.getContext(),position);
        holder.commentRecyclerView.setAdapter(holder.linearAdapter);
    }


    //RecyclerView에서 게시물 순서 꼬이는 문제 해결을 위한 코드
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return myData.obj.size()+1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //ViewHolder 안의 변수로 체크 안하면 에러가 발생
        boolean isHeader = headerFlag;

        //게시물 내용
        private TextView itemObject; // 내용
        private ImageView leftImg;
        private ImageView rightImg;

        //댓글
        private EditText itemReply; //댓글 작성
        private ImageView itemCommit; // 댓글 등록 이미지 업데이트용
        private RecyclerView commentRecyclerView;
        private LinearLayoutManager linearLayoutManager;
        private ReplyItem linearAdapter;
        private ArrayList<ReplyObject> replyObj;

        //헤더
        private LinearLayout header;
        private LinearLayout headerItemWrite;
        private TextView boardItemCommit;
        private EditText boardItemContents;

        public ViewHolder(View itemView)
        {
            super(itemView);

            if(!isHeader)
                init(itemView);
            else
                headerInit(itemView);
        }

        //헤더 값 초기화
        private void headerInit(View itemView)
        {
            header = (LinearLayout)itemView.findViewById(R.id.board_header);
            headerItemWrite = (LinearLayout)itemView.findViewById(R.id.write_board_item);
            boardItemCommit = (TextView)itemView.findViewById(R.id.board_item_commit);
            boardItemContents = (EditText)itemView.findViewById(R.id.board_item_contents);
        }

        //게시글 값 초기화
        private void init(View itemView)
        {
            //게시물
            itemObject = (TextView)itemView.findViewById(R.id.item_contents);
            leftImg = (ImageView)itemView.findViewById(R.id.leftImg);
            rightImg = (ImageView)itemView.findViewById(R.id.rightImg);

            //댓글
            itemReply = (EditText)itemView.findViewById(R.id.item_reply);
            itemCommit= (ImageView)itemView.findViewById(R.id.item_commit);

            //댓글 RecyclerView
            commentRecyclerView = (RecyclerView)itemView.findViewById(R.id.comment_recyclerView);
            replyObj = new ArrayList<>();

            linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            commentRecyclerView.setLayoutManager(linearLayoutManager);
        }


    }
}