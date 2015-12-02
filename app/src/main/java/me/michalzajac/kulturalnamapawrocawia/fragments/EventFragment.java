package me.michalzajac.kulturalnamapawrocawia.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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


public class EventFragment extends Fragment implements AbsListView.OnItemClickListener {

    private final static String TAG = EventFragment.class.getSimpleName();

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private EventFragmentBinding eventFragmentBinding;
    private ObservableArrayList<Event> events;

    private OnFragmentInteractionListener _eventFragmentListener;
    private ArrayAdapter<Event> _eventArrayAdapter;
    private AbsListView _eventListView;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        events = new ObservableArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<Event>> query = _api.getAllEvents();
        query.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Response<List<Event>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    events.addAll(response.body());
                    EventAdapter eventAdapter = new EventAdapter(events);
                    recyclerView.setAdapter(eventAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        eventFragmentBinding.setEvents(events);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _eventFragmentListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _eventFragmentListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (_eventFragmentListener != null) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Event selectedEvent = (Event) parent.getItemAtPosition(position);
            _eventFragmentListener.onEventSelected(selectedEvent);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = _eventListView.getEmptyView();

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
        public void onEventSelected(Event selectedEvent);
    }

}
