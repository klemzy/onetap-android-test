package onetap.test.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class OneTapActivity extends AppCompatActivity {

    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.pager) ViewPager viewPager;
    @Bind(R.id.page_indicator) CirclePageIndicator pageIndicator;
    @Bind(R.id.content_list) RecyclerView contentList;

    @BindColor(R.color.white) int white;
    @BindColor(R.color.black) int black;
    @BindColor(R.color.white_77) int white_77;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tap);
        ButterKnife.bind(this);

        contentList.setLayoutManager(new LinearLayoutManager(this));
        contentList.addItemDecoration(new ContentAdapter.DividerDecoration(this));
        final ContentAdapter contentAdapter = new ContentAdapter(getResources().getStringArray(R.array.content_one));
        contentList.setAdapter(contentAdapter);

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

    }
}
