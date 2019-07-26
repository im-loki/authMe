package in.loki.mylogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {
    private List<Users> user1;
    private Context context;

    public MyAdapter1(List<Users> user, Context context) {
        this.user1 = user;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {//
        View v = LayoutInflater.from(context).inflate(R.layout.card_view1,parent,false);
        final ViewHolder viewHolder = new ViewHolder(v);

        viewHolder.item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Test"+viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "  "+user1.get(viewHolder.getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                String e_id=user1.get(viewHolder.getAdapterPosition()).getId();

                Intent intent = new Intent(context, Main3Activity.class);
                intent.putExtra("E_POST_KEY", e_id);
                context.startActivity(intent);
            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter1.ViewHolder holder, int position) {
        holder.Gname1.setText(user1.get(position).getName());
        holder.Date1.setText("Date:   "+user1.get(position).getDate());
        if(user1.get(position).getAck()==1)
            holder.Action1.setText("Action:"+"    Awaiting");
    }

    @Override
    public int getItemCount() {
        return user1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Gname1,Date1,Action1;
        public LinearLayout item_view;
        public ViewHolder(View view) {
            super(view);
            item_view = view.findViewById(R.id.cardView);
            Gname1 = (TextView) view.findViewById(R.id.gname1);
            Date1 = view.findViewById(R.id.date1);
            Action1 = view.findViewById(R.id.action1);
        }
    }
}
