package com.gianlu.pretendyourexyzzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gianlu.commonutils.Toaster;
import com.gianlu.pretendyourexyzzy.Adapters.StarredDecksAdapter;
import com.gianlu.pretendyourexyzzy.Cards.StarredDecksManager;

public class StarredDecksActivity extends AppCompatActivity implements StarredDecksAdapter.IAdapter {
    private static CardcastDeckActivity.IOngoingGame handler;

    public static void startActivity(Context context, CardcastDeckActivity.IOngoingGame handler) {
        if (StarredDecksManager.hasAnyDeck(context)) {
            StarredDecksActivity.handler = handler;
            context.startActivity(new Intent(context, StarredDecksActivity.class));
        } else {
            Toaster.show(context, Utils.Messages.NO_STARRED_DECKS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);
        setTitle(R.string.starredDecks);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.recyclerViewLayout_loading).setVisibility(View.GONE);
        findViewById(R.id.recyclerViewLayout_swipeRefresh).setVisibility(View.VISIBLE);
        RecyclerView list = findViewById(R.id.recyclerViewLayout_list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(new StarredDecksAdapter(this, StarredDecksManager.loadDecks(this), this));
    }

    @Override
    public void onDeckSelected(StarredDecksManager.StarredDeck deck) {
        CardcastDeckActivity.startActivity(this, deck.code, deck.name, handler);
    }
}