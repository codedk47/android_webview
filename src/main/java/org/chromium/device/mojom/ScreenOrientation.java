// ScreenOrientation.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/mojom/screen_orientation.mojom
//

package org.chromium.device.mojom;

import androidx.annotation.IntDef;


public interface ScreenOrientation extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends ScreenOrientation, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<ScreenOrientation, ScreenOrientation.Proxy> MANAGER = ScreenOrientation_Internal.MANAGER;


    void lockOrientation(
int orientation, 
LockOrientationResponse callback);

    interface LockOrientationResponse extends org.chromium.mojo.bindings.Callbacks.Callback1<Integer> { }



    void unlockOrientation(
);


}
