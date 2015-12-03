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
import me.michalzajac.kulturalnamapawrocawia.activities.RouteDetailsActvity;
import me.michalzajac.kulturalnamapawrocawia.models.Route;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.BindingHolder> {
    private List<Route> routes;

    public RouteAdapter(List<Route> routes) {
        this.routes = routes;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() { return binding; }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_card, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Route route = routes.get(position);
        holder.getBinding().setVariable(BR.route, route);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, RouteDetailsActvity.class);
                intent.putExtra("selectedRoute", (Parcelable) route);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}
