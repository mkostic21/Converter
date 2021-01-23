package hr.ferit.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements ButtonClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private hr.ferit.converter.PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupPager();
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tab);

        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupPager() {
        pagerAdapter = new hr.ferit.converter.PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onButtonClick(String conversionType) {
        //TODO click method

        pagerAdapter.setConversionType(conversionType);
        viewPager.setCurrentItem(1); //switch to input fragment
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position, String data) {
        pagerAdapter.setHistoryData(data);
        viewPager.setCurrentItem(1); //return to input screen
        pagerAdapter.notifyDataSetChanged();
    }

}