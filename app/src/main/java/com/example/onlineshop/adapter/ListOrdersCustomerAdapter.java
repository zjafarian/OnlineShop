package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.LineItem;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.RowItemOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersCustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Order> mOrderList = new ArrayList<>();
    private List<Products> mProductsList = new ArrayList<>();
    private List<Products> mProductsOrder = new ArrayList<>();
    private OnItemClickOrder mOnItemClickOrder;

    public ListOrdersCustomerAdapter() {
    }

    public void setData(List<Order> orders, List<Products> products) {
        mOrderList = orders;
        mProductsList = products;
        notifyDataSetChanged();
    }

    public void setOnItemClickOrder(OnItemClickOrder onItemClickOrder) {
        this.mOnItemClickOrder = onItemClickOrder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowItemOrderBinding itemOrderBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.row_item_order,
                parent,
                false);

        return new ListOrderCustomerHolder(itemOrderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order order = mOrderList.get(position);
        ((ListOrderCustomerHolder) holder).bindHolder(order);

        if (mOnItemClickOrder != null)
            ((ListOrderCustomerHolder) holder).itemView.
                    findViewById(R.id.img_view_all_products_shopping)
                    .setOnClickListener(v -> mOnItemClickOrder.onItemClicked(mProductsOrder,order.getId()));

    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    class ListOrderCustomerHolder extends RecyclerView.ViewHolder {
        private RowItemOrderBinding mBinding;


        public ListOrderCustomerHolder(RowItemOrderBinding itemOrderBinding) {
            super(itemOrderBinding.getRoot());
            mBinding = itemOrderBinding;

        }

        public void bindHolder(Order order) {
            mBinding.setOrder(order);
            mBinding.executePendingBindings();
            List<LineItem> lineItems = order.getLineItems();
            for (int i = 0; i < lineItems.size(); i++) {
                for (Products productOrder : mProductsList) {
                    if (productOrder.getId() == lineItems.get(i).getProductId())
                        mProductsOrder.add(productOrder);
                }
            }

        }
    }

    public interface OnItemClickOrder {
        void onItemClicked(List<Products> products, int orderId);
    }
}
