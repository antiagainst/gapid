/*
 * Copyright (C) 2017 Google Inc.
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
package com.google.gapid.rpclib.any;

import com.google.gapid.rpclib.binary.BinaryClass;
import com.google.gapid.rpclib.binary.BinaryObject;
import com.google.gapid.rpclib.binary.Decoder;
import com.google.gapid.rpclib.binary.Encoder;
import com.google.gapid.rpclib.binary.Namespace;
import com.google.gapid.rpclib.schema.Entity;
import com.google.gapid.rpclib.schema.Field;
import com.google.gapid.rpclib.schema.Interface;

import java.io.IOException;

final class ObjectBox extends Box implements BinaryObject {
    @Override
    public Object unwrap() {
        return getValue();
    }

    //<<<Start:Java.ClassBody:1>>>
    private BinaryObject mValue;

    // Constructs a default-initialized {@link ObjectBox}.
    public ObjectBox() {}


    public BinaryObject getValue() {
        return mValue;
    }

    public ObjectBox setValue(BinaryObject v) {
        mValue = v;
        return this;
    }

    @Override
    public BinaryClass klass() { return Klass.INSTANCE; }


    private static final Entity ENTITY = new Entity("any", "object_", "", "");

    static {
        ENTITY.setFields(new Field[]{
            new Field("Value", new Interface("binary.Object")),
        });
        Namespace.register(Klass.INSTANCE);
    }
    public static void register() {}
    //<<<End:Java.ClassBody:1>>>
    public enum Klass implements BinaryClass {
        //<<<Start:Java.KlassBody:2>>>
        INSTANCE;

        @Override
        public Entity entity() { return ENTITY; }

        @Override
        public BinaryObject create() { return new ObjectBox(); }

        @Override
        public void encode(Encoder e, BinaryObject obj) throws IOException {
            ObjectBox o = (ObjectBox)obj;
            e.object(o.mValue);
        }

        @Override
        public void decode(Decoder d, BinaryObject obj) throws IOException {
            ObjectBox o = (ObjectBox)obj;
            o.mValue = d.object();
        }
        //<<<End:Java.KlassBody:2>>>
    }
}
