package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by BigBuddha on 2017/08/09.
 */

public class SettingFragment extends PreferenceFragment {

    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.date);

        mPreference = getActivity().getSharedPreferences(ThemeUtils.PREFERENCE, MODE_PRIVATE);
        mEditor = mPreference.edit();

        Preference preference = findPreference("is_night_mode");
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean isChecked = (boolean)o;
                int theme = isChecked ? ThemeUtils.THEME_DARK : ThemeUtils.THEME_LIGHT;
                save( theme );

                getActivity().finish();
                getActivity().startActivity(new Intent(getActivity(), getActivity().getClass()));

                return true;
            }
        });
    }

    public void save(int theme) {
        mEditor.putInt(ThemeUtils.THEME, theme);
        mEditor.apply();
    }
}
