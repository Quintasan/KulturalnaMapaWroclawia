package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import me.michalzajac.kulturalnamapawrocawia.models.POI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class POIFragment extends Fragment implements AbsListView.OnItemClickListener {

    private final static String TAG = POIFragment.class.getSimpleName();

    private OnFragmentInteractionListener _poiFragmentListener;
    private ArrayAdapter<POI> _poiArrayAdapter;
    private AbsListView _poiListView;
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
        View view = inflater.inflate(R.layout.fragment_poi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        _poiArrayAdapter = new ArrayAdapter<POI>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<POI>());
        _poiListView = (AbsListView) view.findViewById(android.R.id.list);
        _poiListView.setAdapter(_poiArrayAdapter);
        Call<List<POI>> query = _api.getAllPOIs();
        query.enqueue(new Callback<List<POI>>() {
            @Override
            public void onResponse(Response<List<POI>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    _poiArrayAdapter.addAll(response.body());
                    _poiArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        _poiListView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _poiFragmentListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _poiFragmentListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (_poiFragmentListener != null) {
            POI selectedPOI = (POI) parent.getItemAtPosition(position);
            _poiFragmentListener.onPOISelected(selectedPOI);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = _poiListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onPOISelected(POI selectedPOI);
    }

}
