/*
 * Copyright (c) 2011-2018 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.client.voice;

import com.nexmo.client.HttpConfig;
import com.nexmo.client.HttpWrapper;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StartTalkMethodTest {
    private StartTalkMethod method;

    @Before
    public void setUp() throws Exception {
        this.method = new StartTalkMethod(new HttpWrapper());
    }

    @Test
    public void testDefaultUri() throws Exception {
        TalkRequest request = new TalkRequest("uuid", "text", 0);
        RequestBuilder builder = method.makeRequest(request);
        assertEquals("PUT", builder.getMethod());
        assertEquals("https://api.nexmo.com/v1/calls/uuid/talk", builder.build().getURI().toString());
    }

    @Test
    public void testCustomUri() throws Exception {
        HttpWrapper wrapper = new HttpWrapper(new HttpConfig.Builder().baseUri("https://example.com").build());
        StartTalkMethod method = new StartTalkMethod(wrapper);
        TalkRequest request = new TalkRequest("uuid", "text", 0);

        RequestBuilder builder = method.makeRequest(request);
        assertEquals("PUT", builder.getMethod());
        assertEquals("https://example.com/v1/calls/uuid/talk", builder.build().getURI().toString());
    }
}
