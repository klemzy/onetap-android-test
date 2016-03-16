package onetap.test.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] content;

    private int headerHeight = 0;
    private int headerWidth = 0;

    public ContentAdapter(String[] content) {
        this.content = content;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
            return new ContentHolder(view);
        } else {
            View view = new View(parent.getContext());
            view.setLayoutParams(new RecyclerView.LayoutParams(headerWidth, headerHeight));
            return new HeaderHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ContentHolder) {
            ContentHolder contentHolder = (ContentHolder) holder;
            contentHolder.contentView.setText(content[position - 1]);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return content.length + 1;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setHeaderWidth(int headerWidth) {
        this.headerWidth = headerWidth;
    }

    public void updateContent(String[] content) {
        this.content = content;
        notifyDataSetChanged();
    }

    public class ContentHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title) TextView contentView;

        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    public static class DividerDecoration extends RecyclerView.ItemDecoration {

        private Drawable divider;

        public DividerDecoration(Context context) {
            super();
            divider = ContextCompat.getDrawable(context, R.drawable.divider);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
