package rusha.x.basket;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rusha.x.databinding.BasketItemBinding;

public class BasketListAdapter extends RecyclerView.Adapter<BasketListAdapter.ItemViewHolder> {

    private BasketViewModel viewModel;
    public BasketListAdapter(BasketViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private List<BasketItem> itemsToAdopt = new ArrayList<>();

    public void setItemsToAdopt(List<BasketItem> items) {
        itemsToAdopt = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsToAdopt.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BasketItemBinding binding = BasketItemBinding.inflate(inflater, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BasketListAdapter.ItemViewHolder holder, int position) {
        BasketItem itemOnPosition = itemsToAdopt.get(position);
        holder.bind(itemOnPosition);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public void bind(BasketItem item) {
            binding.getRoot().setOnClickListener ( v -> {
                viewModel.onBasketItemClick(item);
            });
            binding.basketItemView.setText(item.product.name);
            binding.basketCountView.setText(String.valueOf(item.count));
        }
        private BasketItemBinding binding;
        public ItemViewHolder(BasketItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}