package com.example.lll.gdsappproject.contract.hierarchy;

import com.example.lll.gdsappproject.base.presenter.AbstractPresenter;
import com.example.lll.gdsappproject.base.view.AbstractView;
import com.example.lll.gdsappproject.core.bean.hierarchy.KnowledgeHierarchyData;

import java.util.List;



/**
 * @author quchao
 * @date 2017/12/7
 */

public interface KnowledgeHierarchyContract {

    interface View extends AbstractView {

        /**
         * Show Knowledge Hierarchy Data
         *
         * @param knowledgeHierarchyDataList (List<KnowledgeHierarchyData>
         */
        void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         *
         * @param isShowError If show error
         */
        void getKnowledgeHierarchyData(boolean isShowError);
    }
}
