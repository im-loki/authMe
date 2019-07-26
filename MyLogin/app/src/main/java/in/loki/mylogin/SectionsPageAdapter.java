package in.loki.mylogin;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mProgarmList = new ArrayList<>();
    private final List<String> mFragmentList = new ArrayList<>();

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String Title){
        mProgarmList.add(fragment);
        mFragmentList.add(Title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mProgarmList.get(position);
    }

    @Override
    public int getCount() {
        return mProgarmList.size();
    }
}
