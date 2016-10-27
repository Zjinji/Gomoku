package com.z.gomoku;

import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class GomokuAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<Chess> list;

    public GomokuAdapter(LayoutInflater layoutInflater, List<Chess> list) {
        this.layoutInflater = layoutInflater;
        this.list = list;
    }

    public void initData(List<Chess> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Chess getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            viewHolder.boardImageView = (ImageView) convertView.findViewById(R.id.boardImageView);
            viewHolder.chessImageView = (ImageView) convertView.findViewById(R.id.chessImageView);

            int[] xy = postion2XY(position);
            //棋盘4角
            if(xy[0] == 0 && xy[1] == 0){
                //左上角
                viewHolder.boardImageView.setImageResource(R.drawable.top_left);
            }

            if(xy[0] == 0 && xy[1] == 13){
                //右上角
                viewHolder.boardImageView.setImageResource(R.drawable.top_right);
            }

            if(xy[0] == 15 && xy[1] == 0){
                //左下角
                viewHolder.boardImageView.setImageResource(R.drawable.bottom_left);
            }

            if(xy[0] == 15 && xy[1] == 13){
                //右下角
                viewHolder.boardImageView.setImageResource(R.drawable.bottom_right);
            }

            if(xy[0] == 0 && xy[1] != 0 && xy[1] != 13){
                //上边缘
                viewHolder.boardImageView.setImageResource(R.drawable.top);
            }

            if(xy[0] == 15 && xy[1] != 0 && xy[1] != 13){
                //下边缘
                viewHolder.boardImageView.setImageResource(R.drawable.bottom);
            }

            if(xy[1] == 0 && xy[0] != 0 && xy[0] != 15){
                //左边缘
                viewHolder.boardImageView.setImageResource(R.drawable.left);
            }

            if(xy[1] == 13 && xy[0] != 0 && xy[0] != 15){
                //右边缘
                viewHolder.boardImageView.setImageResource(R.drawable.right);
            }

            if(xy[0] > 0 && xy[0] < 15 && xy[1] > 0 && xy[1] < 13){
                viewHolder.boardImageView.setImageResource(R.drawable.line);
            }

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Chess chess = list.get(position);

        switch (chess.who){
            case 0:
                viewHolder.chessImageView.setVisibility(View.INVISIBLE);
                break;
            case 1:
                viewHolder.chessImageView.setVisibility(View.VISIBLE);
                viewHolder.chessImageView.setImageResource(list.get(position).CHESS_BLACK);
                break;
            case 2:
                viewHolder.chessImageView.setVisibility(View.VISIBLE);
                viewHolder.chessImageView.setImageResource(list.get(position).CHESS_WHITE);
                break;
        }

        return convertView;
    }

    class ViewHolder{
        ImageView boardImageView;
        ImageView chessImageView;
    }

    /**
     * 将position转化为二维坐标
     * @param postion
     * @return
     */
    public int[] postion2XY(int postion){
        int[] xy= new int[2];
        xy[0] = postion / 14;
        xy[1] = postion % 14;
        return xy;
    }
}
