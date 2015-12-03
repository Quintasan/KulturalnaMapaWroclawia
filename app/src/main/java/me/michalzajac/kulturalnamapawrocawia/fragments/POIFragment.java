package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private PoiFragmentBinding poiFragmentBinding;
    private ObservableArrayList<POI> pois;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        pois = new ObservableArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<POI>> query = _api.getAllPOIs();
        query.enqueue(new Callback<List<POI>>() {
            @Override
            public void onResponse(Response<List<POI>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    pois.addAll(response.body());
                    POIAdapter poiAdapter = new POIAdapter(pois);
                    recyclerView.setAdapter(poiAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        poiFragmentBinding.setPois(pois);
    }

}
