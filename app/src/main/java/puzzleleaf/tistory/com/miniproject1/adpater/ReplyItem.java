package puzzleleaf.tistory.com.miniproject1.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import puzzleleaf.tistory.com.miniproject1.R;
import puzzleleaf.tistory.com.miniproject1.objects.RepleObject;

public class RepleItem extends RecyclerView.Adapter<RepleItem.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<RepleObject> obj;

    public RepleItem(Context context, ArrayList<RepleObject> obj)
    {
        this.mInflater = LayoutInflater.from(context);
        this.obj = obj;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_reple_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(!obj.isEmpty())
             holder.repleItem.setText(obj.get(position).getContents().toString());
    }


    @Override
    public int getItemCount() {
        return obj.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView repleItem;

        public ViewHolder(View itemView)
        {
            super(itemView);
            repleItem = (TextView)itemView.findViewById(R.id.reple_contents);

        }


    }
}