package utility.castles.getfile;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import utility.castles.getfile.TabFragment.FirstFragment;
import utility.castles.getfile.TabFragment.SecondFragment;
import utility.castles.getfile.TabFragment.ThirdFragment;


public class MainFragment extends AppCompatActivity {


    private final FragmentManager fm = getFragmentManager();
    private Boolean AisActive=false;
    private Fragment currentFragment=null;
    private FirstFragment FirstFragment = new FirstFragment();
    private SecondFragment SecondFragment = new SecondFragment();
    private ThirdFragment ThirdFragment = new ThirdFragment();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfragment);
        BottomNavigationView bnve = findViewById(R.id.bnve);


        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, ThirdFragment).hide(ThirdFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, SecondFragment).hide(SecondFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, FirstFragment).commit();
        AisActive = true;
        currentFragment = FirstFragment;
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectFragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_getfile:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(FirstFragment).commit();
                        currentFragment = FirstFragment;
                        break;
                    case R.id.menu_debug:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(SecondFragment).commit();
                        currentFragment = SecondFragment;
                        break;
                    case R.id.menu_setting:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(ThirdFragment).commit();
                        currentFragment = ThirdFragment;
                        break;

                }




                return loadfragment(currentFragment);
            }
        });
    }


    private boolean loadfragment(Fragment fragment){

        if(fragment!=null) {
            //FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.lay_container, fragment,fragment.getTag());
            //transaction.commit();
            return true;
        }
        return false;
    }








}