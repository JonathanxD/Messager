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
import com.github.jonathanxd.messager.MessageSender;
import com.github.jonathanxd.messager.receivers.MappedReceiver;
import com.github.jonathanxd.messager.receivers.MultiMessageReceiver;
import com.github.jonathanxd.messager.Receiver;
import com.github.jonathanxd.messager.receivers.TypedMsgMessageReceiver;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathan on 21/04/16.
 */
public class SimpleCommunication {

    @Test
    public void testSimpleCommunication() {
        Communication communication = new Communication();

        TypedMsgMessageReceiver<List> listReceiver = (messageSender, message) -> {
            Instant now = Instant.now();
            Assert.assertArrayEquals(new Object[]{"A", "B"}, message.getContent().toArray());
            System.out.println("Received from '" + messageSender + "' -> a list -> " + message + ". Now = " + now);
        };

        TypedMsgMessageReceiver<String> stringReceiver = (messageSender, message) -> {
            Instant now = Instant.now();
            Assert.assertEquals("0.", message.getContent());
            System.out.println("Received from '" + messageSender + "' -> a string -> " + message + ". Now = " + now);
        };

        communication.addGlobal(new MultiMessageReceiver<>(o -> {
            if(o instanceof List) {
                return new MappedReceiver<>(x -> (List) x, listReceiver);
            }

            if(o instanceof String) {
                return new MappedReceiver<>(Object::toString, stringReceiver);
            }
            return null;
        }, t -> t));

        // communication.<List<String>>addGlobal(new MultiMessageReceiver(message -> {
        //   if(message.getType() == END_VISIT) {
        //       return new TypeReceiver(message.getAs(END_VISIT));
        //   }
        //   return null;
        // }, message -> message.map(Types::getType))
        MessageSender<Object> objectMessageSender = MessageSender.newInstance(communication);

        objectMessageSender.send(Arrays.asList("A", "B"));
        objectMessageSender.send("0.");
    }
}
