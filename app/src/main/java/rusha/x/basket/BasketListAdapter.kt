package rusha.x.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.basket_item.view.*
import rusha.x.R

class BasketListAdapter(
    private val viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketListAdapter.ItemViewHolder>() {

    var itemsToAdopt: List<Basket.Item> = listOf()
    override fun getItemCount(): Int {
        return itemsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BasketListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context) // была опечатка. LayoutInfla-y-ter
        val view = inflater.inflate(
            R.layout.basket_item,
            parent,
            false
        )
        return ItemViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: BasketListAdapter.ItemViewHolder, position: Int) {
        val itemOnPosition = itemsToAdopt.get(index = position)
        holder.bind(item = itemOnPosition)
    }

    inner class ItemViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        fun bind(item: Basket.Item) {
            containerView.setOnClickListener {
                viewModel.onBasketItemClick(item)
            }
            containerView.basketItemView.text = item.product.name
            containerView.basketCountView.text = item.count.toString()
        }
    }
}