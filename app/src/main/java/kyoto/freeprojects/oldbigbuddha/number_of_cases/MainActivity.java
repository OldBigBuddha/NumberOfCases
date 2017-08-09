package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.app.UiModeManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.math.BigDecimal;
import java.math.BigInteger;

import kyoto.freeprojects.oldbigbuddha.number_of_cases.databinding.ActivityMainBinding;
import static kyoto.freeprojects.oldbigbuddha.number_of_cases.Symbol.*;

/**
 * Copyright 2017 Big Buddha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private Symbol mSymbol;

    private SharedPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreference = getSharedPreferences(ThemeUtils.PREFERENCE, MODE_PRIVATE);
        ThemeUtils.setTheme(this, mPreference.getInt(ThemeUtils.THEME, ThemeUtils.THEME_DARK));

        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);

        initOnClick();
    }

    private void initOnClick() {
        mBinding.btPermutation.setOnClickListener(onClickListener);
        mBinding.btCombination.setOnClickListener(onClickListener);
        mBinding.btHomogeneousProduct.setOnClickListener(onClickListener);
        mBinding.btFactorial.setOnClickListener(onClickListener);
        mBinding.btEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigInteger n = new BigInteger( mBinding.etNum1.getText().toString() );
                BigInteger r = null;
                if (!TextUtils.isEmpty(mBinding.etNum2.getText())) {
                    r = new BigInteger( mBinding.etNum2.getText().toString() );
                }
                switch (mSymbol) {
                    case PERMUTATION: {
                        setAnswer( CasesUtils.permutation(n, r) );
                        break;
                    }
                    case COMBINATION: {
                        setAnswer( CasesUtils.combination(n, r) );
                        break;
                    }
                    case HOMOGENEOUSPRODUCT: {
                        setAnswer( CasesUtils.homogeneousProduct(n, r));
                        break;
                    }
                    case FACTORIAL: {
                        setAnswer( CasesUtils.factorial(n) );
                    }
                }
            }
        });

        mBinding.btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.etNum1.setText("");
                mBinding.etNum2.setText("");
                mBinding.tvOperator.setText("");
                mBinding.tvAnswer.setText("");
                mSymbol = null;
                mBinding.etNum1.setFocusableInTouchMode(true);
                mBinding.etNum1.requestFocus(View.FOCUS_UP);
                mBinding.etNum1.setSelection(0);
            }
        });
    }

    private void setOperator(String ope) {
        mBinding.tvOperator.setText(ope);
    }

    private void setAnswer(BigInteger answer) {
        mBinding.tvAnswer.setText(answer.toString());
    }

    private void moveFocus() {
        mBinding.etNum2.setFocusableInTouchMode(true);
        mBinding.etNum2.requestFocus(View.FOCUS_UP);
        mBinding.etNum2.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.go_setting_menu: {
                boolean isChecked = !item.isChecked();
                Log.d("Menu Item", "Checked = " + isChecked);
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String ope = (String)view.getTag();
            if (ope.equals(PERMUTATION.ope)) {
                mSymbol = PERMUTATION;
            } else if (ope.equals(COMBINATION.ope)) {
                mSymbol = COMBINATION;
            } else if (ope.equals(HOMOGENEOUSPRODUCT.ope)) {
                mSymbol = HOMOGENEOUSPRODUCT;
            } else if (ope.equals(FACTORIAL.ope)) {
                mSymbol = FACTORIAL;
            }
            setOperator(mSymbol.ope);
            moveFocus();
        }
    };
}