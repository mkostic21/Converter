package hr.ferit.converter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;
    private String conversionType = ""; //default
    private ArrayList<String> historyData = new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return InputFragment.newInstance(conversionType);
            case 2:
                return HistoryFragment.newInstance(historyData);
            default:
                return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public void setConversionType(String conversionType) {
        if (!conversionType.isEmpty()) {
            this.conversionType = conversionType;
        }
    }

    public void addHistoryData(String s){
        historyData.add(s);
        notifyDataSetChanged();
    }

    public void clearHistoryData(){
        historyData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
