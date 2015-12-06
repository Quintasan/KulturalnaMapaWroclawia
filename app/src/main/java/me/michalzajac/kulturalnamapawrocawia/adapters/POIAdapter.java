package me.michalzajac.kulturalnamapawrocawia.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.michalzajac.kulturalnamapawrocawia.BR;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.activities.POIDetailsActvity;
import me.michalzajac.kulturalnamapawrocawia.models.POI;

public class POIAdapter extends RecyclerView.Adapter<POIAdapter.BindingHolder> {
    private List<POI> pois;

    public POIAdapter(List<POI> pois) {
        this.pois = pois;
    }

    public void clear() {
        pois.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<POI> pois) {
        this.pois.addAll(pois);
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_card, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final POI poi = pois.get(position);
        holder.getBinding().setVariable(BR.poi, poi);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, POIDetailsActvity.class);
                intent.putExtra("selectedPOI", (Parcelable) poi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pois.size();
    }
}
