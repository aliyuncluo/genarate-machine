package com.najie.exam;

import com.najie.exam.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
