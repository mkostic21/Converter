package hr.ferit.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements ButtonClickListener{

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
        pagerAdapter.setConversionType(conversionType);
        viewPager.setCurrentItem(1); //switch to input fragment
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConvertClick(String input) {
        pagerAdapter.addHistoryData(input);
    }

    @Override
    public void onClearClick() {
        pagerAdapter.clearHistoryData();
    }

}