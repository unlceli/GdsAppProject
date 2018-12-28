package com.example.lll.gdsappproject.contract.main;

import com.example.lll.gdsappproject.base.presenter.AbstractPresenter;
import com.example.lll.gdsappproject.base.view.AbstractView;
import com.example.lll.gdsappproject.core.bean.main.search.TopSearchData;
import com.example.lll.gdsappproject.core.dao.HistoryData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/12
 */

public interface SearchContract {

    interface View extends AbstractView {

        /**
         * Show history data
         *
         * @param historyDataList List<HistoryData>
         */
        void showHistoryData(List<HistoryData> historyDataList);

        /**
         * Show top search data
         *
         * @param topSearchDataList List<TopSearchData>
         */
        void showTopSearchData(List<TopSearchData> topSearchDataList);

        /**
         * Judge to the search list activity
         */
        void judgeToTheSearchListActivity();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Load all history data
         *
         * @return all history data
         */
        List<HistoryData> loadAllHistoryData();

        /**
         * Add history data
         *
         * @param data history data
         */
        void addHistoryData(String data);

        /**
         * 热搜
         */
        void getTopSearchData();

        /**
         * Clear history data
         */
        void clearHistoryData();
    }

}
