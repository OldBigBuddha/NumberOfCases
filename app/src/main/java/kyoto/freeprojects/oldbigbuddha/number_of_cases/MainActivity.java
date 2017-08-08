package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.StrictMode;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
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
                int n = Integer.parseInt(mBinding.etNum1.getText().toString());
                int r = 0;
                if (!TextUtils.isEmpty(mBinding.etNum2.getText())) {
                    r = Integer.parseInt( mBinding.etNum2.getText().toString() );
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

    private void setAnswer(int answer) {
        mBinding.tvAnswer.setText(answer + "");
    }

    private void moveFocus() {
        mBinding.etNum2.setFocusableInTouchMode(true);
        mBinding.etNum2.requestFocus(View.FOCUS_UP);
        mBinding.etNum2.setSelection(0);
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

enum Symbol {
    PERMUTATION("p"),
    COMBINATION("c"),
    HOMOGENEOUSPRODUCT("h"),
    FACTORIAL("!"), ;
    public final String ope;
    Symbol(String ope) {
        this.ope = ope;
    }
}
