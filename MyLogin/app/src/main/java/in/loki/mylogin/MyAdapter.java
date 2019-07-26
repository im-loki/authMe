package in.loki.mylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Users> user;
    private Context context;

    public MyAdapter(List<Users> user, Context context) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_veiw2,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.Gname.setText(user.get(position).getName());
        holder.Date.setText("Date:    "+user.get(position).getDate());
        if(user.get(position).getAck()==0)
            holder.Action.setText("Action:"+"    Accepted");
        else if(user.get(position).getAck()==2)
            holder.Action.setText("Action:"+"     Rejected");
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Gname,Date,Action;
        public ViewHolder(View view) {
            super(view);
            Gname = (TextView) view.findViewById(R.id.gname);
            Date = view.findViewById(R.id.date);
            Action = view.findViewById(R.id.action);
        }
    }
}
