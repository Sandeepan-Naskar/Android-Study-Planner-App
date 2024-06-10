package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userlist;
    Adapter adaptr;
    View view;

    MyDatabaseHelper myDB;
    ArrayList<String> ids,titles,descs,dates,types;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB=new MyDatabaseHelper(this.getContext());
        ids=new ArrayList<>();
        titles=new ArrayList<>();
        descs=new ArrayList<>();
        dates=new ArrayList<>();
        types=new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initData();
        view=inflater.inflate(R.layout.fragment_first, container, false);
        initRecyclerView();
        return view;    }
    private void initData() {
        Cursor cursor=myDB.readAllData();
        userlist=new ArrayList<>();
        if(cursor.getCount()==0){
            Toast.makeText(this.getContext(),"No data.",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                if(cursor.getString(1).equals("Exam")) {
                    userlist.add(new ModelClass(
                            cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(1),cursor.getString(5)
                    ));
                }
            }

        }



        System.out.println(userlist);
    }

    private void initRecyclerView() {
        Log.d("layx","FU");

        recyclerView=view.findViewById(R.id.rv1);
        Log.d("layx","FUck");
        layoutManager=new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        System.out.println(layoutManager.getOrientation());
        Log.d("layx","FUc");
        recyclerView.setLayoutManager(layoutManager);

        System.out.println(userlist);

        adaptr=new Adapter(userlist);
        System.out.println(adaptr.getItemCount());
        recyclerView.setAdapter(adaptr);
        adaptr.notifyDataSetChanged();

    }
}