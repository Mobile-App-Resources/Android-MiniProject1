package puzzleleaf.tistory.com.miniproject1.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import puzzleleaf.tistory.com.miniproject1.R;
import puzzleleaf.tistory.com.miniproject1.MyData;
import puzzleleaf.tistory.com.miniproject1.object.ReplyObject;

public class ReplyItem extends RecyclerView.Adapter<ReplyItem.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<ReplyObject> obj;

    public ReplyItem(Context context, int position) {
        this.mInflater = LayoutInflater.from(context);
        this.obj = MyData.obj.get(position).getReplyObjects();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_reply_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(!obj.isEmpty())
             holder.replyItem.setText(obj.get(position).getContents().toString());
    }


    @Override
    public int getItemCount() {
        return obj.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView replyItem;

        public ViewHolder(View itemView) {
            super(itemView);
            replyItem = (TextView)itemView.findViewById(R.id.reply_contents);

        }


    }
}