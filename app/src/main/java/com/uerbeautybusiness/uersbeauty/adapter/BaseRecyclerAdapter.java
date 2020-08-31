package com.uerbeautybusiness.uersbeauty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uerbeautybusiness.uersbeauty.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 16/9/25.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected final List<T> mData;
    protected final Context mContext;
    protected LayoutInflater mInflater;
    private boolean isLoadMore = false;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;
    private View footer;
    private static final int TYPE_FOOTER = 11;

    public BaseRecyclerAdapter(Context ctx, List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_FOOTER) {
            //返回加载更多的布局的holder

            footer = LayoutInflater.from(mContext).inflate(R.layout.footer, parent, false);
            return new RecyclerViewHolder(mContext, footer);
        } else {
            view = LayoutInflater.from(mContext).inflate(getItemLayoutId(viewType), parent, false);
            return new RecyclerViewHolder(mContext, view);
        }
    }

    public void setNoDataFooterView() {
        if (footer != null) {
            hideFooter();
//            ToastUtil.showLong(x.app(), "没有更多数据了");
        }
    }

    public void hideFooter() {
        TextView textView = (TextView) footer.findViewById(R.id.loading_text);
        ProgressBar progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        if (position < mData.size()) {
            bindData(holder, position, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (isLoadMore && mData.size() >= 10) {
            return mData.size() == 0 ? 0 : mData.size() + 1;
        } else {
            return mData.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadMore && mData.size() >= 10) {
            if ((position + 1) == getItemCount()) {
                return TYPE_FOOTER;
            }
        }
        return getItemType(position);
    }

    public void setLoadMore(boolean flag) {
        isLoadMore = flag;
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public int getItemType(int position);

    abstract public void bindData(RecyclerViewHolder holder, int position, T item);
    private boolean mIsLoadHead = false;
    private int mHeadNum = 1;
    public void setLoadHead(boolean isLoadHead, int headNum, T bean) {
        mIsLoadHead = isLoadHead;
        mHeadNum = headNum;
        if (isLoadHead) {
            for (int i = 0; i < headNum; i++) {
                T t = bean;
                mData.add(0, t);
            }
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View itemView, int pos);
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(View itemView, int pos);
    }
}