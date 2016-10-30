package com.aplication.material.sortumanen;
import android.view.Menu;
import android.view.MenuItem;

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
//        GithubServiceImpl githubService = new GithubServiceImpl();
//        githubService.init();
    }
}
