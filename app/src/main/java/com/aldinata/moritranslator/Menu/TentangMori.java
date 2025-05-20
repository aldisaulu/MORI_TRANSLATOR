package com.aldinata.moritranslator.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldinata.moritranslator.Login.Login;
import com.aldinata.moritranslator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TentangMori#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TentangMori extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "myPref";
    private static final String KEY_NAME = "name";

    private TextView tvNama, tvLogout;
    private ImageView ivLogout;

    public TentangMori() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TentangMori.
     */
    // TODO: Rename and change types and number of parameters
    public static TentangMori newInstance(String param1, String param2) {
        TentangMori fragment = new TentangMori();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tentang_mori, container, false);

        tvNama = rootView.findViewById(R.id.tvNama);
        tvLogout = rootView.findViewById(R.id.tvLogout);
        ivLogout = rootView.findViewById(R.id.ivLogout);

        //shared Preferences
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME,getActivity().MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name != null){
            tvNama.setText("Halo, " + name);
        }

        //loguout
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("hasLoggedIn",false);
                editor.apply();

                Intent keluar = new Intent(getActivity().getApplicationContext(), Login.class);
                startActivity(keluar);
                getActivity().finish();

            }
        });

        return rootView;
    }
}