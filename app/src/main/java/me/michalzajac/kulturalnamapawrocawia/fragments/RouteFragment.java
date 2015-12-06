package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.adapters.RouteAdapter;
import me.michalzajac.kulturalnamapawrocawia.api.API;
import me.michalzajac.kulturalnamapawrocawia.databinding.RouteFragmentBinding;
import me.michalzajac.kulturalnamapawrocawia.models.Route;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class RouteFragment extends Fragment {

    private final static String TAG = RouteFragment.class.getSimpleName();

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private RouteFragmentBinding routeFragmentBinding;
    private ObservableArrayList<Route> routes;
    private API.APIInterface _api;

    public RouteFragment() {
    }

    public static RouteFragment newInstance() {
        RouteFragment fragment = new RouteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _api = API.getClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        routeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.route_fragment, container, false);
        return routeFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        routes = new ObservableArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<Route>> query = _api.getAllRoutes();
        query.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Response<List<Route>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    routes.addAll(response.body());
                    RouteAdapter routeAdapter = new RouteAdapter(routes);
                    recyclerView.setAdapter(routeAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "Exception was thrown, please report this to developer", t);
            }
        });
        routeFragmentBinding.setRoutes(routes);
    }

}
