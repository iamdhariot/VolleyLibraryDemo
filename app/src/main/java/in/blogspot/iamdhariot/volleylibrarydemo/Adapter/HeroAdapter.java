package in.blogspot.iamdhariot.volleylibrarydemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import in.blogspot.iamdhariot.volleylibrarydemo.Model.Hero;
import in.blogspot.iamdhariot.volleylibrarydemo.R;

public class HeroAdapter extends ArrayAdapter<Hero> {
    // hero list
    private List<Hero> heroList;
    // context
    private Context context;
    //initializing the heroList and context
    // when we create the object oh this HeroAdapter class. We will give context and heroList
    public HeroAdapter(@NonNull Context context, List<Hero> heroList) {
        super(context, R.layout.hero_list_item, heroList);
        this.heroList = heroList;
        this.context = context;
    }

    // this method will return the list item

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // layoutInflater to inflate the custom list item
        View heroItemView = LayoutInflater.from(context).inflate(R.layout.hero_list_item,null,true);
        // getting textViews
        TextView textViewName,textViewImageUrl;
        textViewName = heroItemView.findViewById(R.id.text_view_name);
        textViewImageUrl = heroItemView.findViewById(R.id.text_view_image_url);

        //Getting the hero for specific position
        Hero hero = heroList.get(position);

        // Setting the hero values to the textViews
        textViewName.setText(hero.getName());
        textViewImageUrl.setText(hero.getImageUrl());
        // returning the listItem
        return heroItemView;
    }
}
