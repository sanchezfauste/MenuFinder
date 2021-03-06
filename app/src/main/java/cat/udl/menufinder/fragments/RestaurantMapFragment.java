package cat.udl.menufinder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.Utils;

public class RestaurantMapFragment extends MasterFragment implements OnMapReadyCallback {
    private static final String TAG = RestaurantMapFragment.class.getName();
    private Restaurant restaurant;

    public static RestaurantMapFragment newInstance() {
        RestaurantMapFragment myFragment = new RestaurantMapFragment();
        return myFragment;
    }

    public static RestaurantMapFragment newInstance(Restaurant restaurant) {
        RestaurantMapFragment myFragment = new RestaurantMapFragment();
        myFragment.restaurant = restaurant;
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng r = new LatLng(41.6175899, 0.6200145999999904);
        if (restaurant != null) {
            r = Utils.getLatLngOfRestaurant(restaurant, getActivity());
            putRestaurantInMap(googleMap, restaurant);
        } else putRestaurantsInMap(googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(r, 14));
    }

    private void putRestaurantsInMap(GoogleMap googleMap) {
        List<Restaurant> restaurants = getDbManager().getRestaurants();
        for (Restaurant restaurant : restaurants) {
            putRestaurantInMap(googleMap, restaurant);
        }
    }

    private void putRestaurantInMap(GoogleMap googleMap, Restaurant restaurant) {
        LatLng latLng = Utils.getLatLngOfRestaurant(restaurant, getActivity());
        if (latLng != null)
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.marker_title, restaurant.getName()))
                    .snippet(String.format("%s\n%s\n%s", restaurant.getAddressWithCity(),
                            restaurant.getPhone(), restaurant.getEmail()))
            );
    }


}
