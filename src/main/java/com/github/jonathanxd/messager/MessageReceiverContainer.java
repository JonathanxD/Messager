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
package com.github.jonathanxd.messager;

import java.util.function.Predicate;

/**
 * Created by jonathan on 21/04/16.
 */
public class MessageReceiverContainer<T> {
    private final MessageReceiver<T> messageReceiver;
    private final Predicate<Message<?>> messagePredicate;

    public MessageReceiverContainer(MessageReceiver<T> messageReceiver, Predicate<Message<?>> messagePredicate) {
        this.messageReceiver = messageReceiver;
        this.messagePredicate = messagePredicate;
    }

    public MessageReceiver<T> getMessageReceiver() {
        return messageReceiver;
    }

    public Predicate<Message<?>> getMessagePredicate() {
        return messagePredicate;
    }
}
