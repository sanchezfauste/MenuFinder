package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.DetailRestaurantFragment;
import cat.udl.menufinder.models.Restaurant;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class DetailRestaurantActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra(KEY_RESTAURANT);
        DetailRestaurantFragment fragment = (DetailRestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        fragment.update(restaurant);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }
}
