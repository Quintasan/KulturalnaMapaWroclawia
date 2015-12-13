package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private RouteFragmentBinding routeFragmentBinding;
    private ObservableArrayList<Route> routes;
    private RouteAdapter routeAdapter;
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
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.list_routes);
        routes = new ObservableArrayList<>();
        routeAdapter = new RouteAdapter(routes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<Route>> query = _api.getAllRoutes();
        query.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Response<List<Route>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    routes.addAll(response.body());
                    routeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        recyclerView.setAdapter(routeAdapter);
        routeFragmentBinding.setRoutes(routes);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<Route>> query = _api.getAllRoutes();
                query.enqueue(new Callback<List<Route>>() {
                    @Override
                    public void onResponse(Response<List<Route>> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            routes = new ObservableArrayList<Route>();
                            routes.addAll(response.body());
                            routeAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_INDEFINITE).show();
                    }
                });
            }
        });
    }

}
