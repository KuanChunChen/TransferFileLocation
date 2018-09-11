package utility.castles.getfile.TabFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import utility.castles.getfile.GetfileMAin;
import utility.castles.getfile.R;

import static utility.castles.getfile.Define.d_FILE_NAME;
import static utility.castles.getfile.Define.d_FILE_TEST_STR;


public class FirstFragment extends Fragment {

    private View view;
    public String[] strarrSplit;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Button btnStart,btnEnd,btnGetSize,btnDelFile;
        view=inflater.inflate(R.layout.fragment_1,null);
        btnStart=view.findViewById(R.id.frag1_btn1);
        btnEnd=view.findViewById(R.id.frag1_btn2);
        btnGetSize=view.findViewById(R.id.frag1_btn3);
        btnDelFile=view.findViewById(R.id.frag1_btn4);

        final GetfileMAin getfile = new GetfileMAin() ;
        getfile.writeFile(d_FILE_NAME,d_FILE_TEST_STR);


        btnStart.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                String strReadData ="";
                String strWriteData="";
                TextView tVPath = view.findViewById(R.id.frag1_text1);
                TextView tVStatus = view.findViewById(R.id.frag1_text2);
                tVStatus.setMovementMethod(ScrollingMovementMethod.getInstance());

                strReadData=getfile.readFile(d_FILE_NAME);
                //String[] strarrSplit = strReadData.split("\n");
                if(strReadData!="FileNotFoundException"&& strReadData!="IOExecption"){


                    //tVPath.setText("StoreDataPosition : "+d_DIR_PATH+File.separator+d_FILE_NAME+".log");
                    tVPath.setText("StoreDataPosition : "+ Environment.getExternalStorageDirectory().getAbsolutePath());
                    tVPath.setTextColor(Color.WHITE);
                    tVPath.setBackgroundColor(android.graphics.Color.RED);

                    //writeFile Start
                    strWriteData =getfile.writeFile(d_FILE_NAME,strReadData);
                    if(strWriteData=="Suc!"){


                        strarrSplit = strReadData.split("\n");


                        tVStatus.setText(strReadData);
                        // tVStatus.setText(strarrSplit[0]+strarrSplit[1]);


                    }else{
                        //失敗下顯示錯誤情況
                        tVPath.setText(strWriteData);

                    }
                }else{
                    tVPath.setText(strReadData);

                }
                tVStatus.setTextColor(Color.WHITE);
                tVStatus.setBackgroundColor(android.graphics.Color.BLUE);

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
                TextView tVSize =view.findViewById(R.id.frag1_text1);
                TextView tClear =view.findViewById(R.id.frag1_text2);
                strSize = getfile.getFileSize(d_FILE_NAME);
                tVSize.setText("file size:"+strSize);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
                tClear.setText("");
                tClear.setBackgroundColor(Color.WHITE);
            }
        });




        /* **DelFile button** */
        btnDelFile.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean boDel;


                TextView tVSize =view.findViewById(R.id.frag1_text1);
                TextView tClear =view.findViewById(R.id.frag1_text2);
                boDel = getfile.delFile(d_FILE_NAME);
                tVSize.setText("Delete file (boolean):"+boDel);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(Color.RED);
                tClear.setText("");
                tClear.setBackgroundColor(Color.WHITE);
            }
        });




        return view;
    }





}