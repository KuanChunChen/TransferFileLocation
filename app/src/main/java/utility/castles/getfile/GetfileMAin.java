package utility.castles.getfile;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import utility.castles.getfile.Define;

import static utility.castles.getfile.Define.d_DIR_PATH;
import static utility.castles.getfile.Define.d_FILE_TYPE;

public class GetfileMAin {





    public String writeFile(String fileName, String strWrite)
    {

        String strRespone ="";

        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED) ) {
            try {



                String fullPath =  Environment.getExternalStorageDirectory().getAbsolutePath();
                String savePath = fullPath + File.separator  + fileName + d_FILE_TYPE;

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
            //String fullPath = d_DIR_PATH;
            String fullPath =Environment.getExternalStorageDirectory().getAbsolutePath();
            String savePath = fullPath + File.separator + strfileName + d_FILE_TYPE;

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


        String strRespone ="";

        try {

            //String fullPath = d_DIR_PATH;
            String fullPath =Environment.getExternalStorageDirectory().getAbsolutePath();
            String savePath = fullPath + File.separator +strfileName+d_FILE_TYPE;


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

            //String fullPath = d_DIR_PATH;
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String savePath = fullPath + File.separator +strfileName+d_FILE_TYPE;


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
