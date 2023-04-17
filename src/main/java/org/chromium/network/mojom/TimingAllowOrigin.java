// TimingAllowOrigin.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/network/public/mojom/timing_allow_origin.mojom
//

package org.chromium.network.mojom;

import androidx.annotation.IntDef;


public final class TimingAllowOrigin extends org.chromium.mojo.bindings.Union {

    public static final class Tag {
        public static final int SerializedOrigins = 0;
        public static final int All = 1;
    };
    private String[] mSerializedOrigins;
    private byte mAll;

    public void setSerializedOrigins(String[] serializedOrigins) {
        this.mTag = Tag.SerializedOrigins;
        this.mSerializedOrigins = serializedOrigins;
    }

    public String[] getSerializedOrigins() {
        assert this.mTag == Tag.SerializedOrigins;
        return this.mSerializedOrigins;
    }

    public void setAll(byte all) {
        this.mTag = Tag.All;
        this.mAll = all;
    }

    public byte getAll() {
        assert this.mTag == Tag.All;
        return this.mAll;
    }


    @Override
    protected final void encode(org.chromium.mojo.bindings.Encoder encoder0, int offset) {
        encoder0.encode(org.chromium.mojo.bindings.BindingsHelper.UNION_SIZE, offset);
        encoder0.encode(this.mTag, offset + 4);
        switch (mTag) {
            case Tag.SerializedOrigins: {
                
                if (this.mSerializedOrigins == null) {
                    encoder0.encodeNullPointer(offset + 8, false);
                } else {
                    org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(this.mSerializedOrigins.length, offset + 8, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    for (int i0 = 0; i0 < this.mSerializedOrigins.length; ++i0) {
                        
                        encoder1.encode(this.mSerializedOrigins[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
                    }
                }
                break;
            }
            case Tag.All: {
                
                encoder0.encode(this.mAll, offset + 8);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static TimingAllowOrigin deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message).decoderForSerializedUnion(), 0);
    }

    public static final TimingAllowOrigin decode(org.chromium.mojo.bindings.Decoder decoder0, int offset) {
        org.chromium.mojo.bindings.DataHeader dataHeader = decoder0.readDataHeaderForUnion(offset);
        if (dataHeader.size == 0) {
            return null;
        }
        TimingAllowOrigin result = new TimingAllowOrigin();
        switch (dataHeader.elementsOrVersion) {
            case Tag.SerializedOrigins: {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(offset + org.chromium.mojo.bindings.DataHeader.HEADER_SIZE, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.mSerializedOrigins = new String[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        result.mSerializedOrigins[i1] = decoder1.readString(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                    }
                }
                result.mTag = Tag.SerializedOrigins;
                break;
            }
            case Tag.All: {
                
                result.mAll = decoder0.readByte(offset + org.chromium.mojo.bindings.DataHeader.HEADER_SIZE);
                result.mTag = Tag.All;
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }
}