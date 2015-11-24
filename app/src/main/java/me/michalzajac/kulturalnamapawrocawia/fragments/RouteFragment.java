package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.api.API;
import me.michalzajac.kulturalnamapawrocawia.models.Route;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class RouteFragment extends Fragment implements AbsListView.OnItemClickListener {

    private final static String TAG = RouteFragment.class.getSimpleName();

    private OnFragmentInteractionListener _routeFragmentListener;
    private ArrayAdapter<Route> _routeArrayAdapter;
    private AbsListView _routeListView;
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
        View view = inflater.inflate(R.layout.fragment_trail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        _routeArrayAdapter = new ArrayAdapter<Route>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<Route>());
        _routeListView = (AbsListView) view.findViewById(android.R.id.list);
        _routeListView.setAdapter(_routeArrayAdapter);
        Call<List<Route>> query = _api.getAllRoutes();
        query.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Response<List<Route>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    _routeArrayAdapter.addAll(response.body());
                    _routeArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        _routeListView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _routeFragmentListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _routeFragmentListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (_routeFragmentListener != null) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Route selectedRoute = (Route) parent.getItemAtPosition(position);
            _routeFragmentListener.onTrailSelected(selectedRoute);
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = _routeListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onTrailSelected(Route selectedRoute);
    }

}
