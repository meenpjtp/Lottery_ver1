package com.test.testsrc4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Srh Dp on 30-Jun-17.
 */

public class LotteryAdapter extends RecyclerView.Adapter<LotteryAdapter.ViewHolder> {

    public List<LotteryModel> data;

    public LotteryAdapter(){}
    public void setData(List<LotteryModel> data){
        this.data=data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View wordView=inflater.inflate(R.layout.lottery_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(wordView,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       LotteryModel lotteryModel =data.get(position);
        holder.lotteryText.setText(lotteryModel.getLotteryNo());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public TextView lotteryText;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context=context;
            lotteryText=(TextView) itemView.findViewById(R.id.lotterytext);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    LotteryModel lotteryModel = data.get(position);

                    Intent intent = new Intent(context,DescriptionActivity.class);
                    intent.putExtra("LOTTERYNO",lotteryModel.getLotteryNo());
                    intent.putExtra("DESCRIPTION",lotteryModel.getDescription());
                    context.startActivity(intent);
                }
            });
        }
    }
}
