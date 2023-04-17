// RunInput.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     mojo/public/interfaces/bindings/interface_control_messages.mojom
//

package org.chromium.mojo.bindings.interfacecontrol;

import androidx.annotation.IntDef;


public final class RunInput extends org.chromium.mojo.bindings.Union {

    public static final class Tag {
        public static final int QueryVersion = 0;
        public static final int FlushForTesting = 1;
    };
    private QueryVersion mQueryVersion;
    private FlushForTesting mFlushForTesting;

    public void setQueryVersion(QueryVersion queryVersion) {
        this.mTag = Tag.QueryVersion;
        this.mQueryVersion = queryVersion;
    }

    public QueryVersion getQueryVersion() {
        assert this.mTag == Tag.QueryVersion;
        return this.mQueryVersion;
    }

    public void setFlushForTesting(FlushForTesting flushForTesting) {
        this.mTag = Tag.FlushForTesting;
        this.mFlushForTesting = flushForTesting;
    }

    public FlushForTesting getFlushForTesting() {
        assert this.mTag == Tag.FlushForTesting;
        return this.mFlushForTesting;
    }


    @Override
    protected final void encode(org.chromium.mojo.bindings.Encoder encoder0, int offset) {
        encoder0.encode(org.chromium.mojo.bindings.BindingsHelper.UNION_SIZE, offset);
        encoder0.encode(this.mTag, offset + 4);
        switch (mTag) {
            case Tag.QueryVersion: {
                
                encoder0.encode(this.mQueryVersion, offset + 8, false);
                break;
            }
            case Tag.FlushForTesting: {
                
                encoder0.encode(this.mFlushForTesting, offset + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static RunInput deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message).decoderForSerializedUnion(), 0);
    }

    public static final RunInput decode(org.chromium.mojo.bindings.Decoder decoder0, int offset) {
        org.chromium.mojo.bindings.DataHeader dataHeader = decoder0.readDataHeaderForUnion(offset);
        if (dataHeader.size == 0) {
            return null;
        }
        RunInput result = new RunInput();
        switch (dataHeader.elementsOrVersion) {
            case Tag.QueryVersion: {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(offset + org.chromium.mojo.bindings.DataHeader.HEADER_SIZE, false);
                result.mQueryVersion = QueryVersion.decode(decoder1);
                result.mTag = Tag.QueryVersion;
                break;
            }
            case Tag.FlushForTesting: {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(offset + org.chromium.mojo.bindings.DataHeader.HEADER_SIZE, false);
                result.mFlushForTesting = FlushForTesting.decode(decoder1);
                result.mTag = Tag.FlushForTesting;
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }
}