package gr.anggoro.mangaboo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class DrawerAdapter extends ArrayAdapter<NavItemModel> {
    Context mContext;
    int layoutResourceId;
    NavItemModel[] items = null;

    public DrawerAdapter(Context mContext, int layoutResourceId, NavItemModel[] items){
        super(mContext, layoutResourceId, items);
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView textView = listItem.findViewById((R.id.textViewName));

        NavItemModel folder = items[position];
        textView.setText(folder.name);

        return listItem;
    }
}
