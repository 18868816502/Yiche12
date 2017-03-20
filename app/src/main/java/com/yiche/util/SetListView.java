package com.yiche.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yiche.sortlistview.SortAdapter;

public class SetListView {
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		SortAdapter listAdapter = (SortAdapter) listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))+150;
		listView.setLayoutParams(params);
	}
}
