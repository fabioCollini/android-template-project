package nl.bijdorpstudio.funda.app.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import nl.bijdorpstudio.core.data.Broker;
import nl.bijdorpstudio.funda.app.R;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BrokersAdapter
    extends RecyclerView.Adapter<BrokersAdapter.ViewHolder>
{
    private List<Pair<Broker, Integer>> brokers = new ArrayList<>();

    @Inject
    public BrokersAdapter()
    {
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.broker_row_layout, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position )
    {
        Pair<Broker, Integer> brokerInfo = brokers.get( position );
        holder.brokerName.setText( brokerInfo.first.getName() );
        holder.propertiesCount.setText( String.valueOf( brokerInfo.second ) );
    }

    @Override
    public int getItemCount()
    {
        return brokers.size();
    }

    public void setBrokers( @NonNull final List<Pair<Broker, Integer>> brokers )
    {
        this.brokers = brokers;
        notifyDataSetChanged();
    }

    public class ViewHolder
        extends RecyclerView.ViewHolder
    {
        TextView brokerName;

        TextView propertiesCount;

        public ViewHolder( View itemView )
        {
            super( itemView );

            brokerName = (TextView) itemView.findViewById( R.id.broker_name );
            propertiesCount = (TextView) itemView.findViewById( R.id.properties_number );
        }
    }
}
