package com.najie.exam.segment.dao;


import java.util.List;

import com.najie.exam.segment.model.LeafAlloc;

public interface IDAllocDao {
     List<LeafAlloc> getAllLeafAllocs();
     LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);
     LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);
     List<String> getAllTags();
}
