package com.ekuater.labelchat.ui.fragment.mixdynamic;

import android.content.Context;
import android.os.Bundle;

import com.ekuater.labelchat.datastruct.mixdynamic.DynamicWrapper;
import com.ekuater.labelchat.delegate.MixDynamicManager;

/**
 * Created by Leo on 2015/4/21.
 *
 * @author LinYong
 */
class RelatedConfig extends DynamicConfig {

    public RelatedConfig(Context context, Bundle args) {
        super(context, args);
    }

    @Override
    public boolean monitorSentDynamic() {
        return true;
    }

    @Override
    public boolean loadComments() {
        return true;
    }


    public void queryMixDynamic(int requestTime, final QueryListener listener) {
        dynamicManager.queryRelatedDynamic(
                String.valueOf(requestTime),
                new MixDynamicManager.DynamicObserver() {
                    @Override
                    public void onQueryResult(int result, DynamicWrapper[] wrappers) {
                        listener.onQueryResult(result, wrappers);
                    }
                });
    }
}
