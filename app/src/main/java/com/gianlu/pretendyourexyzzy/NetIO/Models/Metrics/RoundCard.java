package com.gianlu.pretendyourexyzzy.NetIO.Models.Metrics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;

import com.gianlu.pretendyourexyzzy.NetIO.Models.BaseCard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RoundCard extends BaseCard {
    public final String text;
    public final String watermark;
    public final int numPick;
    public final String color;

    RoundCard(JSONObject obj) throws JSONException {
        text = Html.fromHtml(obj.getString("Text")).toString();
        watermark = Html.fromHtml(obj.getString("Watermark")).toString();

        JSONObject meta = obj.getJSONObject("Meta");
        numPick = meta.optInt("Pick", -1);
        color = meta.getString("Color");
    }

    @NonNull
    @Override
    public String text() {
        return text;
    }

    @Nullable
    @Override
    public String watermark() {
        return watermark;
    }

    @Override
    public int numPick() {
        return numPick;
    }

    @Override
    public int numDraw() {
        return 0;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public boolean unknown() {
        return false;
    }

    @Override
    public boolean black() {
        return Objects.equals(color, "black");
    }

    @Override
    public boolean writeIn() {
        return false;
    }

    @Nullable
    @Override
    public JSONObject toJson() {
        return null;
    }
}
