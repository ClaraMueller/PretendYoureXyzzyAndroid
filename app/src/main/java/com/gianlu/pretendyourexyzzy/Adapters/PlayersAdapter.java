package com.gianlu.pretendyourexyzzy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gianlu.commonutils.SuperTextView;
import com.gianlu.pretendyourexyzzy.NetIO.Models.GameInfo;
import com.gianlu.pretendyourexyzzy.R;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private final List<GameInfo.Player> players;
    private final LayoutInflater inflater;

    public PlayersAdapter(Context context, List<GameInfo.Player> players) {
        this.inflater = LayoutInflater.from(context);
        this.players = players;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return players.get(position).name.hashCode();
    }

    public List<GameInfo.Player> getPlayers() {
        return players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameInfo.Player player = players.get(position);
        holder.name.setText(player.name);
        holder.update(player);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            holder.update((GameInfo.Player) payloads.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void notifyItemChanged(GameInfo.Player player) {
        int pos = players.indexOf(player);
        if (pos != -1) {
            players.set(pos, player);
            notifyItemChanged(pos, player);
        }
    }

    public void notifyDataSetChanged(List<GameInfo.Player> players) {
        this.players.clear();
        this.players.addAll(players);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final SuperTextView score;
        final ImageView status;

        public ViewHolder(ViewGroup parent) {
            super(inflater.inflate(R.layout.player_item, parent, false));
            setIsRecyclable(false);

            name = (TextView) itemView.findViewById(R.id.playerItem_name);
            score = (SuperTextView) itemView.findViewById(R.id.playerItem_score);
            status = (ImageView) itemView.findViewById(R.id.playerItem_status);
        }

        public void update(GameInfo.Player player) {
            score.setHtml(R.string.score, player.score);

            switch (player.status) {
                case HOST:
                    status.setImageResource(R.drawable.ic_person_black_48dp);
                    break;
                case IDLE:
                    status.setImageResource(R.drawable.ic_access_time_black_48dp);
                    break;
                case JUDGING:
                case JUDGE:
                    status.setImageResource(R.drawable.ic_gavel_black_48dp);
                    break;
                case PLAYING:
                    status.setImageResource(R.drawable.ic_hourglass_empty_black_48dp);
                    break;
                case WINNER:
                    status.setImageResource(R.drawable.ic_star_black_48dp);
                    break;
                case SPECTATOR:
                    status.setImageResource(R.drawable.ic_remove_red_eye_black_48dp);
                    break;
            }
        }
    }
}