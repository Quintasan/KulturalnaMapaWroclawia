package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.adapters.EventAdapter;
import me.michalzajac.kulturalnamapawrocawia.api.API;
import me.michalzajac.kulturalnamapawrocawia.databinding.EventFragmentBinding;
import me.michalzajac.kulturalnamapawrocawia.models.Event;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class EventFragment extends Fragment {

    private final static String TAG = EventFragment.class.getSimpleName();

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private EventFragmentBinding eventFragmentBinding;
    private ObservableArrayList<Event> events;
    private EventAdapter eventAdapter;
    private API.APIInterface _api;

    public EventFragment() {
    }

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
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
        eventFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.event_fragment, container, false);
        return eventFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        events = new ObservableArrayList<>();
        eventAdapter = new EventAdapter(events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<Event>> query = _api.getAllEvents();
        query.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Response<List<Event>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    events.addAll(response.body());
                    eventAdapter.addAll(events);
                    eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        recyclerView.setAdapter(eventAdapter);
        eventFragmentBinding.setEvents(events);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<Event>> query = _api.getAllEvents();
                query.enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Response<List<Event>> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            events = new ObservableArrayList<>();
                            events.addAll(response.body());
                            eventAdapter.notifyDataSetChanged();
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
