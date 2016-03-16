package onetap.test.app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class OneTapRecyclerView extends RecyclerView {

    private boolean isTouched = false;

    public OneTapRecyclerView(Context context) {
        this(context, null);
    }

    public OneTapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public OneTapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            isTouched = true;
        } else if (e.getActionMasked() == MotionEvent.ACTION_CANCEL ||
                e.getActionMasked() == MotionEvent.ACTION_UP) {
            isTouched = false;
        }
        return super.onTouchEvent(e);
    }

    public boolean isTouched() {
        return isTouched;
    }
}
