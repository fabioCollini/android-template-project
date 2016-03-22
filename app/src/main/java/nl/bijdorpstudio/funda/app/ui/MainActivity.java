package nl.bijdorpstudio.funda.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bijdorpstudio.funda.app.FundaApp;
import nl.bijdorpstudio.funda.app.R;
import nl.bijdorpstudio.funda.app.inject.ActivityComponent;
import nl.bijdorpstudio.funda.app.inject.AppComponent;
import nl.bijdorpstudio.funda.core.Funda;
import nl.bijdorpstudio.funda.core.data.Broker;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity
    extends AppCompatActivity
{
    @Bind( R.id.recycler_view )
    RecyclerView recyclerView;

    @Inject
    BrokersAdapter brokersAdapter;

    @Inject
    Funda funda;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        injectDependencies();

        setupUI();

        callFunda();
    }

    private void callFunda()
    {
        final Observable<List<Pair<Broker, Integer>>> fundaThrottledLast =
            funda.topBrokers( "/Amstelveen", 10 ).throttleWithTimeout( 50, TimeUnit.MILLISECONDS );

        Observable.interval( 300L, TimeUnit.MILLISECONDS ).zipWith( fundaThrottledLast,
                                                                    ( time, list ) -> list ).subscribeOn(
            Schedulers.newThread() ).observeOn( AndroidSchedulers.mainThread() ).subscribe(
            brokersAdapter::setBrokers );
    }

    private void injectDependencies()
    {
        //noinspection ResourceType
        final AppComponent component =
            (AppComponent) getApplicationContext().getSystemService( FundaApp.APP_INJECTION );
        final ActivityComponent activityComponent = component.getActivityComponent();

        activityComponent.inject(this);
    }

    private void setupUI()
    {
        setContentView( R.layout.activity_main );

        ButterKnife.bind( this );

        prepareRecyclerView();
    }

    private void prepareRecyclerView()
    {
        final LinearLayoutManager layoutManager = new LinearLayoutManager( recyclerView.getContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( brokersAdapter );
    }
}
