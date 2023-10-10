package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

        private EditText memoEditText;
        private SharedPreferences sharedPreferences;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_home, container, false);

                memoEditText = view.findViewById(R.id.editTextMemo);

                sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

                // 메모 텍스트를 불러와서 EditText에 설정
                String savedMemo = sharedPreferences.getString("memo", "");
                memoEditText.setText(savedMemo);

                // TextWatcher를 생성하고 EditText에 등록
                memoEditText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                // 입력 전
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                // 텍스트가 변경될 때마다 호출됩니다.
                                saveMemo(); // 텍스트가 변경될 때마다 메모를 저장합니다.
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                // 입력 후
                        }
                });

                return view;
        }

        private void saveMemo() {
                String memoText = memoEditText.getText().toString();

                // SharedPreferences.Editor를 사용하여 메모 저장
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("memo", memoText);
                editor.apply(); // 변경 내용을 저장
        }
}

