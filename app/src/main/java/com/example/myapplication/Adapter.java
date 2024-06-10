package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    private List<ModelClass> userList;

    public Adapter(List<ModelClass>userList){

        this.userList=userList;

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String t=userList.get(position).getTitle();
        String dNt=userList.get(position).getDateNtime();
        String dsc=userList.get(position).getDesc();
        String ty=userList.get(position).getType();
        String ti=userList.get(position).getTime();


        holder.setData(t,dNt,dsc,ty,ti);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog(v.getContext(),v,t);



            }
        });




    }
    void confirmDialog(Context context,View v,String t){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete the entry?");
        builder.setMessage("Are you sure you want ot delete the entry?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDb=new MyDatabaseHelper(v.getContext());
                Cursor cursor=myDb.getReadableDatabase().rawQuery("Select * from Second where Title=?",new String[]{t});
                cursor.moveToFirst();
                System.out.println("htg4");
                System.out.println(cursor.getCount());
                System.out.println(cursor.getString(0));
                myDb.delete(cursor.getString(0));
                Intent intent=new Intent(v.getContext(),MainActivity.class);
                v.getContext().startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView desc;
        private TextView dateNtime;
        private RelativeLayout layout;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desc);
            dateNtime=itemView.findViewById(R.id.dateNtime);
            time=itemView.findViewById(R.id.time);
            layout=itemView.findViewById(R.id.layout9);


        }

        public void setData(String t, String dNt, String dsc, String ty,String ti) {
            title.setText(t);
            dateNtime.setText(dNt);
            desc.setText(dsc);
            time.setText(ti);
        }
    }
}
