// Blob.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     third_party/blink/public/mojom/blob/blob.mojom
//

package org.chromium.blink.mojom;

import androidx.annotation.IntDef;


public interface Blob extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends Blob, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<Blob, Blob.Proxy> MANAGER = Blob_Internal.MANAGER;


    void clone(
org.chromium.mojo.bindings.InterfaceRequest<Blob> blob);



    void asDataPipeGetter(
org.chromium.mojo.bindings.InterfaceRequest<org.chromium.network.mojom.DataPipeGetter> dataPipeGetter);



    void readAll(
org.chromium.mojo.system.DataPipe.ProducerHandle pipe, BlobReaderClient client);



    void readRange(
long offset, long length, org.chromium.mojo.system.DataPipe.ProducerHandle pipe, BlobReaderClient client);



    void load(
org.chromium.mojo.bindings.InterfaceRequest<org.chromium.network.mojom.UrlLoader> loader, String requestMethod, org.chromium.network.mojom.HttpRequestHeaders headers, org.chromium.network.mojom.UrlLoaderClient client);



    void readSideData(

ReadSideDataResponse callback);

    interface ReadSideDataResponse extends org.chromium.mojo.bindings.Callbacks.Callback1<org.chromium.mojo_base.mojom.BigBuffer> { }



    void captureSnapshot(

CaptureSnapshotResponse callback);

    interface CaptureSnapshotResponse extends org.chromium.mojo.bindings.Callbacks.Callback2<Long, org.chromium.mojo_base.mojom.Time> { }



    void getInternalUuid(

GetInternalUuidResponse callback);

    interface GetInternalUuidResponse extends org.chromium.mojo.bindings.Callbacks.Callback1<String> { }


}