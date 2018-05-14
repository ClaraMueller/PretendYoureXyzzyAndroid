package com.gianlu.pretendyourexyzzy.Cards;

import android.support.annotation.NonNull;

import com.gianlu.pretendyourexyzzy.NetIO.Models.BaseCard;
import com.gianlu.pretendyourexyzzy.NetIO.Models.Card;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CardsGroup extends ArrayList<BaseCard> {

    public CardsGroup(JSONArray array) throws JSONException {
        for (int j = 0; j < array.length(); j++)
            add(new Card(array.getJSONObject(j)));
    }

    private CardsGroup() {
    }

    @NonNull
    public static CardsGroup singleton(@NonNull BaseCard card) {
        CardsGroup group = new CardsGroup();
        group.add(card);
        return group;
    }

    @NonNull
    public static List<CardsGroup> list(@NonNull JSONArray array) throws JSONException {
        List<CardsGroup> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) list.add(new CardsGroup(array.getJSONArray(i)));
        return list;
    }

    public boolean hasCard(int id) {
        for (BaseCard card : this)
            if (card.id() == id)
                return true;

        return false;
    }

    public void setWinner() {
        for (BaseCard card : this)
            if (card instanceof Card)
                ((Card) card).setWinner();
    }
}
