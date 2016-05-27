package com.ujujzk.androiddesignsupport.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import fr.ganfra.materialspinner.MaterialSpinner;
import rx.Observable;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] ITEMS = {"Add item...", "Item 2", "Error", "Item 4", "Item 5", "Item 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    spinner.setError("Error");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello SnackbarHello SnackbarHello SnackbarHello SnackbarHello SnackbarHello SnackbarHello Snackbar", Snackbar.LENGTH_LONG).show();
            }
        });


        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.d("myTAG",s);
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        myObservable.subscribe(mySubscriber);
    }
}
