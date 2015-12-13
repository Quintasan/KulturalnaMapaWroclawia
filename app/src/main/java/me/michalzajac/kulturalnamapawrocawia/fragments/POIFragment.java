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
import me.michalzajac.kulturalnamapawrocawia.adapters.POIAdapter;
import me.michalzajac.kulturalnamapawrocawia.api.API;
import me.michalzajac.kulturalnamapawrocawia.databinding.PoiFragmentBinding;
import me.michalzajac.kulturalnamapawrocawia.models.POI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class POIFragment extends Fragment {

    private final static String TAG = POIFragment.class.getSimpleName();

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private PoiFragmentBinding poiFragmentBinding;
    private ObservableArrayList<POI> pois;
    private POIAdapter poiAdapter;
    private API.APIInterface _api;

    public POIFragment() {
    }

    public static POIFragment newInstance() {
        POIFragment fragment = new POIFragment();
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
        poiFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.poi_fragment, container, false);
        return poiFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.list_places);
        pois = new ObservableArrayList<>();
        poiAdapter = new POIAdapter(pois);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<POI>> query = _api.getAllPOIs();
        query.enqueue(new Callback<List<POI>>() {
            @Override
            public void onResponse(Response<List<POI>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    pois.addAll(response.body());
                    poiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        recyclerView.setAdapter(poiAdapter);
        poiFragmentBinding.setPois(pois);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<POI>> query = _api.getAllPOIs();
                query.enqueue(new Callback<List<POI>>() {
                    @Override
                    public void onResponse(Response<List<POI>> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            pois = new ObservableArrayList<POI>();
                            pois.addAll(response.body());
                            poiAdapter.notifyDataSetChanged();
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
