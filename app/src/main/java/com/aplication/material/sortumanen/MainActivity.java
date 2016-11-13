package com.aplication.material.sortumanen;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aplication.material.sortumanen.fragments.EventListFragment;
import com.aplication.material.sortumanen.managers.StatusManager;

public class MainActivity extends BaseActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * init view after injecting frag
     */
    @Override
    public void initView() {
        getSupportActionBar().setTitle("Events");
        getSupportActionBar().setElevation(0);
    }

    @Override
    public void onBackPressed()
    {
        if (StatusManager.getInstance().isFilterMode()) {
            Log.e("TAG", "FILTER MODE");
            StatusManager.getInstance().setIdleMode();
            ((EventListFragment) getSupportFragmentManager().getFragments().get(0)).onBackPressed();
            return;
        }
        super.onBackPressed();
    }
}
