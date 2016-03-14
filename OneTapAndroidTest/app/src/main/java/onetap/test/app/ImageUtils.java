package onetap.test.app;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

public class ImageUtils {
    private ImageUtils() {

    }

    @NonNull
    public static RoundedBitmapDrawable getRoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(resources, bitmap);
        drawable.setAntiAlias(true);
        drawable.setCircular(true);
        return drawable;
    }

    public static Bitmap getSquareBitmap(Resources resources, @DrawableRes int drawableResId) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, drawableResId);
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int x = bitmap.getWidth() / 2 - size / 2;
        int y = bitmap.getHeight() / 2 - size / 2;
        bitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
        return bitmap;
    }

}
