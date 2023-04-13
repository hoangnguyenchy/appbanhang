package com.example.appbanhang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.Database.HanghoaDAO;
import com.example.appbanhang.Model.Hanghoa;
import com.example.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class hanghoaAdapter extends BaseAdapter {
    List<Hanghoa> arrSach;
    List<Hanghoa> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    HanghoaDAO hanghoaDAO;

    public hanghoaAdapter(Activity context, List<Hanghoa> arrayHanghoa) {
        super();
        this.context = context;
        this.arrSach = arrayHanghoa;
        this.arrSortSach = arrayHanghoa;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hanghoaDAO = new HanghoaDAO(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_hanghoa, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtBookName = (TextView) convertView.findViewById(R.id.tvBookName);
            holder.txtBookPrice = (TextView) convertView.findViewById(R.id.tvBookPrice);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hanghoaDAO.deleteSachByID(arrSach.get(position).getMaHangHoa());
                    arrSach.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();
        Hanghoa _entry = (Hanghoa) arrSach.get(position);
        holder.img.setImageResource(R.drawable.sach);
        holder.txtBookName.setText("Mã hàng hóa: " + _entry.getMaHangHoa());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        holder.txtBookPrice.setText("Giá: " + _entry.getGiaBia() + "");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Hanghoa> items) {
        this.arrSach = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSach = arrSortSach;
    }


    public Filter getFilter() {
        if (sachFilter == null) sachFilter = new CustomFilter();
        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // triển khai dữ liệu
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSach;
                results.count = arrSortSach.size();
            } else {
                List<Hanghoa> lsHangHoa = new ArrayList<Hanghoa>();
                for (Hanghoa p : arrSach) {
                    if (p.getMaHangHoa().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsHangHoa.add(p);
                }
                results.values = lsHangHoa;
                results.count = lsHangHoa.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) notifyDataSetInvalidated();
            else {
                arrSach = (List<Hanghoa>) results.values;
                notifyDataSetChanged();
            }
        }

    }}

