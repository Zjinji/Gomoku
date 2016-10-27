package com.z.gomoku;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AIPosition implements Comparable<AIPosition>{
    public float level;
    public int position = -1;

    @Override
    public int compareTo(AIPosition aiPosition) {
        if(this.level > aiPosition.level){
            return 1;
        }else if(this.level == aiPosition.level){
            //level优先级和position落子点全部一致，则这个位置重复
            if(this.position == aiPosition.position){
                return 0;
            }else if(this.position > aiPosition.position){
                return 1;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AIPosition that = (AIPosition) o;

        if (Float.compare(that.level, level) != 0) return false;
        return position == that.position;

    }

    @Override
    public int hashCode() {
        int result = (level != +0.0f ? Float.floatToIntBits(level) : 0);
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        String result = "[Level:" + level + ",Position:" + position + "]";
        return result;
    }
}
