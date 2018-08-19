import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmanm2.firebaseui.DataModel.menuModel;
import com.example.rahmanm2.firebaseui.MenuDataModel;
import com.example.rahmanm2.firebaseui.R;

import java.util.List;

public class MenuModelAdapter extends RecyclerView.Adapter<MenuModelAdapter.menuViewHolder>{

    private List<MenuDataModel>mMenuDataModels;
    //Context ctx;
    LayoutInflater mLayoutInflater;

    public MenuModelAdapter(Context context, List<MenuDataModel>model){
        this.mMenuDataModels = model;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public menuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull menuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class menuViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        MenuDataModel current;
        int position;
        public menuViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.diff_Layout_TextID);

            mImageView = (ImageView)itemView.findViewById(R.id.diff_layout_ImageID);

        }

        public void setData(MenuDataModel current, int position) {
            this.mTextView.setText(current.getTitle());
            this.mImageView.setImageResource(current.getImageurl());
            this.position = position;
            this.current = current;
        }
    }
}
