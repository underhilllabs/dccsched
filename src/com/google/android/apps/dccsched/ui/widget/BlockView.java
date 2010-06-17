/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.dccsched.ui.widget;

import com.google.android.apps.dccsched.R;
import com.google.android.apps.dccsched.provider.ScheduleContract.Blocks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.widget.Button;

/**
 * Custom view that represents a {@link Blocks#BLOCK_ID} instance, including its
 * title and time span that it occupies. Usually organized automatically by
 * {@link BlocksLayout} to match up against a {@link TimeRulerView} instance.
 */
public class BlockView extends Button {
    private final String mBlockId;
    private final String mTitle;
    private final long mStartTime;
    private final long mEndTime;
    private final boolean mContainsStarred;
    private final int mColumn;

    public BlockView(Context context, String blockId, String title, long startTime,
            long endTime, boolean containsStarred, int column) {
        super(context);

        mBlockId = blockId;
        mTitle = title;
        mStartTime = startTime;
        mEndTime = endTime;
        mContainsStarred = containsStarred;
        mColumn = column;

        setText(mTitle);

        // TODO: turn into color state list with layers?
        int textColor = -1;
        int accentColor = -1;
        switch (mColumn) {
            case 0:
                // blue
                textColor = Color.WHITE;
                accentColor = Color.parseColor("#18b6e6");
                break;
            case 1:
                // red
                textColor = Color.WHITE;
                accentColor = Color.parseColor("#df1831");
                break;
            case 2:
                // green
                textColor = Color.WHITE;
                accentColor = Color.parseColor("#00a549");
                break;
        }

        LayerDrawable buttonDrawable = (LayerDrawable)
                context.getResources().getDrawable(R.drawable.btn_block);
        buttonDrawable.getDrawable(0).setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);
        buttonDrawable.getDrawable(1).setAlpha(mContainsStarred ? 255 : 0);

        setTextColor(textColor);
        setBackgroundDrawable(buttonDrawable);
    }

    public String getBlockId() {
        return mBlockId;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public int getColumn() {
        return mColumn;
    }
}
