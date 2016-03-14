package onetap.test.app;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class OneTapPagerAdapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.page, container, false);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.thumbnail);

        Bitmap bitmap = ImageUtils.getSquareBitmap(container.getResources(), position == 0 ? R.drawable.cat_one : R.drawable.cat_two);
        RoundedBitmapDrawable drawable = ImageUtils.getRoundedBitmapDrawable(container.getResources(), bitmap);

        imageView.setImageDrawable(drawable);
        container.addView(frameLayout);
        return frameLayout;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
