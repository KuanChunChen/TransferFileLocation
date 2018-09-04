package utility.castles.getfile;


import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static utility.castles.getfile.Define.d_FILE_NAME;
import static utility.castles.getfile.Define.d_FILE_TEST_STR;


public class MainActivity extends AppCompatActivity {



    //private final static String d_FILE_NAME="CTMSDebugLog";
    //private final static String d_DIR_PATH="/data/CTMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart =findViewById(R.id.btn1);
        Button btnEnd =findViewById(R.id.btn2);
        Button btnGetSize =findViewById(R.id.btn3);
        Button btnDelFile =findViewById(R.id.btn4);

        final GetfileMAin getfile = new GetfileMAin() ;

        getfile.writeFile(d_FILE_NAME,d_FILE_TEST_STR);

        //tVPath.setText(d_DIR_PATH+File.separator+d_FILE_NAME+".txt");


        //listView
        //String[] strarrSplit = strReadData.split("\n");
        //ListView mList = findViewById(R.id.listView1);
        //ListAdapter mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strarrSplit);
        //mList.setAdapter(mAdapter);

        /* **Start button** */
        btnStart.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                String strReadData ="";
                String strWriteData="";
                TextView tVPath = findViewById(R.id.test1);
                TextView tVStatus = findViewById(R.id.test2);
                tVStatus.setMovementMethod(ScrollingMovementMethod.getInstance());

                strReadData=getfile.readFile(d_FILE_NAME);
                //String[] strarrSplit = strReadData.split("\n");
                if(strReadData!="FileNotFoundException"&&strReadData!="IOExecption"){
                    int fileSize = strReadData.length();

                    //tVPath.setText("StoreDataPosition : "+d_DIR_PATH+File.separator+d_FILE_NAME+".log");
                    tVPath.setText(Environment.getExternalStorageDirectory().getAbsolutePath());
                    tVPath.setTextColor(Color.WHITE);
                    tVPath.setBackgroundColor(android.graphics.Color.RED);

                    //writeFile Start
                    strWriteData =getfile.writeFile(d_FILE_NAME,strReadData);
                    if(strWriteData=="Suc!"){
                        tVStatus.setText(strReadData);
                        tVStatus.setTextColor(Color.WHITE);
                        tVStatus.setBackgroundColor(android.graphics.Color.BLUE);
                    }else{
                        //失敗下顯示錯誤情況
                        tVStatus.setText(strWriteData);
                    }
                }else{
                    tVStatus.setText(strReadData);
                }

            }

        });



        /* **exit button** */
        btnEnd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        /* **get Size button** */
        btnGetSize.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strSize;
                TextView tVSize =findViewById(R.id.test1);
                strSize = getfile.getFileSize(d_FILE_NAME);
                tVSize.setText("file size:"+strSize);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });

        /* **DelFile button** */
        btnDelFile.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean boDel;


                TextView tVSize =findViewById(R.id.test1);
                boDel = getfile.delFile(d_FILE_NAME);
                tVSize.setText("Delete fail (boolean):"+boDel);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });

    }













}

