package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hcmus.vuphan.moneykeeper.R;

/**
 * Created by Monster on 6/25/2016.
 */
public class AboutFragment extends Fragment {
    public static void setContext(Context context) {
        AboutFragment.context = context;
    }

    static Context context;

    public static AboutFragment concreateFragment(Context context) {
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.setContext(context);
        return aboutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_me_layout, container, false);

        return view;
    }
}
