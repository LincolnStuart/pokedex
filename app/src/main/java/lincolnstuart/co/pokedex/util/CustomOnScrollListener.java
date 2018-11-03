package lincolnstuart.co.pokedex.util;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomOnScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;

    public CustomOnScrollListener(LinearLayoutManager layoutManager, FloatingActionButton fab) {
        this.mLayoutManager = layoutManager;
        this.fab = fab;
    }

    public CustomOnScrollListener(StaggeredGridLayoutManager layoutManager, FloatingActionButton fab) {
        this.mLayoutManager = layoutManager;
        this.fab = fab;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        if (dy > 0) {
            int lastVisibleItemPosition = 0;
            int totalItemCount = mLayoutManager.getItemCount();

            if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
            } else if (mLayoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            } else if (mLayoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            }
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }
            if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++;
                onLoadMore(currentPage, totalItemCount, view);
                loading = true;
            }
        }
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (mLayoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPosition() != 0) {
                fab.show();
            } else {
                fab.hide();
            }

        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            final int[] aux = ((StaggeredGridLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPositions(null);
            List<Integer> firstPositions = new ArrayList<Integer>() {{
                for (int i : aux) add(i);
            }};
            if (!firstPositions.contains(0)) {
                fab.show();
            } else {
                fab.hide();
            }
        }

    }
}