package com.avenueinfotech.shreegurudevdatta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.avenueinfotech.shreegurudevdatta.Fragments.CarFragment;
import com.avenueinfotech.shreegurudevdatta.Fragments.LuxuryCarFragment;
import com.avenueinfotech.shreegurudevdatta.Fragments.OldCarFragment;
import com.avenueinfotech.shreegurudevdatta.Fragments.PopularCarFragment;
import com.avenueinfotech.shreegurudevdatta.Fragments.SportCarFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    //    @BindView(R.id.drawer_recyclerView) RecyclerView drawerRecyclerView;
    private Drawer.Result navigationDrawerLeft;
    private Drawer.Result getNavigationDrawerRight;
    private AccountHeader.Result headerNavigationLeft;
    private int mPositionClicked;
    private ViewPager mViewPager;
    private int mItemDrawerSelected;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity.this, "OnCheckedChangeListener: "+( b ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setSupportActionBar(mtoolbar);


        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.car_1)
                .addProfiles(
                        new ProfileDrawerItem().withName("Shashi").withEmail("sukenkar@gmail.com").withIcon(getResources().getDrawable(R.drawable.sport)),
                        new ProfileDrawerItem().withName("Nishu").withEmail("nishu@gmail.com").withIcon(getResources().getDrawable(R.drawable.ferrai)),
                        new ProfileDrawerItem().withName("Abhishek").withEmail("abhishek@gmail.com").withIcon(getResources().getDrawable(R.drawable.bmw)),
                        new ProfileDrawerItem().withName("love").withEmail("love@gmail.com").withIcon(getResources().getDrawable(R.drawable.flayer))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                        Toast.makeText(MainActivity.this, "onProfileChanged: "+iProfile.getName(), Toast.LENGTH_SHORT).show();
                        headerNavigationLeft.setBackgroundRes(R.drawable.car_show);
                        return false;
                    }
                })
                .build();

        //navigation Drawer
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationLeft)
//                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
//                    @Override
//                    public boolean onNavigationClickListener(View view) {
//                        return false;
//                    }
//                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        mViewPager.setCurrentItem(i);


                        Fragment frag = null;
                        mItemDrawerSelected = i;

                        if(i == 0){ // ALL CARS
                            frag = new CarFragment();
                        }
                        else if(i == 1){ // LUXURY CAR
                            frag = new LuxuryCarFragment();
                        }
                        else if(i == 2){ // SPORT CAR
                            frag = new SportCarFragment();
                        }
                        else if(i == 3){ // OLD CAR
                            frag = new OldCarFragment();
                        }
                        else if(i == 4){ // POPULAR CAR
                            frag = new PopularCarFragment();
                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();

//                        mToolbar.setTitle( ((PrimaryDrawerItem) iDrawerItem).getName() );
                    }
                })
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size();count < tam; count++){
//                            if (count == mPositionClicked && mPositionClicked <= 3){
//                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count);
//                                aux.setIcon(getResources().getDrawable( getCorretcDrawerIcon( count, false)));
//                                break;
//                            }
//                        }
//
//                        if(i <= 3){
//                            ((PrimaryDrawerItem) iDrawerItem).setIcon(getResources().getDrawable( getCorretcDrawerIcon( i, true)));
//                        }
//
//                        mPositionClicked = i;
//                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
//                    }
//                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick: "+i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Carros Esportives").withIcon(getResources().getDrawable(R.drawable.car_1)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Audi Delecus").withIcon(getResources().getDrawable(R.drawable.audi)));
        //     navigationDrawerLeft.addItem(new DividerDrawerItem());
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Ferrai dex").withIcon(getResources().getDrawable(R.drawable.ferrai)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Mercedes dex").withIcon(getResources().getDrawable(R.drawable.mercedes)));
        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("configuration"));
//        navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notification").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
//        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("News").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));






    }

    private int getCorretcDrawerIcon(int position, boolean isSelected) {
        switch (position){
            case 0:
                return ( isSelected ? R.drawable.car_1 : R.drawable.audi);
            case 1:
                return ( isSelected ? R.drawable.ferrai: R.drawable.bmw);
            case 3:
                return ( isSelected ? R.drawable.flayer : R.drawable.mercedes);
        }
        return (0);
    }

    public interface BackButtonSupportFragment {
        // return true if your fragment has consumed
        // the back press event, false if you didn't
        boolean onBackPressed();
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
