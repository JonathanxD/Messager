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
package com.github.jonathanxd.messager.test;

import com.github.jonathanxd.messager.Communication;
import com.github.jonathanxd.messager.MessageReceiver;
import com.github.jonathanxd.messager.MessageSender;

import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;

/**
 * Created by jonathan on 21/04/16.
 */
public class SimpleCommunication {

    @Test
    public void testSimpleCommunication() {
        Communication communication = new Communication();

        communication.addGlobal((messageSender, message) -> {
            Instant now = Instant.now();
            System.out.println("Received from '"+messageSender+"' -> "+message+". Now = "+now);
        });

        MessageSender<Object> objectMessageSender = MessageSender.newInstance(communication);

        objectMessageSender.send(Arrays.asList("A", "B"));
    }
}
