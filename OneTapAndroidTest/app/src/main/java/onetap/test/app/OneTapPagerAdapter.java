package onetap.test.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
        Bitmap bitmap = getSquareBitmap(container, position);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(container.getResources(), bitmap);
        drawable.setAntiAlias(true);
        drawable.setCircular(true);
        imageView.setImageDrawable(drawable);
        container.addView(frameLayout);
        return frameLayout;
    }

    private Bitmap getSquareBitmap(ViewGroup container, int position) {
        Bitmap bitmap = BitmapFactory.decodeResource(container.getResources(), position == 0 ? R.drawable.cat_one : R.drawable.cat_two);
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int x = bitmap.getWidth() / 2 - size / 2;
        int y = bitmap.getHeight() / 2 - size / 2;
        bitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
        return bitmap;
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
