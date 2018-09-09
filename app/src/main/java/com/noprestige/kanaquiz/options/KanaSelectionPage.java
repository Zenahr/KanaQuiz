package com.noprestige.kanaquiz.options;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static com.noprestige.kanaquiz.questions.QuestionManagement.HIRAGANA;
import static com.noprestige.kanaquiz.questions.QuestionManagement.KATAKANA;

public class KanaSelectionPage extends Fragment
{
    private static final String ARG_PAGE_NUMBER = "position";
    private static final String ARG_ITEM_STATES = "states";
    LinearLayout screen;

    public static KanaSelectionPage newInstance(int id)
    {
        KanaSelectionPage screen = new KanaSelectionPage();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, id);
        screen.setArguments(args);
        return screen;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        switch (getArguments().getInt(ARG_PAGE_NUMBER, -1))
        {
            case 0:
                screen = HIRAGANA.getSelectionScreen(getContext());
                break;
            case 1:
                screen = KATAKANA.getSelectionScreen(getContext());
                break;
            default:
                screen = null;
        }
        ScrollView scrollView = new ScrollView(getContext());
        scrollView.addView(screen);
        return scrollView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //This block of code could also work in the onResume method
        boolean[] record = getArguments().getBooleanArray(ARG_ITEM_STATES);
        if (record != null)
        {
            int count = Math.min(screen.getChildCount(), record.length);

            for (int i = 0; i < count; i++)
                ((Checkable) screen.getChildAt(i)).setChecked(record[i]);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        //This block of code could also work in the onPause and onStop methods
        int count = screen.getChildCount();
        boolean[] record = new boolean[count];

        for (int i = 0; i < count; i++)
            record[i] = ((Checkable) screen.getChildAt(i)).isChecked();

        getArguments().putBooleanArray(ARG_ITEM_STATES, record);
    }
}
