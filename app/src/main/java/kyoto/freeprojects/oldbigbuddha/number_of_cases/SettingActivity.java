package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import kyoto.freeprojects.oldbigbuddha.number_of_cases.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding mBinding;

    private SharedPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreference = getSharedPreferences(ThemeUtils.PREFERENCE, MODE_PRIVATE);
        ThemeUtils.setTheme(this, mPreference.getInt(ThemeUtils.THEME, ThemeUtils.THEME_LIGHT));
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        setActionBar(mBinding.settingToolbar);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.fragment, new SettingFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ThemeUtils.THEME, mPreference.getInt(ThemeUtils.THEME, ThemeUtils.THEME_LIGHT));
        startActivity(intent);
//        onBackPressed();
        return true;
    }
}
