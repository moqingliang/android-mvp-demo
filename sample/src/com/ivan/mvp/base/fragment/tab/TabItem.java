package com.ivan.mvp.base.fragment.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class TabItem implements TabListener {
	
	protected final Context mContext;
    protected final String mTag;
    protected final Class<?> mClass;
    protected final Bundle mArgs;
    protected Fragment mFragment;

    public TabItem(Context ctx, String mTag, Class<?> mClass) {
        this(ctx, mTag, mClass, null);
    }

    public TabItem(Context ctx, String mTag, Class<?> mClass, Bundle mArgs) {
        this.mContext = ctx;
        this.mTag = mTag;
        this.mClass = mClass;
        this.mArgs = mArgs;
    }

    public String getTag() {
        return mTag;
    }

}
