package onetap.test.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class OneTapActivity extends AppCompatActivity {

    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.pager) ViewPager viewPager;
    @Bind(R.id.page_indicator) CirclePageIndicator pageIndicator;
    @Bind(R.id.content_list) RecyclerView contentList;
    @Bind(R.id.pager_container) SquareFrameLayout pagerContainer;
    @Bind(R.id.container) View container;

    @BindColor(R.color.white) int white;
    @BindColor(R.color.black) int black;
    @BindColor(R.color.white_77) int white_77;

    private int sumY = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tap);
        ButterKnife.bind(this);

        contentList.setLayoutManager(new LinearLayoutManager(this));
        contentList.addItemDecoration(new ContentAdapter.DividerDecoration(this));
        final ContentAdapter contentAdapter = new ContentAdapter(getResources().getStringArray(R.array.content_one));

        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText(R.string.tab_one);
        tab1.setTag(R.array.content_one);
        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText(R.string.tab_two);
        tab2.setTag(R.array.content_two);
        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setText(R.string.tab_three);
        tab3.setTag(R.array.content_three);

        tabLayout.setTabTextColors(black, white);
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                contentAdapter.updateContent(getResources().getStringArray(tag));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        OneTapPagerAdapter adapter = new OneTapPagerAdapter();
        viewPager.setAdapter(adapter);
        pageIndicator.setFillColor(white);
        pageIndicator.setPageColor(white_77);
        pageIndicator.setStrokeWidth(0.0f);
        pageIndicator.setViewPager(viewPager);

        contentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                if (dy > 0) {
                    //Scroll upwards
                    //Scroll tab to the top
                    float translation = Math.max(tabLayout.getTranslationY() - dy, 0.0f);
                    tabLayout.setTranslationY(translation);
                } else if (dy < 0) {
                    //Scroll downwards
                    if (sumY < pagerContainer.getHeight()) {
                        //If scroll amount is less than pager view height, scroll tab to its original position
                        float translation = Math.min(pagerContainer.getHeight(), tabLayout.getTranslationY() + Math.abs(dy));
                        tabLayout.setTranslationY(translation);
                    }
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                container.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                contentAdapter.setHeaderWidth(pagerContainer.getWidth());
                contentAdapter.setHeaderHeight(pagerContainer.getHeight() + tabLayout.getHeight());
                contentList.setAdapter(contentAdapter);

                tabLayout.setTranslationY(pagerContainer.getHeight());
            }
        });

        contentList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);
                if (position == 0) {
                    //Touch happened in first item in recycler view
                    //DOWN motion event is not sent in onTouchEvent so send it here
                    viewPager.dispatchTouchEvent(e);
                }
                return position == 0;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                viewPager.dispatchTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
