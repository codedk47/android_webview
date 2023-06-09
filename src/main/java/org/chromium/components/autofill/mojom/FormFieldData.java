// FormFieldData.java is auto generated by mojom_bindings_generator.py, do not edit


// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     components/autofill/core/common/mojom/autofill_types.mojom
//

package org.chromium.autofill.mojom;

import androidx.annotation.IntDef;


public final class FormFieldData extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 200;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(200, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];

    public static final class CheckStatus {
        private static final boolean IS_EXTENSIBLE = false;
        @IntDef({

            CheckStatus.NOT_CHECKABLE,
            CheckStatus.CHECKABLE_BUT_UNCHECKED,
            CheckStatus.CHECKED})
        public @interface EnumType {}

        public static final int NOT_CHECKABLE = 0;
        public static final int CHECKABLE_BUT_UNCHECKED = 1;
        public static final int CHECKED = 2;
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 2;

        public static boolean isKnownValue(int value) {
            return value >= 0 && value <= 2;
        }

        public static void validate(int value) {
            if (IS_EXTENSIBLE || isKnownValue(value)) return;
            throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
        }

        public static int toKnownValue(int value) {
          return value;
        }

        private CheckStatus() {}
    }

    public static final class RoleAttribute {
        private static final boolean IS_EXTENSIBLE = false;
        @IntDef({

            RoleAttribute.PRESENTATION,
            RoleAttribute.OTHER})
        public @interface EnumType {}

        public static final int PRESENTATION = 0;
        public static final int OTHER = 1;
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 1;

        public static boolean isKnownValue(int value) {
            return value >= 0 && value <= 1;
        }

        public static void validate(int value) {
            if (IS_EXTENSIBLE || isKnownValue(value)) return;
            throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
        }

        public static int toKnownValue(int value) {
          return value;
        }

        private RoleAttribute() {}
    }

    public static final class LabelSource {
        private static final boolean IS_EXTENSIBLE = false;
        @IntDef({

            LabelSource.UNKNOWN,
            LabelSource.LABEL_TAG,
            LabelSource.P_TAG,
            LabelSource.DIV_TABLE,
            LabelSource.TD_TAG,
            LabelSource.DD_TAG,
            LabelSource.LI_TAG,
            LabelSource.PLACE_HOLDER,
            LabelSource.ARIA_LABEL,
            LabelSource.COMBINED,
            LabelSource.VALUE})
        public @interface EnumType {}

        public static final int UNKNOWN = 0;
        public static final int LABEL_TAG = 1;
        public static final int P_TAG = 2;
        public static final int DIV_TABLE = 3;
        public static final int TD_TAG = 4;
        public static final int DD_TAG = 5;
        public static final int LI_TAG = 6;
        public static final int PLACE_HOLDER = 7;
        public static final int ARIA_LABEL = 8;
        public static final int COMBINED = 9;
        public static final int VALUE = 10;
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 10;

        public static boolean isKnownValue(int value) {
            return value >= 0 && value <= 10;
        }

        public static void validate(int value) {
            if (IS_EXTENSIBLE || isKnownValue(value)) return;
            throw new org.chromium.mojo.bindings.DeserializationException("Invalid enum value.");
        }

        public static int toKnownValue(int value) {
          return value;
        }

        private LabelSource() {}
    }
    public org.chromium.mojo_base.mojom.String16 label;
    public org.chromium.mojo_base.mojom.String16 name;
    public org.chromium.mojo_base.mojom.String16 idAttribute;
    public org.chromium.mojo_base.mojom.String16 nameAttribute;
    public org.chromium.mojo_base.mojom.String16 value;
    public String formControlType;
    public String autocompleteAttribute;
    public org.chromium.mojo_base.mojom.String16 placeholder;
    public org.chromium.mojo_base.mojom.String16 cssClasses;
    public org.chromium.mojo_base.mojom.String16 ariaLabel;
    public org.chromium.mojo_base.mojom.String16 ariaDescription;
    public FieldRendererId uniqueRendererId;
    public FormRendererId hostFormId;
    public int propertiesMask;
    public int formControlAxId;
    public long maxLength;
    public boolean isAutofilled;
    public String section;
    public int checkStatus;
    public boolean isFocusable;
    public boolean shouldAutocomplete;
    public int role;
    public int textDirection;
    public boolean isEnabled;
    public boolean isReadonly;
    public org.chromium.mojo_base.mojom.String16 userInput;
    public SelectOption[] options;
    public int labelSource;
    public org.chromium.gfx.mojom.RectF bounds;
    public org.chromium.mojo_base.mojom.String16[] datalistValues;
    public org.chromium.mojo_base.mojom.String16[] datalistLabels;

    private FormFieldData(int version) {
        super(STRUCT_SIZE, version);
    }

    public FormFieldData() {
        this(0);
    }

    public static FormFieldData deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static FormFieldData deserialize(java.nio.ByteBuffer data) {
        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static FormFieldData decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        FormFieldData result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            final int elementsOrVersion = mainDataHeader.elementsOrVersion;
            result = new FormFieldData(elementsOrVersion);
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                result.label = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(16, false);
                result.name = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(24, false);
                result.idAttribute = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(32, false);
                result.nameAttribute = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(40, false);
                result.value = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                result.formControlType = decoder0.readString(48, false);
                }
                {
                    
                result.autocompleteAttribute = decoder0.readString(56, false);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(64, false);
                result.placeholder = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(72, false);
                result.cssClasses = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(80, false);
                result.ariaLabel = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(88, false);
                result.ariaDescription = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(96, false);
                result.uniqueRendererId = FieldRendererId.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(104, false);
                result.hostFormId = FormRendererId.decode(decoder1);
                }
                {
                    
                result.propertiesMask = decoder0.readInt(112);
                }
                {
                    
                result.formControlAxId = decoder0.readInt(116);
                }
                {
                    
                result.maxLength = decoder0.readLong(120);
                }
                {
                    
                result.isAutofilled = decoder0.readBoolean(128, 0);
                }
                {
                    
                result.isFocusable = decoder0.readBoolean(128, 1);
                }
                {
                    
                result.shouldAutocomplete = decoder0.readBoolean(128, 2);
                }
                {
                    
                result.isEnabled = decoder0.readBoolean(128, 3);
                }
                {
                    
                result.isReadonly = decoder0.readBoolean(128, 4);
                }
                {
                    
                result.checkStatus = decoder0.readInt(132);
                    FormFieldData.CheckStatus.validate(result.checkStatus);
                    result.checkStatus = FormFieldData.CheckStatus.toKnownValue(result.checkStatus);
                }
                {
                    
                result.section = decoder0.readString(136, false);
                }
                {
                    
                result.role = decoder0.readInt(144);
                    FormFieldData.RoleAttribute.validate(result.role);
                    result.role = FormFieldData.RoleAttribute.toKnownValue(result.role);
                }
                {
                    
                result.textDirection = decoder0.readInt(148);
                    org.chromium.mojo_base.mojom.TextDirection.validate(result.textDirection);
                    result.textDirection = org.chromium.mojo_base.mojom.TextDirection.toKnownValue(result.textDirection);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(152, false);
                result.userInput = org.chromium.mojo_base.mojom.String16.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(160, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.options = new SelectOption[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        org.chromium.mojo.bindings.Decoder decoder2 = decoder1.readPointer(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                        result.options[i1] = SelectOption.decode(decoder2);
                    }
                }
                }
                {
                    
                result.labelSource = decoder0.readInt(168);
                    FormFieldData.LabelSource.validate(result.labelSource);
                    result.labelSource = FormFieldData.LabelSource.toKnownValue(result.labelSource);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(176, false);
                result.bounds = org.chromium.gfx.mojom.RectF.decode(decoder1);
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(184, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.datalistValues = new org.chromium.mojo_base.mojom.String16[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        org.chromium.mojo.bindings.Decoder decoder2 = decoder1.readPointer(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                        result.datalistValues[i1] = org.chromium.mojo_base.mojom.String16.decode(decoder2);
                    }
                }
                }
                {
                    
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(192, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.datalistLabels = new org.chromium.mojo_base.mojom.String16[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        org.chromium.mojo.bindings.Decoder decoder2 = decoder1.readPointer(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                        result.datalistLabels[i1] = org.chromium.mojo_base.mojom.String16.decode(decoder2);
                    }
                }
                }

        } finally {
            decoder0.decreaseStackDepth();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void encode(org.chromium.mojo.bindings.Encoder encoder) {
        org.chromium.mojo.bindings.Encoder encoder0 = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        
        encoder0.encode(this.label, 8, false);
        
        encoder0.encode(this.name, 16, false);
        
        encoder0.encode(this.idAttribute, 24, false);
        
        encoder0.encode(this.nameAttribute, 32, false);
        
        encoder0.encode(this.value, 40, false);
        
        encoder0.encode(this.formControlType, 48, false);
        
        encoder0.encode(this.autocompleteAttribute, 56, false);
        
        encoder0.encode(this.placeholder, 64, false);
        
        encoder0.encode(this.cssClasses, 72, false);
        
        encoder0.encode(this.ariaLabel, 80, false);
        
        encoder0.encode(this.ariaDescription, 88, false);
        
        encoder0.encode(this.uniqueRendererId, 96, false);
        
        encoder0.encode(this.hostFormId, 104, false);
        
        encoder0.encode(this.propertiesMask, 112);
        
        encoder0.encode(this.formControlAxId, 116);
        
        encoder0.encode(this.maxLength, 120);
        
        encoder0.encode(this.isAutofilled, 128, 0);
        
        encoder0.encode(this.isFocusable, 128, 1);
        
        encoder0.encode(this.shouldAutocomplete, 128, 2);
        
        encoder0.encode(this.isEnabled, 128, 3);
        
        encoder0.encode(this.isReadonly, 128, 4);
        
        encoder0.encode(this.checkStatus, 132);
        
        encoder0.encode(this.section, 136, false);
        
        encoder0.encode(this.role, 144);
        
        encoder0.encode(this.textDirection, 148);
        
        encoder0.encode(this.userInput, 152, false);
        
        if (this.options == null) {
            encoder0.encodeNullPointer(160, false);
        } else {
            org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(this.options.length, 160, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
            for (int i0 = 0; i0 < this.options.length; ++i0) {
                
                encoder1.encode(this.options[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
            }
        }
        
        encoder0.encode(this.labelSource, 168);
        
        encoder0.encode(this.bounds, 176, false);
        
        if (this.datalistValues == null) {
            encoder0.encodeNullPointer(184, false);
        } else {
            org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(this.datalistValues.length, 184, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
            for (int i0 = 0; i0 < this.datalistValues.length; ++i0) {
                
                encoder1.encode(this.datalistValues[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
            }
        }
        
        if (this.datalistLabels == null) {
            encoder0.encodeNullPointer(192, false);
        } else {
            org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(this.datalistLabels.length, 192, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
            for (int i0 = 0; i0 < this.datalistLabels.length; ++i0) {
                
                encoder1.encode(this.datalistLabels[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
            }
        }
    }
}