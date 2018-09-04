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


public class MainActivity extends AppCompatActivity {



    private final static String d_FILE_NAME="CTMSDebugLog";
    private final static String d_DIR_PATH="/data/CTMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart =findViewById(R.id.btn1);
        Button btnEnd =findViewById(R.id.btn2);
        Button btnGetSize =findViewById(R.id.btn3);
        Button btnDelFile =findViewById(R.id.btn4);



        //writeFile(d_FILE_NAME,        );

        //tVPath.setText(d_DIR_PATH+File.separator+d_FILE_NAME+".txt");


        //listView
        //String[] strarrSplit = strReadData.split("\n");
        //ListView mList = findViewById(R.id.listView1);
        //ListAdapter mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strarrSplit);
        //mList.setAdapter(mAdapter);


        btnStart.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                String strReadData ="";
                String strWriteData="";
                TextView tVPath = findViewById(R.id.test1);
                TextView tVStatus = findViewById(R.id.test2);
                tVStatus.setMovementMethod(ScrollingMovementMethod.getInstance());

                strReadData=readFile(d_FILE_NAME);
                //String[] strarrSplit = strReadData.split("\n");
                if(strReadData!="FileNotFoundException"&&strReadData!="IOExecption"){
                    int fileSize = strReadData.length();

                    tVPath.setText("StoreDataPosition : "+d_DIR_PATH+File.separator+d_FILE_NAME+".log");
                    tVPath.setTextColor(Color.WHITE);
                    tVPath.setBackgroundColor(android.graphics.Color.RED);


                    //writeFile Start
                    strWriteData = writeFile(d_FILE_NAME,strReadData);
                    if(strWriteData=="Suc!"){
                        tVStatus.setText(strReadData);
                    }else{
                        //失敗下顯示錯誤情況
                        tVStatus.setText(strWriteData);
                    }

                }else{
                    tVStatus.setText(strReadData);
                }

            }

        });



        //exit button
        btnEnd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        //get size button
        btnGetSize.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strSize;
                TextView tVSize =findViewById(R.id.test1);
                strSize = getFileSize(d_FILE_NAME);
                tVSize.setText("file size:"+strSize);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });


        btnDelFile.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean boDel;
                TextView tVSize =findViewById(R.id.test1);
                boDel = delFile(d_FILE_NAME);
                tVSize.setText("Delete fail (boolean):"+boDel);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });

    }





    public String writeFile(String fileName, String strWrite)
    {

        String strRespone ="";

        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED) ) {
            try {



                String fullPath =  Environment.getExternalStorageDirectory().getAbsolutePath();
                String savePath = fullPath + File.separator  + fileName + ".log";

                File file = new File(savePath);

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(strWrite);


                bw.close();

                strRespone ="Suc!";


            } catch (IOException e) {
                e.printStackTrace();

                strRespone ="Fail!";
            } catch (Exception e){
                e.printStackTrace();
                strRespone = e.toString();

            }
        }else{
            strRespone ="NO SD CARD!";


        }

        return  strRespone;
    }




    public String readFile(String strfileName){

        BufferedReader buffReader = null;
        String strRespone ="";

        try {
            StringBuffer strBuffOutput = new StringBuffer();
            String fullPath = d_DIR_PATH;
            //String fullPath =Environment.getExternalStorageDirectory().getAbsolutePath();
            String savePath = fullPath + File.separator +strfileName+".log";

            buffReader = new BufferedReader(new FileReader(savePath));
            String line = "";
            while ((line = buffReader.readLine()) != null) {
                strBuffOutput.append(line +"\n");


                if(strBuffOutput.capacity()>102400)
                {
                    strRespone +=strBuffOutput.toString();
                    strBuffOutput.setLength(0);
                }
            }
            strRespone += strBuffOutput.toString();
            buffReader.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            strRespone ="FileNotFoundException";
        } catch (IOException e) {
            e.printStackTrace();
            strRespone ="IOExecption";

        }
        return strRespone;
    }

    public String getFileSize(String strfileName){

        BufferedReader buffReader = null;
        String strRespone ="";

        try {

            String fullPath = d_DIR_PATH;
            //String fullPath =Environment.getExternalStorageDirectory().getAbsolutePath();
            String savePath = fullPath + File.separator +strfileName+".log";


            File fr= new File(savePath);
            long lSize=fr.length();
            strRespone=Long.toString(lSize);
        } catch (Exception e) {
            e.printStackTrace();
            strRespone ="Execption";

        }
        return strRespone;

    }

    public boolean delFile(String strfileName){

        BufferedReader buffReader = null;
        boolean boRespone =false;

        try {

            String fullPath = d_DIR_PATH;

            String savePath = fullPath + File.separator +strfileName+".log";


            File fr= new File(savePath);
            if(fr.exists()){
                boRespone=fr.delete();

            }

        } catch (Exception e) {
            e.printStackTrace();


        }
        return boRespone;

    }





}

