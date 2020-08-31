/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
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

package rx.android.samples.fragment;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import mobctrl.model.ItemData;

public class CodeFragmentManager {

    private static final String ARG_POSITION = "position";
    private int position;
    private ImageView iv_img;


    public static Fragment newInstance(int position, ItemData itemData) {
        switch (position) {
            case 0:
                return new Fragment_Web();
            case 1:
                try {
                    return (Fragment) Class.forName(itemData.clazz).newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                return new Fragment_Content();
        }
        return new Fragment();
    }

    ;




}