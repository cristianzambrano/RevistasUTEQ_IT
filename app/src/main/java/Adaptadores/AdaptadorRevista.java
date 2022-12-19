package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import models.Revista;
import uteq.solutions.revistasuteq_it.R;

public class AdaptadorRevista extends ArrayAdapter<Revista> {

    public AdaptadorRevista(Context context, ArrayList<Revista> datos) {
        super(context, R.layout.lyitemrevista, datos);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemrevista, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(getItem(position).name);

        ImageView imageView = (ImageView)item.findViewById(R.id.imgUsr);
        Glide.with(this.getContext())
                .load(getItem(position).portada)
                .into(imageView);


        return(item);
    }
}
