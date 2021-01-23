package hr.ferit.converter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;
    private static final String TAB_NAME = "#%d";
    private String historyData = ""; //TODO: <- default string here
    private String conversionType = ""; //default

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return InputFragment.newInstance(conversionType, historyData);
            case 2:
                return HistoryFragment.newInstance();
            default:
                return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public void setHistoryData(String historyData){
        if(!historyData.isEmpty()){
            this.historyData = historyData;
        }
    }

    public void setConversionType(String conversionType){
        if(!conversionType.isEmpty()){
            this.conversionType = conversionType;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
