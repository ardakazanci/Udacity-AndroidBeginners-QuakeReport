/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.flavor;

/**
 * {@link AndroidFlavor} represents a single Android platform release.
 * Each object has 3 properties: name, version number, and image resource ID.
 */
public class AndroidFlavor {


    private String mVersionName;


    private String mVersionNumber;

    // Image için ID Değeri
    private int mImageResourceId;


    public AndroidFlavor(String vName, String vNumber, int imageResourceId)
    {
        mVersionName = vName;
        mVersionNumber = vNumber;
        mImageResourceId = imageResourceId;
    }


    public String getVersionName() {
        return mVersionName;
    }


    public String getVersionNumber() {
        return mVersionNumber;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }


}
