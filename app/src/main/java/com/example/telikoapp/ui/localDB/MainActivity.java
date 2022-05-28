package com.example.telikoapp.ui.localDB;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.telikoapp.R;
import com.example.telikoapp.ui.cloudDB.Customer;
import com.example.telikoapp.ui.localDB.MyDatabase;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.telikoapp.databinding.ActivityMainBinding;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



import com.example.telikoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {




    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    public  static FirebaseFirestore db;

    //test connecting firebase



    //test migration
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
       @Override
      public void migrate(SupportSQLiteDatabase MyDatabase) {
             //Since we didn't alter the table, there's nothing else to do here.

        }
    };


    Context context;

// end test migration

    //test migration
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase MyDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.

            myDatabase.execSQL("DROP TABLE IF EXISTS table_tmp");
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS `table_tmp` ...");

            myDatabase.execSQL("insert into table_tmp (`id`, `name` , ...");

            myDatabase.execSQL("DROP INDEX IF EXISTS `index_table_name`");

            myDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_table_name` ON `table_tmp` (`name`)");

            myDatabase.execSQL("DROP TABLE IF EXISTS table");

            myDatabase.execSQL("alter table table_tmp rename to table");

        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase MyDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.

            myDatabase.execSQL("DROP TABLE IF EXISTS table_tmp");
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS `table_tmp` ...");

            myDatabase.execSQL("insert into table_tmp (`id`, `name` , ...");

            myDatabase.execSQL("DROP INDEX IF EXISTS `index_table_name`");

            myDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_table_name` ON `table_tmp` (`name`)");

            myDatabase.execSQL("DROP TABLE IF EXISTS table");

            myDatabase.execSQL("alter table table_tmp rename to table");

        }
    };

    Context context1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        fragmentManager = getSupportFragmentManager();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "DB").allowMainThreadQueries().addMigrations(MIGRATION_1_2).build();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "DB").allowMainThreadQueries().addMigrations(MIGRATION_2_3).build();
        db=FirebaseFirestore.getInstance();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}