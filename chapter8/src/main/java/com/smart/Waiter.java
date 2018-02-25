package com.smart;

import com.smart.anno.NeedTest;

public interface Waiter {
    @NeedTest
    void greetTo(String clientName);

    void serveTo(String clientName);
}
