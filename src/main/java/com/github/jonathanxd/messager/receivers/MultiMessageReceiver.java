/*
 *      Messager - Simple Messaging Interface! <https://github.com/JonathanxD/Messager>
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2016 TheRealBuggy/JonathanxD (https://github.com/JonathanxD/ & https://github.com/TheRealBuggy/) <jonathan.scripter@programmer.net>
 *      Copyright (c) contributors
 *
 *
 *      Permission is hereby granted, free of charge, to any person obtaining a copy
 *      of this software and associated documentation files (the "Software"), to deal
 *      in the Software without restriction, including without limitation the rights
 *      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *      copies of the Software, and to permit persons to whom the Software is
 *      furnished to do so, subject to the following conditions:
 *
 *      The above copyright notice and this permission notice shall be included in
 *      all copies or substantial portions of the Software.
 *
 *      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *      FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *      THE SOFTWARE.
 */
package com.github.jonathanxd.messager.receivers;

import com.github.jonathanxd.messager.Message;
import com.github.jonathanxd.messager.MessageSender;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by jonathan on 21/04/16.
 */
public class MultiMessageReceiver<T, R> implements TypedMessageReceiver<T> {

    private final BiFunction<T, R, TypedMsgMessageReceiver<R>> receiverFunction;
    private final Function<T, R> mapper;

    public MultiMessageReceiver(BiFunction<T, R, TypedMsgMessageReceiver<R>> receiverFunction, Function<T, R> mapper) {
        this.receiverFunction = receiverFunction;
        this.mapper = mapper;
    }

    @Override
    public void typedReceive(MessageSender<T> messageSender, Message<T> message) {
        R mapped = message.map(mapper);

        TypedMsgMessageReceiver<R> apply = receiverFunction.apply(message.getContent(), mapped);

        if(apply != null) {
            apply.receive(messageSender, message.newInstanceTimed(mapped));
        }

    }
}
