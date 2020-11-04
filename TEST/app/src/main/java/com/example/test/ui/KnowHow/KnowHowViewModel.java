package com.example.test.ui.KnowHow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KnowHowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KnowHowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is KnowHow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
