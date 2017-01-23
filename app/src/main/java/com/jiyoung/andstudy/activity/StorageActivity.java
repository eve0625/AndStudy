package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiyoung.andstudy.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {

    private EditText edittext;

    private static final int CREATE_REQUEST_CODE = 40;
    private static final int OPEN_REQUEST_CODE = 41;
    private static final int SAVE_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        edittext = (EditText) findViewById(R.id.et_text);

        ((Button) findViewById(R.id.btn_new)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE); //파일 디스크립터로 열수 있는 파일들만 반환
                intent.setType("text/plain"); //열고자 하는 파일의 MIME 타입 지정
                intent.putExtra(Intent.EXTRA_TITLE, "newfile.txt"); //임시파일명 지정

                startActivityForResult(intent, CREATE_REQUEST_CODE);
            }
        });

        ((Button) findViewById(R.id.btn_open)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/plain");

                startActivityForResult(intent, OPEN_REQUEST_CODE);
            }
        });

        ((Button) findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE); //파일 디스크립터로 열수 있는 파일들만 반환
                intent.setType("text/plain"); //열고자 하는 파일의 MIME 타입 지정

                startActivityForResult(intent, SAVE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri currentUri = null;

        if (resultCode == RESULT_OK) {
            if (requestCode == CREATE_REQUEST_CODE) {
                if (data != null) {
                    edittext.setText("");
                }
            } else if (requestCode == SAVE_REQUEST_CODE) {
                if (data != null) {
                    currentUri = data.getData();
                    writeFileContent(currentUri);
                }
            } else if (requestCode == OPEN_REQUEST_CODE) {
                if (data != null) {
                    currentUri = data.getData();
                    String content = readFileContent(currentUri);
                    edittext.setText(content);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String readFileContent(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String currentline;
            while((currentline = reader.readLine()) != null) {
                stringBuilder.append(currentline + "\n");
            }
            inputStream.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void writeFileContent(Uri uri) {
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "w"); //쓰기모드
            FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());

            String textContent = edittext.getText().toString();

            fileOutputStream.write(textContent.getBytes());
            fileOutputStream.close();
            pfd.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
