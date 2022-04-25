package com.mobileproject.mobileprojectqr.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileproject.mobileprojectqr.R;
import com.mobileproject.mobileprojectqr.database.HistoryItem;
import com.mobileproject.mobileprojectqr.databinding.ItemHistoryCodeBinding;
import com.mobileproject.mobileprojectqr.ui.viewmodel.HistoryItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {

    private Context context;
    private List<HistoryItem> historyEntries;

    public HistoryAdapter(Context context) {
        this.context = context;
        this.historyEntries = new ArrayList<>();

        setHasStableIds(true);
    }

    public void setHistoryEntries(List<HistoryItem> entries) {
        historyEntries.clear();
        historyEntries.addAll(entries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemHistoryCodeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_history_code,
                viewGroup,
                false
        );
        return new HistoryItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, int i) {
        ItemHistoryCodeBinding binding = historyItemViewHolder.binding;
        HistoryItemViewModel viewModel = new HistoryItemViewModel(context, historyEntries.get(i));
        binding.setViewModel(viewModel);
        binding.itemView.setOnClickListener(viewModel.onClickItem());
        binding.itemView.setOnLongClickListener(viewModel.onLongClickItem());

    }

    @Override
    public int getItemCount() {
        return historyEntries.size();
    }

    @Override
    public long getItemId(int i) {
        return historyEntries.get(i).get_id();
    }

    public static class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryCodeBinding binding;

        public HistoryItemViewHolder(@NonNull ItemHistoryCodeBinding binding) {
            super(binding.itemView);
            this.binding = binding;
        }
    }
}
