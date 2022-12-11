package com.jjasan2.project_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private Context context;
    private List<String> animal_names;
    private List<Integer> animal_images;
    private List<String> animal_web;
    private boolean isList = false;

    public RVAdapter(Context context) {
        this.context = context;
        this.animal_names = Arrays.asList(context.getResources().getStringArray(R.array.animal_names));
        this.animal_web = Arrays.asList(context.getResources().getStringArray(R.array.animal_web));
        this.animal_images = Arrays.asList(R.drawable._1_elephant, R.drawable._2_bear, R.drawable._3_yak,
                R.drawable._4_tiger, R.drawable._5_giraffe, R.drawable._6_leopard, R.drawable._7_zebra,
                R.drawable._8_lion);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        // Create inflater with the layout that has to be inflated
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        // view type is set as 1 for list view in getItemViewType()
        if(viewType == 1) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        else {
            view = inflater.inflate(R.layout.grid_item, parent, false);
        }

        // create ViewHolder passing the view that it will wrap

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(animal_names.get(position));
        holder.image.setImageResource(animal_images.get(position));
    }

    @Override
    public int getItemCount() {
        return animal_names.size();
    }

    // To get the view type for each item in recycle view
    @Override
    public int getItemViewType (int position) {
        if (isList){
            return 1;
        }else{
            return 0;
        }
    }

    // To set the recycle view to use based on the menu option
    public void setListView(boolean isListView){
        isList = isListView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener, View.OnClickListener {

        public TextView name;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // To listen to clicks
            itemView.setOnClickListener(this);
            // To listen to long clicks
            itemView.setOnCreateContextMenuListener(this);

            name = itemView.findViewById(R.id.name_id);
            image = itemView.findViewById(R.id.image_id);

            image.setLayoutParams(new GridView.LayoutParams(300, 300));
            image.setPadding(8, 8, 8, 8);
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        // To create the context menu
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);
            // To set listener for each item in menu
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
        }

        // To handle on click of the context menu
        private final MenuItem.OnMenuItemClickListener onMenu = item -> {
            if(item.getTitleCondensed().equals("browser"))
                openRelatedWebpage(getAdapterPosition());
            else
                openImgFullscreen(getAdapterPosition());
            return true;
        };

        // Open the image in a fullscreen activity on click
        @Override
        public void onClick(View v) {
            openRelatedWebpage(getAdapterPosition());
        }

        private void openRelatedWebpage(int index){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(animal_web.get(index)));
            context.startActivity(webIntent);
        }

        // Open the image in a fullscreen activity
        private void openImgFullscreen(int index){
            Intent imgIntent = new Intent(context, ViewImage.class);
            imgIntent.putExtra("imgSource", animal_images.get(index));
            context.startActivity(imgIntent);
        }
    }
}
